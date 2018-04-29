package com.santukis.spellbook.presentation.boundary;

import com.santukis.spellbook.domain.model.Avatar;

public interface AvatarsController {

    void loadAvatars();

    void deleteAvatar(Avatar avatar);

    void saveAvatar(Avatar avatar);

}
