package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Spell;

import java.io.InputStream;
import java.util.List;

public interface SpellsGateway {

    List<Spell> loadSpells(InputStream stream);

    List<Spell> loadAvatarSpells(String avatarName);

    Spell loadSpell();

    boolean saveSpell(Spell spell, List<String> avatarName);

    boolean deleteSpell(Spell spell);

}
