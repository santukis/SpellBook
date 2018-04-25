package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public interface SpellsUseCaseOutput {

    void showSpells(List<Spell> spells);

    void showError(String error);
}
