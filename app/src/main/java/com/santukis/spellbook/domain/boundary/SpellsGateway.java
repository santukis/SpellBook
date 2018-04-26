package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Spell;

import java.io.InputStream;
import java.util.List;

public interface SpellsGateway {

    List<Spell> loadSpells(InputStream stream);

    Spell loadSpell();

    void cacheSpell(Spell spell);

    boolean saveSpell(String avatarName);

}
