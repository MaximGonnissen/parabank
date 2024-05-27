package com.parasoft.parabank.graphwalker.utils;

import org.graphwalker.java.test.Result;

public class ResultHandler {

    public static boolean handleResult(Result result, boolean isolatedTest) {
        return handleResult(result, isolatedTest, false);
    }

    public static boolean handleResult(Result result, boolean isolatedTest, boolean verbose) {
        if (verbose)
            System.out.println("Test finished with result: " + result.getResults().toString());

        if (result.hasErrors()) {
            if (isolatedTest && result.getErrors().size() == 1 && result.getErrors().get(0).contains("Could not find a valid path from element")) {
                return true;
            }

            System.out.println("Errors: " + result.getErrors().toString());
            return false;
        }

        return !result.getResults().toString().contains("FAILED");
    }
}
