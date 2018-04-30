package com.santukis.spellbook.presentation.boundary;

import com.santukis.spellbook.domain.model.Spell;

import java.io.InputStream;
import java.util.List;

public interface SpellsController {

    void loadSpells(InputStream stream);

    void loadSpells(String name);

    void saveSpell(Spell spell, String avatarName);

    void deleteSpell(Spell spell);

    void sort(List<Spell> spells, int criteria);
}
