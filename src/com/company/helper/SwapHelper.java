package com.company.helper;

import java.util.List;

public class SwapHelper <T>  {
    public void swap(List<T> numbers, int pos1, int pos2) {
        T number1 = numbers.get(pos1);
        T number2 = numbers.get(pos2);
        numbers.set(pos1, number2);
        numbers.set(pos2, number1);
    }
}
