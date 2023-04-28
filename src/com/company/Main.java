package com.company;

import com.company.helpers.SwapHelper;

import java.util.*;

public class Main {

    private static final SwapHelper<Integer> swapHelper = new SwapHelper<>();

    public static void main(String[] args) {}

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
     * The bubble sort is the sort method where algorithm compares all pairs of elements.
     *
     * @param array - array to sort.
     */
    public static void bubbleSort(int[] array) {
        int length = array.length;

        for (int i = length-1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j+1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * The Comb Sort is the modification of the bubble-sort algorithm.
     * The main difference is comparing elements that are placed on a distance.
     * The distance decrease with each new cycle iteration.
     *
     * @param array - array to sort.
     */
    public static void combSort(int[] array) {
        int length = array.length;
        int step = (int) (length / 1.247);

        while (step != 0) {
            for (int i = 0; i + step < length; i++) {
                if (array[i] > array[i + step]) {
                    swap(array, i, i + step);
                }
            }
            step = (int) (step / 1.247);
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
                swapHelper.swap(numbers, i, positionForSwap);
            }
        }
        return numbers;
    }

    /**
     * quickSort - the method, which sorts number in the list with the following behavior:
     *  1. Choose a pivot - the element with which other elements will be compared.
     *  2. Compare all elements with the pivot:
     *      If element is more than pivot, then it should be placed on the right part is relative to the pivot.
     *      If element is less than pivot, then it should be placed on the left part is relative to the pivot.
     *
     * @param array - the input number array.
     */

    public static void quickSort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        quickSortInternal(array, 0, array.length - 1);
    }

    private static void quickSortInternal(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = getPivot(array, left, right);
        int leftPointer = left, rightPointer = right;

        while(leftPointer <= rightPointer) {
            while (array[leftPointer] < pivot) leftPointer++;
            while (array[rightPointer] > pivot) rightPointer--;
            if (leftPointer <= rightPointer) {
                swap(array, leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            }
        }

        if (left < rightPointer) {
            quickSortInternal(array, left, rightPointer);
        }

        if (right > leftPointer) {
            quickSortInternal(array, leftPointer, right);
        }

    }

    private static int getPivot(int[] array, int left, int right) {
        int pivotPosition = left + (right - left) / 2;
        return array[pivotPosition];
    }

    private static void swap(int[] array, int left, int right) {
        int var = array[left];
        array[left] = array[right];
        array[right] = var;
    }

    /**
     * quickSort - the method, which sorts number in the list with the following behavior:
     *  1. Choose a pivot - the element with which other elements will be compared.
     *  2. Compare all elements with the pivot:
     *      If element is more than pivot, then it should be placed on the right part is relative to the pivot.
     *      If element is less than pivot, then it should be placed on the left part is relative to the pivot.
     * That realization uses two additional arrays: one for elements from "right" side and another one for elements from
     * "left" side. Finally, that two arrays are merged in one.
     *
     * @param numbers    - the input number list.
     * @param comparator - object, which implements {@link java.util.Comparator}.
     * @return - new {@link List} with sorted numbers.
     */
    public static List<Integer> quickSortWithArrays(List<Integer> numbers, Comparator<Integer> comparator) {
        if (numbers.isEmpty() || numbers.size() == 1) {
            return numbers;
        } else if (numbers.size() == 2) {
            Integer number0 = numbers.get(0);
            Integer number1 = numbers.get(1);
            if (comparator.compare(number0, number1) > 0) {
                swapHelper.swap(numbers, 0, 1);
            }
            return numbers;
        } else {
            List<Integer> arrayLess = new ArrayList<>();
            List<Integer> arrayMore = new ArrayList<>();
            int pivotPosition = (int) (Math.random() * numbers.size());
            int pivot = numbers.get(pivotPosition);

            splitNumberListUsingSupportElement(numbers, pivotPosition, pivot, arrayLess, arrayMore, comparator);

            arrayLess = quickSortWithArrays(arrayLess, comparator);
            arrayMore = quickSortWithArrays(arrayMore, comparator);

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
