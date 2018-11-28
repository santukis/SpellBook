package com.santukis.spellbook.domain.sort;

import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.domain.sort.algorithms.Algorithm;

import java.util.List;

public class LevelSort implements SpellSort {

    private Algorithm algorithm;

    public LevelSort(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public List<Spell> sort(List<Spell> unorderedSpells) {
        algorithm.sort(unorderedSpells, (o1, o2) ->  ((Integer)o1.getLevel()).compareTo(o2.getLevel()));
        return unorderedSpells;
    }
}