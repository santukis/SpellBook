package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.domain.usecase.SaveSpell;
import com.santukis.spellbook.presentation.boundary.SpellDetailController;

import java.util.List;

public class SpellDetailControllerImp implements SpellDetailController {

    private final UseCase<Void, ?> getSpell;
    private final UseCase<Void, ?> getAvatars;
    private final UseCase<SaveSpell.RequestValues, ?> saveSpell;

    public SpellDetailControllerImp(UseCase<Void, ?> getSpell,
                                    UseCase<Void, ?> getAvatars,
                                    UseCase<SaveSpell.RequestValues, ?> saveSpell) {
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
    public void saveCachedSpell(List<String> avatarsNames) {
        saveSpell.execute(new SaveSpell.RequestValues(Spell.EMPTY_SPELL, avatarsNames));
    }
}
