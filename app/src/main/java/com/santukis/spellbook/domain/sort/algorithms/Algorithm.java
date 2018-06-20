package com.santukis.spellbook.domain.sort.algorithms;

import java.util.Comparator;
import java.util.List;

public interface Algorithm {

    <T> void sort(List<T> list, Comparator<? super T> comparator);

    default <T> void swap(List<T> list, int i, int j) {
        T aux = list.set(i, list.get(j));
        list.set(j, aux);
    }
}