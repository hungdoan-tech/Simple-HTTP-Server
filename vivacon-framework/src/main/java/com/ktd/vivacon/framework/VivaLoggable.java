package com.ktd.vivacon.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VivaLoggable {

    protected final transient Logger logger
            = LoggerFactory.getLogger(getClass());

    protected Logger getLogger() {
        return logger;
    }
}
