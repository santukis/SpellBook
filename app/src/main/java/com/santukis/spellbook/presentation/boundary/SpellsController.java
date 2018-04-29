package com.santukis.spellbook.presentation.boundary;

import com.santukis.spellbook.domain.model.Spell;

import java.io.InputStream;
import java.util.List;

public interface SpellsController {

    void loadSpells(InputStream stream);

    void loadSpells(String name);

    void cacheSpell(Spell spell);

    void deleteSpell(Spell spell);

    void sort(List<Spell> spells, int criteria);
}
