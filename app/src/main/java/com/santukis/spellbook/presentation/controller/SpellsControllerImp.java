package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.presentation.boundary.SpellsController;

import java.io.InputStream;
import java.util.List;

public class SpellsControllerImp implements SpellsController{


    private final UseCase<InputStream, List<Spell>> getSpells;
    private final UseCase<String, List<Spell>> getAvatarSpells;
    private final UseCase<Spell, Void> cacheSpell;
    private final UseCase<Spell, Boolean> deleteSpell;

    public SpellsControllerImp(UseCase<InputStream, List<Spell>> getSpells,
                               UseCase<String, List<Spell>> getAvatarSpells,
                               UseCase<Spell, Void> cacheSpell,
                               UseCase<Spell, Boolean> deleteSpell) {
        this.getSpells = getSpells;
        this.getAvatarSpells = getAvatarSpells;
        this.cacheSpell = cacheSpell;
        this.deleteSpell = deleteSpell;
    }

    @Override
    public void loadSpells(InputStream stream) {
        getSpells.execute(stream);
    }

    @Override
    public void loadSpells(String name) {
        getAvatarSpells.execute(name);
    }

    @Override
    public void cacheSpell(Spell spell) {
        cacheSpell.execute(spell);
    }

    @Override
    public void deleteSpell(Spell spell) {
        deleteSpell.execute(spell);
    }
}
