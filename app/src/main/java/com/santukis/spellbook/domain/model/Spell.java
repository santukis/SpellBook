package com.santukis.spellbook.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Spell {

    private String name;
    private String description;
    private String atHigherLevels;
    private int range;
    private boolean verbalComponent;
    private boolean somaticComponent;
    private boolean materialComponent;
    private String componentDescription;
    private boolean isRitual;
    private boolean concentration;
    private String castingTime;
    private School school;
    private int level;
    private String duration;
    private List<Profession> professions;

    public static Spell EMPTY_SPELL = new Spell(new Builder());

    private Spell(Builder builder){
        this.name = builder.name;
        this.description = builder.description;
        this.atHigherLevels = builder.atHigherLevels;
        this.range = builder.range;
        this.verbalComponent = builder.verbalComponent;
        this.somaticComponent = builder.somaticComponent;
        this.materialComponent = builder.materialComponent;
        this.componentDescription = builder.componentDescription;
        this.isRitual = builder.isRitual;
        this.concentration = builder.concentration;
        this.castingTime = builder.castingTime;
        this.school = builder.school;
        this.level = builder.level;
        this.duration = builder.duration;
        this.professions = builder.professions;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHigherLevelsDescription() {
        return atHigherLevels;
    }

    public int getRange() {
        return range;
    }

    public boolean isVerbalComponent() {
        return verbalComponent;
    }

    public boolean isSomaticComponent() {
        return somaticComponent;
    }

    public boolean isMaterialComponent() {
        return materialComponent;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public boolean isRitual() {
        return isRitual;
    }

    public boolean isConcentrated() {
        return concentration;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public School getSchool() {
        return school;
    }

    public int getLevel() {
        return level;
    }

    public String getDuration() {
        return duration;
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public static class Builder {
        private String name = "";
        private String description = "";
        private String atHigherLevels = "";
        private int range = 0;
        private boolean verbalComponent = false;
        private boolean somaticComponent = false;
        private boolean materialComponent = false;
        private String componentDescription = "";
        private boolean isRitual = false;
        private boolean concentration = false;
        private String castingTime = "";
        private School school = School.NONE;
        private int level = 0;
        private String duration = "";
        private List<Profession> professions = new ArrayList<>();

        public Builder withNane(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder atHigherLevels(String description) {
            this.atHigherLevels = description;
            return this;
        }

        public Builder withRange(int range) {
            this.range = range;
            return this;
        }

        public Builder hasVerbalComponent(boolean verbal) {
            this.verbalComponent = verbal;
            return this;
        }

        public Builder hasSomaticComponent(boolean somatic) {
            this.somaticComponent = somatic;
            return this;
        }

        public Builder hasMaterialComponent(boolean material) {
            this.materialComponent = material;
            return this;
        }

        public Builder withComponentDescription(String description) {
            this.componentDescription = description;
            return this;
        }

        public Builder isRitual(boolean isRitual) {
            this.isRitual = isRitual;
            return this;
        }

        public Builder needsConcentration(boolean concentration) {
            this.concentration = concentration;
            return this;
        }

        public Builder withCastingTime(String castingTime) {
            this.castingTime = castingTime;
            return this;
        }

        public Builder withSchool(School school) {
            this.school = school;
            return this;
        }

        public Builder withLevel(int level) {
            this.level = level;
            return this;
        }

        public Builder withDuration(String duration) {
            this.duration = duration;
            return this;
        }

        public Builder forProfessions(List<Profession> professions) {
            this.professions = professions;
            return this;
        }

        public Spell build() {
            return new Spell(this);
        }
    }
}
