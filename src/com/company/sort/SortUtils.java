package com.company.sort;

import com.company.helper.SwapHelper;

import java.util.Comparator;
import java.util.List;


public class SortUtils {
    private static final SwapHelper<Integer> swapHelper = new SwapHelper<>();

    private static void swap(int[] array, int left, int right) {
        int var = array[left];
        array[left] = array[right];
        array[right] = var;
    }

    /**
     * The bubble sort is the sort method where algorithm compares all pairs of elements.
     *
     * @param array - array to sort.
     */
    public static void bubbleSort(int[] array) {
        int length = array.length;

        for (int i = length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
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
     * quickSort - the method, which sorts number in the list with the following behavior:
     * 1. Choose a pivot - the element with which other elements will be compared.
     * 2. Compare all elements with the pivot:
     * If element is more than pivot, then it should be placed on the right part is relative to the pivot.
     * If element is less than pivot, then it should be placed on the left part is relative to the pivot.
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
        int pivot = getPivot(array, left, right);
        int leftPointer = left, rightPointer = right;

        while (leftPointer <= rightPointer) {
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
        int pivotPosition = (right + left) / 2;
        return array[pivotPosition];
    }

    /**
     * The merge sort is the algorithm which divides the array into two parts: left and right.
     * After that do the same with left and right parts recursively.
     * When size of the input array is 1, then return array as is.
     * When size of the input array is 2, then compare 2 elements and swap them if necessary.
     * Work with both parts is performed on the same array, so no one additional array would be created.
     *
     * @param array - array to sort.
     */
    public static void mergeSort(int[] array) {
        mergeSortInternal(array, 0, array.length - 1);
    }

    public static void mergeSortInternal(int[] array, int leftBorder, int rightBorder) {
        if (rightBorder == leftBorder) {
            return;
        }

        if (rightBorder - leftBorder + 1 == 2) {
            if (array[leftBorder] > array[rightBorder]) {
                int var = array[leftBorder];
                array[leftBorder] = array[rightBorder];
                array[rightBorder] = var;
            }
            return;
        }

        int half = leftBorder + (rightBorder - leftBorder) / 2;
        mergeSortInternal(array, leftBorder, half);
        mergeSortInternal(array, half + 1, rightBorder);


        int[] tempArray = mergeSortedArrays(array, leftBorder, half, rightBorder);
        System.arraycopy(tempArray, 0, array, leftBorder, tempArray.length);

    }

    private static int[] mergeSortedArrays(int[] array, int leftBorder, int half, int rightBorder) {
        int[] tempArray = new int[rightBorder - leftBorder + 1];
        int resultPointer = 0, leftPointer = leftBorder, rightPointer = half + 1;

        while (leftPointer <= half && rightPointer <= rightBorder) {
            int leftElement = array[leftPointer];
            int rightElement = array[rightPointer];

            if (leftElement < rightElement) {
                tempArray[resultPointer] = leftElement;
                leftPointer++;
            } else {
                tempArray[resultPointer] = rightElement;
                rightPointer++;
            }
            resultPointer++;
        }

        while (leftPointer <= half) {
            tempArray[resultPointer] = array[leftPointer];
            leftPointer++;
            resultPointer++;
        }

        while (rightPointer <= rightBorder) {
            tempArray[resultPointer] = array[rightPointer];
            rightPointer++;
            resultPointer++;
        }
        return tempArray;
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

    private SortUtils() {}
}
