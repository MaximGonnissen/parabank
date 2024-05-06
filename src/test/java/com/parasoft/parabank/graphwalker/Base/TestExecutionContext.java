package com.parasoft.parabank.graphwalker.Base;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.BeforeElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestExecutionContext extends ExecutionContext {
    protected Logger logger = null;
    public static final String MODEL_PATH = "org/graphwalker/parabank_model.json";

    public TestExecutionContext() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @BeforeElement
    public void setup() {
        logger.info("Entering: " + getCurrentElement().getName());
    }
}
