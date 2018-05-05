package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.domain.usecase.SaveSpell;
import com.santukis.spellbook.domain.usecase.SortSpellsBy;
import com.santukis.spellbook.presentation.boundary.SpellsController;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class SpellsControllerImp implements SpellsController{


    private final UseCase<InputStream, ?> getSpells;
    private final UseCase<String, ?> getAvatarSpells;
    private final UseCase<SaveSpell.RequestValues, ?> saveSpell;
    private final UseCase<Spell, ?> deleteSpell;
    private final UseCase<SortSpellsBy.RequestValues, ?> sortBy;

    public SpellsControllerImp(UseCase<InputStream, ?> getSpells,
                               UseCase<String, ?> getAvatarSpells,
                               UseCase<SaveSpell.RequestValues, ?> saveSpell,
                               UseCase<Spell, ?> deleteSpell,
                               UseCase<SortSpellsBy.RequestValues, ?> sortBy) {
        this.getSpells = getSpells;
        this.getAvatarSpells = getAvatarSpells;
        this.saveSpell = saveSpell;
        this.deleteSpell = deleteSpell;
        this.sortBy = sortBy;
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
    public void saveSpell(Spell spell, String avatarName) {
        saveSpell.execute(new SaveSpell.RequestValues(spell, Collections.singletonList(avatarName)));
    }

    @Override
    public void deleteSpell(Spell spell) {
        deleteSpell.execute(spell);
    }

    @Override
    public void sort(List<Spell> spells, int criteria) {
        sortBy.execute(new SortSpellsBy.RequestValues(spells, criteria));
    }
}
