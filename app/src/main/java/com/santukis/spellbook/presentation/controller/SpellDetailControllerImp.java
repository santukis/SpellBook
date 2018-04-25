package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.presentation.boundary.SpellDetailController;

import java.util.List;

public class SpellDetailControllerImp implements SpellDetailController {

    private final UseCase<Void, Spell> getSpell;
    private final UseCase<Void, List<Avatar>> getAvatars;

    public SpellDetailControllerImp(UseCase<Void, Spell> getSpell,
                                    UseCase<Void, List<Avatar>> getAvatars) {
        this.getSpell = getSpell;
        this.getAvatars = getAvatars;
    }

    @Override
    public void loadSpell() {
        getSpell.execute(null);
    }

    @Override
    public void loadAvatars() {
        getAvatars.execute(null);
    }
}
