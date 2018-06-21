package com.santukis.spellbook.domain.sort.algorithms;

import java.util.Comparator;
import java.util.List;

public class Quicksort implements Algorithm {

    //Performance characteristics from Sedgewick R., Wayne K. pp 342

    //                     ORDER OF GROWTH TO SORT N ITEMS
    //      STABLE?     IN PLACE?     RUNNINN TIME    EXTRA SPACE
    //         NO          YES           n log n          lg n

    private static final int CUTOFF = 5;

    public <T> void sort(List<T> list, Comparator<? super T> comparator) {
        sort(list, comparator, 0, list.size() - 1);
    }

    private <T> void sort(List<T> list, Comparator<? super T> comparator, int low, int high) {

        if (high <= low + CUTOFF) {
            new Insertionsort().sort(list, comparator);
            return;

        } //Sedgewick, R., Wayne, K. 2011 Algorithms. Four Edition. pp. 296

        int i = low;
        int j = high + 1;
        T pivot = list.get(low);

        while (true) {
            while (comparator.compare(list.get(++i), pivot) < 0)
                if (i == high) break;
            while (comparator.compare(pivot, list.get(--j)) < 0)
                if (j == low) break;

            if (i >= j) break;

            swap(list, i, j);
        }

        swap(list, low, j);

        sort(list, comparator, low, j - 1);
        sort(list, comparator, j + 1, high);

    }
}
