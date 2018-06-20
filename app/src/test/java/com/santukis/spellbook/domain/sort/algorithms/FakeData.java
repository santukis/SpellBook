package com.santukis.spellbook.domain.sort.algorithms;

import com.santukis.spellbook.domain.model.School;
import com.santukis.spellbook.domain.model.Spell;

public class FakeData {

    static Integer[] sortedIntegers = {-32, -22, 1, 3, 4, 11, 22, 23, 54, 434};
    static Integer[] unsortedIntegers = {3, 1, 54, -32, 434, 23, 22, -22, 11, 4};

    static String[] sortedStrings = {"Abadesa", "Abadía", "Balbucear", "Caballo", "Datil", "Destello", "Elefante", "Salud"};
    static String[] unsortedStrings = {"Caballo", "Abadesa", "Salud", "Abadía", "Destello", "Datil", "Balbucear", "Elefante"};

    static Spell[] sortedSpells = {
            new Spell.Builder().withSchool(School.CONJURATION).build(),
            new Spell.Builder().withSchool(School.CONJURATION).build(),
            new Spell.Builder().withSchool(School.EVOCATION).build(),
            new Spell.Builder().withSchool(School.ABJURATION).build(),
            new Spell.Builder().withSchool(School.ABJURATION).build(),
            new Spell.Builder().withSchool(School.ILLUSION).build(),
            new Spell.Builder().withSchool(School.ENCHANTMENT).build(),
            new Spell.Builder().withSchool(School.NECROMANCY).build(),
            new Spell.Builder().withSchool(School.NECROMANCY).build(),
            new Spell.Builder().withSchool(School.TRANSMUTATION).build(),
            new Spell.Builder().withSchool(School.TRANSMUTATION).build(),
            new Spell.Builder().withSchool(School.DIVINATION).build()
    };
    static Spell[] unorderedSpells = {
            new Spell.Builder().withSchool(School.TRANSMUTATION).build(),
            new Spell.Builder().withSchool(School.CONJURATION).build(),
            new Spell.Builder().withSchool(School.NECROMANCY).build(),
            new Spell.Builder().withSchool(School.ABJURATION).build(),
            new Spell.Builder().withSchool(School.ILLUSION).build(),
            new Spell.Builder().withSchool(School.CONJURATION).build(),
            new Spell.Builder().withSchool(School.ABJURATION).build(),
            new Spell.Builder().withSchool(School.DIVINATION).build(),
            new Spell.Builder().withSchool(School.ENCHANTMENT).build(),
            new Spell.Builder().withSchool(School.NECROMANCY).build(),
            new Spell.Builder().withSchool(School.EVOCATION).build(),
            new Spell.Builder().withSchool(School.TRANSMUTATION).build()
    };
}
