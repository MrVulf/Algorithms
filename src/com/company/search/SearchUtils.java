package com.company.search;

import java.util.List;

public class SearchUtils {

    /**
     * binarySearch - the method, which calculates a position of the {@param number} in the {@param sortedNumber} list.
     *
     * @param sortedNumbers - the list with numbers, that was sorted previously.
     * @param number        - the number for search.
     * @return - position of the {@param number}
     */
    public static Integer binarySearch(List<Integer> sortedNumbers, Integer number) {
        int right = sortedNumbers.size() - 1;
        int left = 0;

        while (right >= left) {
            int middlePos = left + (right - left) / 2;

            Integer numberOnPosition = sortedNumbers.get(middlePos);
            if (numberOnPosition.equals(number)) {
                return middlePos;
            }

            if (numberOnPosition.compareTo(number) > 0) {
                right = middlePos - 1;
            } else {
                left = middlePos + 1;
            }

        }

        return -1;
    }

    private SearchUtils() {}
}
