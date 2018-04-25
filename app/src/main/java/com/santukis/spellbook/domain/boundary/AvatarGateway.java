package com.santukis.spellbook.domain.boundary;

import com.santukis.spellbook.domain.model.Avatar;

import java.util.List;

public interface AvatarGateway {

    List<Avatar> loadCharacters();
}
