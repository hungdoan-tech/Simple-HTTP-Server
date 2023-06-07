package com.ktd.vivacon.framework.impl;

import com.ktd.vivacon.framework.VivaErrorHandler;
import com.ktd.vivacon.framework.VivaLoggable;

public class VivaSimpleErrorHandler
        extends VivaLoggable
        implements VivaErrorHandler {

    @Override
    public void handle(Throwable error) {
        logger.warn("error", error);
    }
}
