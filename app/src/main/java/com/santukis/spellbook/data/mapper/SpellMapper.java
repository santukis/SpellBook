package com.santukis.spellbook.data.mapper;

import android.util.Log;

import com.santukis.spellbook.data.model.SpellEntity;
import com.santukis.spellbook.domain.model.Profession;
import com.santukis.spellbook.domain.model.School;
import com.santukis.spellbook.domain.model.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellMapper {

    public static Spell map(String csv) {
        Spell spell = Spell.EMPTY_SPELL;
        String[] tokens = csv.split("&");

        try {
            spell = new Spell.Builder()
                    .withNane(tokens[1])
                    .withDescription(tokens[2])
                    .atHigherLevels(tokens[3])
                    .withRange(Integer.valueOf(tokens[4]))
                    .hasVerbalComponent(tokens[5].equals("1"))
                    .hasSomaticComponent(tokens[6].equals("1"))
                    .hasMaterialComponent(tokens[7].equals("1"))
                    .withComponentDescription(tokens[8])
                    .isRitual(tokens[9].equals("1"))
                    .needsConcentration(tokens[10].equals("1"))
                    .withCastingTime(tokens[11])
                    .withSchool(School.values()[Integer.valueOf(tokens[12])])
                    .withLevel(Integer.valueOf(tokens[13]))
                    .withDuration(tokens[14])
                    .forProfessions(characterProfessionMapper(tokens[15]))
                    .build();
            return spell;

        } catch (NumberFormatException exception) {
            Log.e("SPELL ERROR", exception.getMessage());
            return spell;
        }
    }

    public static SpellEntity map(Spell spell, String avatarName) {
        SpellEntity entity = new SpellEntity();
        entity.setName(spell.getName());
        entity.setAvatarName(avatarName);
        entity.setDescription(spell.getDescription());
        entity.setAtHigherLevels(spell.getHigherLevelsDescription());
        entity.setRange(spell.getRange());
        entity.setVerbalComponent(spell.isVerbalComponent());
        entity.setSomaticComponent(spell.isSomaticComponent());
        entity.setMaterialComponent(spell.isMaterialComponent());
        entity.setComponentDescription(spell.getComponentDescription());
        entity.setRitual(spell.isRitual());
        entity.setConcentration(spell.isConcentrated());
        entity.setCastingTime(spell.getCastingTime());
        entity.setSchool(spell.getSchool().name());
        entity.setLevel(spell.getLevel());
        entity.setDuration(spell.getDuration());
        return entity;
    }

    public static List<Spell> map(List<SpellEntity> entities) {
        List<Spell> spells = new ArrayList<>();

        for(SpellEntity entity : entities) {
            Spell spell = new Spell.Builder()
                    .withNane(entity.getName())
                    .withDescription(entity.getDescription())
                    .atHigherLevels(entity.getAtHigherLevels())
                    .withRange(entity.getRange())
                    .hasVerbalComponent(entity.isVerbalComponent())
                    .hasSomaticComponent(entity.isSomaticComponent())
                    .hasMaterialComponent(entity.isMaterialComponent())
                    .withComponentDescription(entity.getComponentDescription())
                    .isRitual(entity.isRitual())
                    .needsConcentration(entity.isConcentration())
                    .withCastingTime(entity.getCastingTime())
                    .withSchool(School.valueOf(entity.getSchool()))
                    .withLevel(entity.getLevel())
                    .withDuration(entity.getDuration())
                    .forProfessions(characterProfessionMapper(entity.getProfessions()))
                    .build();
            spells.add(spell);
        }
        return spells;
    }

    private static List<Profession> characterProfessionMapper(String text) {
        List<Profession> classes = new ArrayList<>();
        String[] tokens = text.split(",");

        for(String token : tokens) {
            Profession profession = Profession.values()[Integer.valueOf(token.trim())];
            classes.add(profession);
        }
        return classes;
    }
}
