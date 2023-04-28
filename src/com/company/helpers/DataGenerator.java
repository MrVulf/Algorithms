package com.company.helpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DataGenerator {
    private static final int radius = 100;

    public static List<Integer> generateNumberList(int size) {
        List<Integer> list = new ArrayList<Integer>(size);
        for(int i = 0; i < size; i++){
            list.add(i, new Double(Math.random()*radius).intValue());
        }
        return list;
    }

    public static List<Integer> generateUniqueNumberList(int size) {
        Integer element;
        List<Integer> list = new ArrayList<Integer>(size);
        for(int i = 0; i < size; i++){
            do {
            element = new Double(Math.random()*radius).intValue();
            } while (list.contains(element));
            list.add(i, element);
        }
        return list;
    }

    public static Comparator<Integer> getAscendingOrderComparator() {
        return (o1, o2) -> {
            if(o1.equals(o2)) {
                return 0;
            } else {
                return o1.compareTo(o2) > 0 ? 1 : -1;
            }
        };
    }

    public static Comparator<Integer> getDescendingOrderComparator() {
        return (o1, o2) -> getAscendingOrderComparator().compare(o1,o2) * (-1);
    }

    public static Integer getRandomNumberFromList(List<Integer> numberList){
        int max = numberList.size();
        int position = (int) (Math.random() * max); // return a number from [0,1,2,...,max-1]
        return numberList.get(position);
    }

    private DataGenerator(){};
}
