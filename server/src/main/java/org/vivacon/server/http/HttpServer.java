package org.vivacon.server.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vivacon.framework.web.SimpleIoCContainer;
import org.vivacon.server.http.exception.MethodHandlerNotFound;
import org.vivacon.server.http.exception.PathHandlerNotFound;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {

    private static final int DEFAULT_PORT = 8090;

    private static final Logger LOG = LoggerFactory.getLogger(HttpServer.class);

    private int port;

    private static Map<String, Map<Method, Handler>> pathHandlers = new HashMap<>();

    public static SimpleIoCContainer simpleIoCContainer;

    public HttpServer(int port) {
        this.port = port;
    }

    public HttpServer() {
        this.port = DEFAULT_PORT;
    }

    public static void main(String[] args) {
        simpleIoCContainer = new SimpleIoCContainer();
        HttpServer server = new HttpServer();
        try {
            LOG.info("Start the http server at port " + server.port);
            server.start();
        } catch (IOException e) {
            LOG.info("Something went wrong with the process of operating the http server");
        }
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port)) {
            StaticFileHandler staticFileHandler = new StaticFileHandler();
            addHandler("/index.html", Method.GET, staticFileHandler);
            addHandler("/", Method.GET, staticFileHandler);

            Socket client;
            while ((client = serverSocket.accept()) != null) {
                LOG.info("Receiving and starting the handle the request");
                SimpleDispatcher simpleDispatcher = new SimpleDispatcher(client);
                Thread thread = new Thread(simpleDispatcher);
                thread.start();
                LOG.info("Waiting for the next incoming request");
            }
        }
    }

    private static void addHandler(String path, Method method, Handler handler) {
        Map<Method, Handler> methodHandlers = pathHandlers.get(path);
        if (methodHandlers == null) {
            methodHandlers = new HashMap<>();
            methodHandlers.put(method, handler);
        }
        pathHandlers.put(path, methodHandlers);
    }

    public static Handler getHandler(String path, Method method) throws PathHandlerNotFound, MethodHandlerNotFound {
        Map<Method, Handler> methodHandlerMap = pathHandlers.get(path);
        if (methodHandlerMap == null) {
            throw new PathHandlerNotFound();
        }
        Handler methodHandler = methodHandlerMap.get(method);
        if (methodHandler == null) {
            throw new MethodHandlerNotFound();
        }
        return methodHandler;
    }

}
