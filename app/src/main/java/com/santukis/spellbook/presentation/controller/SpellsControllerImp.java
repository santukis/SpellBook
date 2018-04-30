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


    private final UseCase<InputStream, List<Spell>> getSpells;
    private final UseCase<String, List<Spell>> getAvatarSpells;
    private final UseCase<SaveSpell.RequestValues, Boolean> saveSpell;
    private final UseCase<Spell, Boolean> deleteSpell;
    private final UseCase<SortSpellsBy.RequestValues, List<Spell>> sortBy;

    public SpellsControllerImp(UseCase<InputStream, List<Spell>> getSpells,
                               UseCase<String, List<Spell>> getAvatarSpells,
                               UseCase<SaveSpell.RequestValues, Boolean> saveSpell,
                               UseCase<Spell, Boolean> deleteSpell,
                               UseCase<SortSpellsBy.RequestValues, List<Spell>> sortBy) {
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
