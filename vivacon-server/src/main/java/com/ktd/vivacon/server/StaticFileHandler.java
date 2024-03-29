package com.ktd.vivacon.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ktd.vivacon.server.exception.StaticResourceNotFound;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

public class StaticFileHandler implements Handler {
    private static final Logger LOG = LoggerFactory.getLogger(StaticFileHandler.class);

    @Override
    public Object handle(Request request, Response response) throws StaticResourceNotFound {
        String path = request.getPath();
        String content;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/main/resources/" + path));
            content = bufferedReader.lines().collect(Collectors.joining("\n"));
            response.setResponseCode(200, "OK");
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
        } catch (FileNotFoundException e) {
            LOG.info("Can not find any suitable static file to serve for !" + request.getPath());
            throw new StaticResourceNotFound();
        }
        return content;
    }
}
