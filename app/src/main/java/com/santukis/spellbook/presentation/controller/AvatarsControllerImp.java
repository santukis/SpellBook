package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.presentation.boundary.AvatarsController;

import java.util.List;

public class AvatarsControllerImp implements AvatarsController {

    private final UseCase<Void, List<Avatar>> getAvatars;
    private final UseCase<Avatar, Boolean> deleteAvatar;

    public AvatarsControllerImp(UseCase<Void, List<Avatar>> getAvatars,
                                UseCase<Avatar, Boolean> deleteAvatar) {
        this.getAvatars = getAvatars;
        this.deleteAvatar = deleteAvatar;
    }


    @Override
    public void loadAvatars() {
        getAvatars.execute(null);
    }

    @Override
    public void deleteAvatar(Avatar avatar) {
        deleteAvatar.execute(avatar);
    }
}
