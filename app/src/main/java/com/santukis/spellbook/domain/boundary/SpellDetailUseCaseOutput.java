package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Spell;

public interface SpellDetailUseCaseOutput extends UseCaseOutput {

    void showSpell(Spell spell);

}
