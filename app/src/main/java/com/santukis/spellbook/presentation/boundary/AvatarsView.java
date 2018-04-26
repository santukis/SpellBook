package com.santukis.spellbook.presentation.boundary;

import com.santukis.spellbook.domain.model.Avatar;

import java.util.List;

public interface AvatarsView extends BaseView {

    void showAvatars(List<Avatar> avatars);
}
