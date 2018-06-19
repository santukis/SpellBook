package com.santukis.spellbook.domain.sort;

import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public class NoSort implements SpellSort {

    @Override
    public List<Spell> sort(List<Spell> unorderedSpells) {
        return unorderedSpells;
    }
}
