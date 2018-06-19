package com.santukis.spellbook.sort;


import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public class NameSort implements SpellSort {


    @Override
    public List<Spell> sort(List<Spell> unorderedSpells) {
        Quicksort.sort(unorderedSpells, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return  unorderedSpells;
    }
}