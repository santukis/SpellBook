package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.presentation.boundary.SpellDetailController;

import java.util.List;

public class SpellDetailControllerImp implements SpellDetailController {

    private final UseCase<Void, Spell> getSpell;
    private final UseCase<Void, List<Avatar>> getAvatars;
    private final UseCase<List<String>, Boolean> saveSpell;

    public SpellDetailControllerImp(UseCase<Void, Spell> getSpell,
                                    UseCase<Void, List<Avatar>> getAvatars,
                                    UseCase<List<String>, Boolean> saveSpell) {
        this.getSpell = getSpell;
        this.getAvatars = getAvatars;
        this.saveSpell = saveSpell;
    }

    @Override
    public void loadSpell() {
        getSpell.execute(null);
    }

    @Override
    public void loadAvatars() {
        getAvatars.execute(null);
    }

    @Override
    public void saveSpellTo(List<String> avatarsName) {
        saveSpell.execute(avatarsName);
    }
}
