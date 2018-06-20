package com.santukis.spellbook.domain.sort;


import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.domain.sort.algorithms.Algorithm;
import com.santukis.spellbook.domain.sort.algorithms.Quicksort;

import java.util.List;

public class NameSort implements SpellSort {

    private Algorithm algorithm;

    public NameSort(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public List<Spell> sort(List<Spell> unorderedSpells) {
        algorithm.sort(unorderedSpells, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return  unorderedSpells;
    }
}
