package com.santukis.spellbook.domain.sort;

import com.santukis.spellbook.domain.sort.algorithms.Mergesort;

public class SpellSortFactory {

    public static SpellSort create(int criteria) {

        switch (criteria) {
            case SpellSort.NAME:
                return new NameSort(new Mergesort());

            case SpellSort.LEVEL:
                return new LevelSort(new Mergesort());

            case SpellSort.SCHOOL:
                return new SchoolSort(new Mergesort());
        }

        return new NoSort();
    }
}
