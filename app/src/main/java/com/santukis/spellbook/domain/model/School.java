package com.santukis.spellbook.domain.model;

import com.santukis.spellbook.R;

public enum School {

    NONE(R.string.none, R.color.none),
    CONJURATION(R.string.conjuration, R.color.conjuration),
    EVOCATION(R.string.evocation, R.color.evocation),
    ABJURATION(R.string.abjuration, R.color.abjuration),
    ILLUSION(R.string.illusion, R.color.illusion),
    ENCHANTMENT(R.string.enchantment, R.color.enchantment),
    NECROMANCY(R.string.necromancy, R.color.necromancy),
    TRANSMUTATION(R.string.transmutation, R.color.transmutation),
    DIVINATION(R.string.divination, R.color.divination);

    private int name;
    private int color;

    School(int name, int color) {
        this.name = name;
        this.color = color;
    }

    public int getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}
