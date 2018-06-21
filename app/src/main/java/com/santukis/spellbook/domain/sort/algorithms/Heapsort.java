package com.santukis.spellbook.domain.sort.algorithms;

import java.util.Comparator;
import java.util.List;

public class Heapsort implements Algorithm {

    //Priority Queue example from Sedgewick R., Wayne K. pp 309 y ss;

    //Performance characteristics from Sedgewick R., Wayne K. pp 342

    //                     ORDER OF GROWTH TO SORT N ITEMS
    //      STABLE?     IN PLACE?     RUNNINN TIME    EXTRA SPACE
    //         NO          YES           n log n           1

    @Override
    public <T> void sort(List<T> list, Comparator<? super T> comparator) {

        int n = list.size();

        list.add(0, null);

        for (int k = n / 2; k >= 1; k--) {
            sort(list, comparator, k, n);
        }

        while (n > 1) {
            swap(list, 1, n--);
            sort(list, comparator, 1, n);
        }

        list.remove(0);
    }

    private <T> void sort(List<T> list, Comparator<? super T> comparator, int k, int n) {

        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && comparator.compare(list.get(j), list.get(j + 1)) < 0) j++;
            if (comparator.compare(list.get(k), list.get(j)) >= 0) break;
            swap(list, k, j);
            k = j;
        }
    }
}
