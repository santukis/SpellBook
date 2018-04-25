package com.santukis.spellbook.presentation.boundary;

import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public interface SpellsView {

    void showSpells(List<Spell> spells);
}
