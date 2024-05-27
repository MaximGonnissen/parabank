package com.parasoft.parabank.graphwalker.Base;

import com.parasoft.parabank.graphwalker.utils.Driver;
import org.graphwalker.core.generator.UnifiedDummyPathGenerator;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.BeforeElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestExecutionContext extends ExecutionContext {
    public static final String MODEL_PATH = "org/graphwalker/parabank_model.json";
    protected Logger logger = null;

    public TestExecutionContext() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @BeforeElement
    public void setup() {
        if (getPathGenerator() instanceof UnifiedDummyPathGenerator) {
            logger.info("Entering: {} (Unified element: {})", getCurrentElement().getName(), getPathGenerator().getContext().getCurrentElement().getName());
        }
        else {
            logger.info("Entering: " + getCurrentElement().getName());
        }
        logger.info("Stop condition %: " + Math.round(getPathGenerator().getStopCondition().getFulfilment() * 100));
        Driver.waitFor(50);    // Wait for 100ms to make sure stuff is a bit less flaky
    }
}
