package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public interface ShowAvatarsUseCaseOutput extends UseCaseOutput {

    void showAvatars(List<Avatar> avatars);
}
