package com.ktd.vivacon.server;

public interface Handler {
    Object handle(Request request, Response response);
}
