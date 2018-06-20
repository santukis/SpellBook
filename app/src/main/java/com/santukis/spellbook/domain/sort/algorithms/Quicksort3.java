package com.santukis.spellbook.domain.sort.algorithms;

import java.util.Comparator;
import java.util.List;

public class Quicksort3 implements Algorithm {

    public <T> void sort(List<T> list, Comparator<? super T> comparator) {
        sort(list, comparator, 0, list.size() - 1);
    }

    private <T> void sort(List<T> list, Comparator<? super T> comparator, int low, int high) {

        if (high <= low) return;

        int lt = low;
        int i = low + 1;
        int gt = high;
        T pivot = list.get(low);

        while (i <= gt) {

            int cmp = comparator.compare(list.get(i), pivot);

            if (cmp < 0) swap(list, lt++, i++);
            else if (cmp > 0) swap(list, i, gt--);
            else i++;
        }

        sort(list, comparator, low, lt - 1);
        sort(list, comparator, gt + 1, high);
    }
}
