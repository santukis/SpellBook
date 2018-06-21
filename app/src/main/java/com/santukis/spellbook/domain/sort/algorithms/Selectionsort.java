package com.santukis.spellbook.domain.sort.algorithms;

import java.util.Comparator;
import java.util.List;

public class Selectionsort implements Algorithm {

    //Performance characteristics from Sedgewick R., Wayne K. pp 342

    //                     ORDER OF GROWTH TO SORT N ITEMS
    //      STABLE?     IN PLACE?     RUNNINN TIME    EXTRA SPACE
    //         NO          YES           n**2              1

    public <T> void sort(List<T> list, Comparator<? super T> comparator) {

        int n = list.size();

        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(list.get(j), list.get(min)) < 0)
                    min = j;
            }
            swap(list, i, min);
        }
    }
}
