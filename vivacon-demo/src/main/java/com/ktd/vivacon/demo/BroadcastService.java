package com.ktd.vivacon.demo;

import com.ktd.vivacon.framework.annotation.Component;

@Component
public class BroadcastService {
    public String echo(String message) {
        return message;
    }
}
