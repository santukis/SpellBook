package com.santukis.spellbook.presentation.boundary;

import java.util.List;

public interface SpellDetailController {

    void loadSpell();

    void loadAvatars();

    void saveSpellTo(List<String> avatarsNames);
}
