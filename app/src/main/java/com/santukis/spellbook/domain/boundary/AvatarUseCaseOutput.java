package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Avatar;

import java.util.List;

public interface AvatarUseCaseOutput {

    void showAvatars(List<Avatar> characters);

    void showError(String error);
}
