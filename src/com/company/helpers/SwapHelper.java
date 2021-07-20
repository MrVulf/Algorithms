package com.company.helpers;

import java.util.List;

public class SwapHelper <T>  {
    public void swapAndSetNumbersInList(List<T> numbers, T number1, int pos1, T number2, int pos2) {
        numbers.set(pos1, number2);
        numbers.set(pos2, number1);
    }

    public void swapNumbersInList(List<T> numbers, int pos1, int pos2) {
        T number1 = numbers.get(pos1);
        T number2 = numbers.get(pos2);
        numbers.set(pos1, number2);
        numbers.set(pos2, number1);
    }
}
