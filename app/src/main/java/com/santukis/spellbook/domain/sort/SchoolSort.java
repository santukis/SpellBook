package com.santukis.spellbook.domain.sort;

import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public class SchoolSort implements SpellSort {


    @Override
    public List<Spell> sort(List<Spell> unorderedSpells) {
        Quicksort.sort(unorderedSpells, (o1, o2) -> o1.getSchool().compareTo(o2.getSchool()));
        return unorderedSpells;
    }
}
