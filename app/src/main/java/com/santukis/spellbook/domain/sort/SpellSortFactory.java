package com.santukis.spellbook.domain.sort;

import com.santukis.spellbook.domain.sort.algorithms.Quicksort;
import com.santukis.spellbook.domain.sort.algorithms.Quicksort3;

public class SpellSortFactory {

    public static SpellSort create(int criteria) {

        switch (criteria) {
            case SpellSort.NAME:
                return new NameSort(new Quicksort());

            case SpellSort.LEVEL:
                return new LevelSort(new Quicksort3());

            case SpellSort.SCHOOL:
                return new SchoolSort(new Quicksort3());
        }

        return new NoSort();
    }
}
