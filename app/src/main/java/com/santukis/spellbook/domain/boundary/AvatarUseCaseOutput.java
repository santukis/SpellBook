package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public interface AvatarUseCaseOutput extends UseCaseOutput {

    void showAvatars(List<Avatar> avatars);

    void showSpellsFromAvatar(List<Spell> spells);
}
