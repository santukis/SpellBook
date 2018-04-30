package com.santukis.spellbook.domain.sort;

import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public interface SpellSort {

    //Criteria
    int LEVEL = 0;
    int NAME = 1;
    int SCHOOL = 2;

    List<Spell> sort(List<Spell> unorderedSpells);
}
