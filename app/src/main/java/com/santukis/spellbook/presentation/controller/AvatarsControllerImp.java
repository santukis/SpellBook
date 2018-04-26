package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.presentation.boundary.AvatarsController;

import java.util.List;

public class AvatarsControllerImp implements AvatarsController {

    private final UseCase<Void, List<Avatar>> getAvatars;

    public AvatarsControllerImp(UseCase<Void, List<Avatar>> getAvatars) {
        this.getAvatars = getAvatars;
    }


    @Override
    public void loadAvatars() {
        getAvatars.execute(null);
    }
}
