package com.santukis.spellbook.data.mapper;

import com.santukis.spellbook.data.model.AvatarEntity;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.model.Profession;

import java.util.ArrayList;
import java.util.List;

public class AvatarMapper {

    public static List<Avatar> map(List<AvatarEntity> entities) {
        List<Avatar> avatars = new ArrayList<>();

        for(AvatarEntity entity : entities) {
            Avatar avatar = new Avatar(entity.getName(), Profession.valueOf(entity.getProfession()));
            avatars.add(avatar);
        }

        return avatars;
    }

    public static AvatarEntity map(Avatar avatar) {
        AvatarEntity entity = new AvatarEntity();
        entity.setName(avatar.getName());
        entity.setProfession(avatar.getProfession().name());
        return entity;
    }
}
