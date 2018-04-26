package com.santukis.spellbook.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.santukis.spellbook.domain.model.School;

@Entity(tableName = "spells", indices = {@Index("name")})
public class SpellEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "avatar_name")
    private String avatarName;

    private String name;
    private String description;

    @ColumnInfo(name = "higher_levels")
    private String atHigherLevels;
    private int range;

    @ColumnInfo(name = "verbal")
    private boolean verbalComponent;

    @ColumnInfo(name = "somatic")
    private boolean somaticComponent;

    @ColumnInfo(name = "material")
    private boolean materialComponent;

    @ColumnInfo(name = "component_description")
    private String componentDescription;
    private boolean isRitual;
    private boolean concentration;
    private String castingTime;
    private String school;
    private int level;
    private String duration;
    private String professions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAtHigherLevels() {
        return atHigherLevels;
    }

    public void setAtHigherLevels(String atHigherLevels) {
        this.atHigherLevels = atHigherLevels;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isVerbalComponent() {
        return verbalComponent;
    }

    public void setVerbalComponent(boolean verbalComponent) {
        this.verbalComponent = verbalComponent;
    }

    public boolean isSomaticComponent() {
        return somaticComponent;
    }

    public void setSomaticComponent(boolean somaticComponent) {
        this.somaticComponent = somaticComponent;
    }

    public boolean isMaterialComponent() {
        return materialComponent;
    }

    public void setMaterialComponent(boolean materialComponent) {
        this.materialComponent = materialComponent;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public boolean isRitual() {
        return isRitual;
    }

    public void setRitual(boolean ritual) {
        isRitual = ritual;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getProfessions() {
        return professions;
    }

    public void setProfessions(String professions) {
        this.professions = professions;
    }
}
