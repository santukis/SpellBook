package com.santukis.spellbook.domain.model;

import com.santukis.spellbook.R;

public enum Profession {

    NONE(R.string.none),
    BARD(R.string.bard),
    WARLOCK(R.string.warlock),
    CLERIC(R.string.cleric),
    DRUID(R.string.druid),
    RANGER(R.string.ranger),
    SORCERER(R.string.sorcerer),
    WIZARD(R.string.wizard),
    PALADIN(R.string.paladin);

    private int name;

    Profession(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }
}
