package com.company;

import com.company.helpers.SwapHelper;

import java.util.*;

public class Main {

    private static final SwapHelper<Integer> swapHelper = new SwapHelper<>();

    public static void main(String[] args) {
    }

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

    /**
     * sortingByChoice - the method, which sorts number in the list with "choice"-criteria:
     * the method tries to find the most appropriate number at each iteration (using {@param comparator}),
     * and then insert it into the appropriate place in the list.
     *
     * @param numbers    - the list with numbers.
     * @param comparator - object, which implements {@link java.util.Comparator}.
     * @return - sorted {@param numbers} list.
     */
    public static List<Integer> sortingByChoice(List<Integer> numbers, Comparator<Integer> comparator) {
        Integer localOptimum;
        Integer optimumCandidate;
        int positionForSwap;

        for (int i = 0; i < numbers.size() - 1; i++) {
            localOptimum = numbers.get(i);
            positionForSwap = -1;
            for (int j = i + 1; j < numbers.size(); j++) {
                optimumCandidate = numbers.get(j);
                if (comparator.compare(localOptimum, optimumCandidate) > 0) {
                    localOptimum = optimumCandidate;
                    positionForSwap = j;
                }
            }
            if (positionForSwap != -1) {
                swapHelper.swapNumbersInList(numbers, i, positionForSwap);
            }
        }
        return numbers;
    }

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

    /**
     * quickSort - the method, which sorts number in the list with the method of splitting the list and sorting the parts.
     *
     * @param numbers    - the input number list.
     * @param comparator - object, which implements {@link java.util.Comparator}.
     * @return - new {@link List} with sorted numbers.
     */
    public static List<Integer> quickSort(List<Integer> numbers, Comparator<Integer> comparator) {
        if (numbers.isEmpty() || numbers.size() == 1) {
            return numbers;
        } else if (numbers.size() == 2) {
            Integer number0 = numbers.get(0);
            Integer number1 = numbers.get(1);
            if (comparator.compare(number0, number1) > 0) {
                swapHelper.swapNumbersInList(numbers, 0, 1);
            }
            return numbers;
        } else {
            List<Integer> arrayLess = new ArrayList<>();
            List<Integer> arrayMore = new ArrayList<>();
            int pivotPosition = (int) (Math.random() * numbers.size());
            int pivot = numbers.get(pivotPosition);

            splitNumberListUsingSupportElement(numbers, pivotPosition, pivot, arrayLess, arrayMore, comparator);

            arrayLess = quickSort(arrayLess, comparator);
            arrayMore = quickSort(arrayMore, comparator);

            return makeResultSortedArray(arrayLess, pivot, arrayMore);
        }
    }

    private static void splitNumberListUsingSupportElement(List<Integer> numbers,
                                                           int pivotPosition,
                                                           Integer pivot,
                                                           List<Integer> arrayLess,
                                                           List<Integer> arrayMore,
                                                           Comparator<Integer> comparator) {
        for (int i = 0; i < numbers.size(); i++) {
            if (i != pivotPosition) {
                Integer element = numbers.get(i);
                if (comparator.compare(element, pivot) > 0) {
                    arrayMore.add(element);
                } else {
                    arrayLess.add(element);
                }
            }
        }
    }

    private static List<Integer> makeResultSortedArray(List<Integer> arrayLess, Integer pivot, List<Integer> arrayMore) {
        List<Integer> sortedArray = new ArrayList<>();

        mergeArrays(arrayLess, sortedArray);
        sortedArray.add(pivot);
        mergeArrays(arrayMore, sortedArray);

        return sortedArray;
    }

    private static void mergeArrays(List<Integer> arrayFrom, List<Integer> arrayTo) {
        if (!arrayFrom.isEmpty()) {
            arrayTo.addAll(arrayFrom);
        }
    }

}
