package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Spell;

public interface SpellDetailUseCaseOutput {

    void showSpell(Spell spell);

    void showError(String error);
}
