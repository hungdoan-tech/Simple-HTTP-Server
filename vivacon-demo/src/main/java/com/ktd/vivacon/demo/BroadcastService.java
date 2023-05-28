package com.ktd.vivacon.demo;

import com.ktd.vivacon.framework.Component;

@Component
public class BroadcastService {
    public String echo(String message) {
        return message;
    }
}
