package com.santukis.spellbook.data.local;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.santukis.spellbook.domain.model.Profession;
import com.santukis.spellbook.domain.model.School;

import java.util.ArrayList;
import java.util.List;

public class Settings {

    private static Settings INSTANCE = null;

    private static final String[] PROFESSION_KEYS = {"BARD", "WARLOCK", "CLERIC", "DRUID",
            "RANGER", "SORCERER", "WIZARD", "PALADIN"};

    private static final String[] SCHOOL_KEYS = {"ABJURATION", "DIVINATION", "CONJURATION", "ENCHANTMENT",
            "EVOCATION", "ILLUSION", "NECROMANCY", "TRANSMUTATION"};

    private final SharedPreferences preferences;


    private Settings(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Settings getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Settings(context);
        }
        return INSTANCE;
    }

    public List<School> getSchools() {
        List<School> schools = new ArrayList<>();

        for (String key : SCHOOL_KEYS) {
            if (preferences.getBoolean(key, true)) {
                schools.add(School.valueOf(key));
            }
        }
        return schools;
    }

    public List<Profession> getProfessions() {
        List<Profession> professions = new ArrayList<>();

        for (String key : PROFESSION_KEYS) {
            if (preferences.getBoolean(key, true)) {
                professions.add(Profession.valueOf(key));
            }
        }
        return professions;
    }
}
