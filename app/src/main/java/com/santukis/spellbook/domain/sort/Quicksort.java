package com.santukis.spellbook.domain.sort;

import java.util.Comparator;
import java.util.List;

public class Quicksort {

    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        sort(list, comparator, 0, list.size() - 1);
    }

    private static <T> void sort(List<T> list, Comparator<? super T> comparator, int low, int high) {

        if (high <= low) return;

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

    private static <T> void swap(List<T> list, int i, int j) {
        T aux = list.set(i, list.get(j));
        list.set(j, aux);
    }
}
