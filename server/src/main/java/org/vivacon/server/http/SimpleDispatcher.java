package org.vivacon.server.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;

public class SimpleDispatcher implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleDispatcher.class);

    private Socket client;

    public SimpleDispatcher(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        Response response = null;
        try {
            Request request = new Request(this.client);
            response = new Response(this.client);
            if (!request.parse()) {
                response.respond(400, "Bad request");
                return;
            }
            //Handler handler = HttpServer.getHandler(request.getPath(), request.getVerb());
            //Object result = handler.handle(request, response);

            Method endpointHandler = HttpServer.simpleIoCContainer.getEndpointHandler(request.getPath());
            Object controller = HttpServer.simpleIoCContainer.getController(request.getPath());
            Object result = endpointHandler.invoke(controller);

            response.setBody(result.toString());
            response.setResponseCode(200, "OK");
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            response.send();
//        } catch (PathHandlerNotFound | StaticResourceNotFound e) {
//            response.respond(404, "The resource is not found");
//        } catch (MethodHandlerNotFound e) {
//            response.respond(403, "Method is not supported");
        } catch (Exception e) {
            if (response != null) {
                response.respond(500, "Internal server error");
            }
        } finally {
            try {
                this.client.close();
            } catch (IOException e) {
                LOG.error("Can not close the connection to client");
            }
        }
    }

}
