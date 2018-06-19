package com.santukis.spellbook.sort;

import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public class LevelSort implements SpellSort {

    @Override
    public List<Spell> sort(List<Spell> unorderedSpells) {
        Quicksort.sort(unorderedSpells, (o1, o2) ->  Integer.compare(o1.getLevel(), o2.getLevel()));
        return unorderedSpells;
    }
}