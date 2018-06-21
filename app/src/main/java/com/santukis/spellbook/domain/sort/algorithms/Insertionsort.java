package com.santukis.spellbook.domain.sort.algorithms;

import java.util.Comparator;
import java.util.List;

public class Insertionsort implements Algorithm {

    //Performance characteristics from Sedgewick R., Wayne K. pp 342

    //                     ORDER OF GROWTH TO SORT N ITEMS
    //      STABLE?     IN PLACE?     RUNNINN TIME    EXTRA SPACE
    //        YES          YES    between n and n**2       1

    public <T> void sort(List<T> list, Comparator<? super T> comparator) {

        int n = list.size();

        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && comparator.compare(list.get(j), list.get(j - 1)) < 0; j--)
                swap(list, j, j - 1);
        }
    }
}
