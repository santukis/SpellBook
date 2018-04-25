package com.santukis.spellbook.data.mapper;

import android.util.Log;

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
            spell = new Spell.Builder(Integer.valueOf(tokens[0]))
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
                    .withSchool(schoolMapper(Integer.valueOf(tokens[12])))
                    .withLevel(Integer.valueOf(tokens[13]))
                    .withDuration(tokens[14])
                    .forClasses(characterClassesMapper(tokens[15]))
                    .build();
            return spell;

        } catch (NumberFormatException exception) {
            Log.e("SPELL ERROR", exception.getMessage());
            return spell;
        }
    }

    private static School schoolMapper(int index) {
        School[] schools = {
                School.NONE,
                School.CONJURATION,
                School.EVOCATION,
                School.ABJURATION,
                School.ILLUSION,
                School.ENCHANTMENT,
                School.NECROMANCY,
                School.TRANSMUTATION,
                School.DIVINATION
        };

        return schools[index];
    }

    private static List<Profession> characterClassesMapper(String text) {
        List<Profession> classes = new ArrayList<>();
        String[] tokens = text.split(",");

        for(String token : tokens) {
            Profession profession = Profession.values()[Integer.valueOf(token.trim())];
            classes.add(profession);
        }
        return classes;
    }
}
