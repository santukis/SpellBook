package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.presentation.boundary.SpellsController;

import java.io.InputStream;
import java.util.List;

public class SpellsControllerImp implements SpellsController{


    private final UseCase<InputStream, List<Spell>> getSpells;
    private final UseCase<Spell, Void> cacheSpell;

    public SpellsControllerImp(UseCase<InputStream, List<Spell>> getSpells,
                               UseCase<Spell, Void> cacheSpell) {
        this.getSpells = getSpells;
        this.cacheSpell = cacheSpell;
    }


    @Override
    public void loadSpells(InputStream stream) {
        getSpells.execute(stream);
    }

    @Override
    public void cacheSpell(Spell spell) {
        cacheSpell.execute(spell);
    }
}
