package com.santukis.spellbook.domain.model;

public class Avatar {

    private String name;
    private Profession profession;

    public Avatar(String name) {
        this(name, Profession.NONE);
    }

    public Avatar(String name, Profession profession) {
        this.name = name;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public Profession getProfession() {
        return profession;
    }

}
