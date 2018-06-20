package com.santukis.spellbook.domain.sort;

import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.domain.sort.algorithms.Algorithm;

import java.util.List;

public class SchoolSort implements SpellSort {

    private Algorithm algorithm;

    public SchoolSort(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public List<Spell> sort(List<Spell> unorderedSpells) {
        algorithm.sort(unorderedSpells, (o1, o2) -> o1.getSchool().compareTo(o2.getSchool()));
        return unorderedSpells;
    }
}
