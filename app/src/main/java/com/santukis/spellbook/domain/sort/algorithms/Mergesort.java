package com.santukis.spellbook.domain.sort.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mergesort implements Algorithm {

    private static List aux;

    public <T> void sort(List<T> list, Comparator<? super T> comparator) {
        aux = new ArrayList<T>();
        sort(list, comparator, 0, list.size() - 1);
    }

    @SuppressWarnings("unchecked")
    private <T> void sort(List<T> list, Comparator<? super T> comparator, int low, int high) {

        if (high <= low) return;

        int middle = low + (high - low) / 2;

        sort(list, comparator, low, middle);
        sort(list, comparator, middle + 1, high);

        int i = low;
        int j = middle + 1;

        for(int k = low; k <= high; k++) {
            aux.add(k, list.get(k));
        }

        for (int k = low; k <= high; k++) {
            if (i > middle) list.set(k, (T) aux.get(j++));
            else if (j > high) list.set(k, (T) aux.get(i++));
            else if (comparator.compare((T) aux.get(j), (T) aux.get(i)) < 0) list.set(k, (T) aux.get(j++));
            else list.set(k, (T) aux.get(i++));
        }
    }
}
