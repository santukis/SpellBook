package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Spell;

public interface ShowSpellUseCaseOutput extends UseCaseOutput {

    void showSpell(Spell spell);

}
