package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public interface ShowSpellsUseCaseOutput extends UseCaseOutput {

    void showSpells(List<Spell> spells);
}
