package com.acdlabs;

import java.util.Arrays;
import java.util.Comparator;

public class CustomSorter {

    Comparator<String> valueComparator;

    public CustomSorter(Comparator<String> valueComparator) {
        this.valueComparator=valueComparator;
    }

    public void sort(String[][] array) {

        Arrays.sort(array, (first, second) -> {

            int firstLength = first.length;
            int secondLength = second.length;
            int min = Math.min(firstLength, secondLength);

            for (int i = 0; i < min; i++) {
                int res = valueComparator.compare(first[i], second[i]);
                if (res > 0) {
                    return 1;
                } else if (res<0){
                    return -1;
                }
            }

            return 0;
        });


    }
}
