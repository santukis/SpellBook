package com.santukis.spellbook.presentation.boundary;

import com.santukis.spellbook.domain.model.Spell;

import java.io.InputStream;

public interface SpellsController {

    void loadSpells(InputStream stream);

    void loadSpells(String name);

    void cacheSpell(Spell spell);

    void deleteSpell(Spell spell);
}
