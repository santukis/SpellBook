package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.presentation.boundary.AvatarsController;

import java.util.List;

public class AvatarsControllerImp implements AvatarsController {

    private final UseCase<Void, List<Avatar>> getAvatars;
    private final UseCase<String, List<Spell>> getSpellsFromAvatar;

    public AvatarsControllerImp(UseCase<Void, List<Avatar>> getAvatars,
                                UseCase<String, List<Spell>> getSpellsFromAvatar) {
        this.getAvatars = getAvatars;
        this.getSpellsFromAvatar = getSpellsFromAvatar;
    }


    @Override
    public void loadAvatars() {
        getAvatars.execute(null);
    }

    @Override
    public void loadSpellsFromAvatar(String name) {
        getSpellsFromAvatar.execute(name);
    }
}
