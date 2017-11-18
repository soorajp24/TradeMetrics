package com.statestreet.ais.interview.metrics;

import java.math.BigDecimal;

public class BigDecimalHelper {

    public static boolean equals(BigDecimal value1, BigDecimal value2) {
        if (value1 == null && value2 == null) {
            return true;
        }
        else if (value1 == null || value2 == null) {
            return false;
        }

        return (value1.compareTo(value2) == 0);
    }
}
