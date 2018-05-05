package com.santukis.spellbook.presentation.controller;

import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.model.Profession;
import com.santukis.spellbook.presentation.boundary.AvatarCreationController;

public class AvatarCreationControllerImp implements AvatarCreationController {

    private final UseCase<Avatar, ?> saveAvatar;

    public AvatarCreationControllerImp(UseCase<Avatar, ?> saveAvatar) {
        this.saveAvatar = saveAvatar;
    }

    @Override
    public void saveAvatar(String name, String profession) {
        Avatar avatar = new Avatar(name, Profession.valueOf(profession.toUpperCase()));
        saveAvatar.execute(avatar);
    }
}
