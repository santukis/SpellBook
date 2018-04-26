package com.santukis.spellbook.domain.model;

import com.santukis.spellbook.R;

public enum Profession {

    NONE(R.string.none, 0),
    BARD(R.string.bard, R.drawable.bard),
    WARLOCK(R.string.warlock, R.drawable.warlock),
    CLERIC(R.string.cleric, R.drawable.cleric),
    DRUID(R.string.druid, R.drawable.druid),
    RANGER(R.string.ranger, R.drawable.ranger),
    SORCERER(R.string.sorcerer, R.drawable.sorcerer),
    WIZARD(R.string.wizard, R.drawable.wizard),
    PALADIN(R.string.paladin, R.drawable.paladin);

    private int name;
    private int image;

    Profession(int name, int image) {
        this.name = name;
        this.image = image;
    }

    public int getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
