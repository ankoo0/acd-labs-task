package com.acdlabs;

import java.util.Comparator;

public class ValueComparator implements Comparator<String> {

    @Override
    public int compare(String first, String second) {
        if (first.isEmpty() || second.isEmpty()) {
            return 0;
        }

        boolean isFirstNumber = isNumber(first);
        boolean isSecondNumber = isNumber(second);

        if (isFirstNumber && isSecondNumber) {
            double num1 = Double.parseDouble(first);
            double num2 = Double.parseDouble(second);

            return Double.compare(num1, num2);
        } else if (!isFirstNumber && !isSecondNumber) {

            return first.compareTo(second);
        } else {

            return isFirstNumber ? -1 : 1;
        }
    }

    public static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
