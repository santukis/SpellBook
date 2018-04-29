package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.presentation.boundary.AvatarsController;

import java.util.List;

public class AvatarsControllerImp implements AvatarsController {

    private final UseCase<Void, List<Avatar>> getAvatars;
    private final UseCase<Avatar, Boolean> deleteAvatar;
    private final UseCase<Avatar, Boolean> saveAvatar;

    public AvatarsControllerImp(UseCase<Void, List<Avatar>> getAvatars,
                                UseCase<Avatar, Boolean> deleteAvatar,
                                UseCase<Avatar, Boolean> saveAvatar) {
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
