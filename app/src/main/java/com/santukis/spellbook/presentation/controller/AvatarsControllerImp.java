package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.presentation.boundary.AvatarsController;


public class AvatarsControllerImp implements AvatarsController {

    private final UseCase<Void, ?> getAvatars;
    private final UseCase<Avatar, ?> deleteAvatar;
    private final UseCase<Avatar, ?> saveAvatar;

    public AvatarsControllerImp(UseCase<Void, ?> getAvatars,
                                UseCase<Avatar, ?> deleteAvatar,
                                UseCase<Avatar, ?> saveAvatar) {
        this.getAvatars = getAvatars;
        this.deleteAvatar = deleteAvatar;
        this.saveAvatar = saveAvatar;
    }


    @Override
    public void loadAvatars() {
        getAvatars.execute(null);
    }

    @Override
    public void deleteAvatar(Avatar avatar) {
        deleteAvatar.execute(avatar);
    }

    @Override
    public void saveAvatar(Avatar avatar) {
        saveAvatar.execute(avatar);
    }
}
