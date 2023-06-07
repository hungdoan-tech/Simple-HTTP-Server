package com.ktd.vivacon.framework.impl;

import com.ktd.vivacon.framework.VivaBeanNameTranslator;
import com.ktd.vivacon.framework.VivaClasses;
import com.ktd.vivacon.framework.VivaLoggable;
import lombok.Setter;

public class VivaSimpleBeanFactory extends VivaLoggable {

    @Setter
    protected VivaBeanNameTranslator beanNameTranslator;

    protected final String translateBeanName(String name, Class<?> type) {
        if (beanNameTranslator == null) {
            return name;
        }
        return beanNameTranslator.translate(name, type);
    }

    protected final void mapBeanName(String freeName, Class<?> type, String realName) {
        if (beanNameTranslator != null) {
            beanNameTranslator.map(freeName, type, realName);
        }
    }

    protected final String getDefaultBeanName(Class<?> type) {
        return VivaClasses.getVariableName(type);
    }
}
