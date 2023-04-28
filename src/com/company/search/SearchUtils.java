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
        while (true) {
            int position = calculatePositionInBinarySearch(left, right);
            Integer numberOnPosition = sortedNumbers.get(position);
            if (numberOnPosition.equals(number)) {
                return position;
            } else {
                if (numberOnPosition.compareTo(number) > 0) {
                    right = position;
                } else {
                    left = position;
                }
            }
        }
    }

    private static Integer calculatePositionInBinarySearch(Integer left, Integer right) {
        int diff = right - left;
        if (diff > 1) {
            return diff / 2 + left;
        } else {
            return left == 0 ? left : right;
        }
    }

    private SearchUtils() {}
}
