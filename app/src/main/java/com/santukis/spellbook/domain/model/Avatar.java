package com.santukis.spellbook.domain.model;

public class Avatar {

    private int id;
    private String name;
    private Profession profession;

    public Avatar(String name) {
        this(name, Profession.NONE);
    }

    public Avatar(String name, Profession profession) {
        this.name = name;
        this.profession = profession;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Profession getProfession() {
        return profession;
    }

}
