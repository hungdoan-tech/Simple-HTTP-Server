package org.vivacon.framework.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class BeanFactory {
    private Logger logger = LoggerFactory.getLogger(BeanFactory.class);

    private static volatile BeanFactory instance;

    private final Map<Class<?>, List<String>> beanToBindingNamesCache;

    private BeanFactory() {
        beanToBindingNamesCache = new HashMap<>();
        // Private constructor to prevent instantiation
    }

    public static BeanFactory getInstance() {
        if (instance == null) {
            synchronized (BeanFactory.class) {
                if (instance == null) {
                    instance = new BeanFactory();
                }
            }
        }
        return instance;
    }

    public Object createBean(Class<?> beanClazz, Map<String, Set<Object>> existingBeans) {
        try {
            List<String> bindingNames = getBeanBindingName(beanClazz);

            for (String bindingName : bindingNames) {
                Set<Object> beans = existingBeans.get(bindingName);
                return beans.iterator().next();
            }

            Constructor<?> constructorToInject = getConstructorToInject(beanClazz);

            LinkedHashMap<Parameter, List<String>> dependencyToItsBindNames = getDependencyToItsBindNames(constructorToInject);

            Object[] dependencies = populateDependencies(dependencyToItsBindNames, existingBeans);

            return constructorToInject.newInstance(dependencies);

        } catch (InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException("Failed to create bean: " + beanClazz.getName(), e);
        }
    }

    private Object[] populateDependencies(LinkedHashMap<Parameter, List<String>> dependencyToItsBindNames,
                                          Map<String, Set<Object>> existingBeans) {

        Object[] dependencies = new Object[dependencyToItsBindNames.size()];

        int runner = 0;

        for (Map.Entry<Parameter, List<String>> entry : dependencyToItsBindNames.entrySet()) {

            Parameter parameter = entry.getKey();
            Qualifier qualifier = parameter.getAnnotation(Qualifier.class);

            if (qualifier != null) {

                Set<Object> beans = existingBeans.get(qualifier.name());

                if (beans.isEmpty()) {
                    throw new IllegalArgumentException("Can not find the suitable bean for the binding name, please check the bean registration");
                }

                if (beans.size() == 1) {
                    dependencies[runner++] = beans.iterator().next();
                    continue;
                }

                throw new IllegalArgumentException("Can not find the suitable bean for the binding name, please check the bean registration");
            }


            List<String> lowPriorityBindingNames = entry.getValue();
            Optional<Object> beanViaLowPriorityBindingName = findBeansViaBindingName(lowPriorityBindingNames, existingBeans);
            if (beanViaLowPriorityBindingName.isPresent()) {
                dependencies[runner++] = beanViaLowPriorityBindingName.get();
                continue;
            }

            runner++;
        }

        return dependencies;
    }

    private Optional<Object> findBeansViaBindingName(List<String> bindingNames, Map<String, Set<Object>> existingBeans) {

        for (String bindingName : bindingNames) {

            Set<Object> beans = existingBeans.get(bindingName);

            if (beans.isEmpty()) {
                return Optional.empty();
            }

            if (beans.size() == 1) {
                return Optional.of(beans.iterator().next());
            }

            logger.debug("Binding name {} is currently binding to many objects {}", bindingName, beans);
        }

        throw new IllegalArgumentException("Can not find the suitable bean for the binding name, please check the bean registration");
    }

    private LinkedHashMap<Parameter, List<String>> getDependencyToItsBindNames(Constructor<?> constructor) {
        Parameter[] parameters = constructor.getParameters();
        LinkedHashMap<Parameter, List<String>> dependencyToItsBindNames = new LinkedHashMap<>();

        for (Parameter parameter : parameters) {
            Class<?> dependencyType = parameter.getType();
            List<String> dependencyBindNames = getBeanBindingName(dependencyType);
            dependencyToItsBindNames.put(parameter, dependencyBindNames);
        }

        return dependencyToItsBindNames;
    }

    private List<String> getBeanBindingName(Class<?> beanClazz) {
        if (beanToBindingNamesCache.get(beanClazz) != null) {
            return beanToBindingNamesCache.get(beanClazz);
        }

        List<String> interfaceNames = Arrays.stream(beanClazz.getInterfaces()).map(Class::getSimpleName).toList();
        List<String> bindingNames = new LinkedList<>(interfaceNames);

        Qualifier[] qualifiers = beanClazz.getAnnotationsByType(Qualifier.class);
        if (qualifiers.length == 0) {
            bindingNames.add(beanClazz.getSimpleName());
            beanToBindingNamesCache.put(beanClazz, bindingNames);
            return bindingNames;
        }

        for (Qualifier qualifier : qualifiers) {
            String name = qualifier.name();
            bindingNames.add(name);
        }

        beanToBindingNamesCache.put(beanClazz, bindingNames);
        return bindingNames;
    }

    private Constructor<?> getConstructorToInject(Class<?> beanClazz) {
        Constructor<?> defeaultConstructor = null;
        try {
            defeaultConstructor = beanClazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            // just ignore
        }

        // only get the public constructors
        Constructor<?>[] constructors = beanClazz.getConstructors();

        if (constructors.length == 1 && defeaultConstructor != null) {
            return defeaultConstructor;
        }

        if (constructors.length == 1 && constructors[0].getParameters().length > 0) {
            return constructors[0];
        }

        for (Constructor<?> constructor : constructors) {
            Autowired[] attachedAutowiredAnnotations = constructor.getAnnotationsByType(Autowired.class);
            if (attachedAutowiredAnnotations.length > 1) {
                return constructor;
            }
        }

        throw new IllegalArgumentException(String.format("The class %s has many constructor but no one was annotated by Autowired to specify which the dependencies to inject to the bean", beanClazz));
    }
}