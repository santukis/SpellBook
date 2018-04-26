package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public interface AvatarsGateway {

    List<Avatar> loadAvatars();

    boolean saveAvatar(Avatar avatar);
}
