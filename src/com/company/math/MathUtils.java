package com.company.math;

public class MathUtils {

    /**
     * factorial - the method, which calculate factorial of the {@param number}.
     *
     * @param number - the input number.
     * @return - factorial of the {@param number}.
     */
    public static Integer factorial(Integer number) {
        if (number.equals(1)) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }

    private MathUtils() {}
}
