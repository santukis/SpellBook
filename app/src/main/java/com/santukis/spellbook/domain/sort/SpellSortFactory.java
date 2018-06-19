package com.santukis.spellbook.domain.sort;

public class SpellSortFactory {

    public static SpellSort create(int criteria) {

        switch (criteria) {
            case SpellSort.NAME:
                return new NameSort();

            case SpellSort.LEVEL:
                return new LevelSort();

            case SpellSort.SCHOOL:
                return new SchoolSort();
        }

        return new NoSort();
    }
}
