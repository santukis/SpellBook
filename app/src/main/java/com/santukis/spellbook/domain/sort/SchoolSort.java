package com.santukis.spellbook.domain.sort;

import com.santukis.spellbook.domain.model.Spell;

import java.util.Collections;
import java.util.List;

public class SchoolSort implements SpellSort {


    @Override
    public void sort(List<Spell> unorderedSpells) {
        Collections.sort(unorderedSpells, (o1, o2) -> o1.getSchool().compareTo(o2.getSchool()));
    }
}
