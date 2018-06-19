package com.santukis.spellbook.domain.sort;

import com.santukis.spellbook.domain.model.School;
import com.santukis.spellbook.domain.model.Spell;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QuicksortTest {

    private Integer[] sortedIntegers = {-32, -22, 1, 3, 4, 11, 22, 23, 54, 434};
    private Integer[] unsortedIntegers = {3, 1, 54, -32, 434, 23, 22, -22, 11, 4};

    private String[] sortedStrings = {"Abadesa", "Abadía", "Balbucear", "Caballo", "Datil", "Destello", "Elefante", "Salud"};
    private String[] unsortedStrings = {"Caballo", "Abadesa", "Salud", "Abadía", "Destello", "Datil", "Balbucear", "Elefante"};

    private Spell[] sortedSpells = {
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
    private Spell[] unorderedSpells = {
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

    @Test
    public void sortShouldSortNumberList() {
        List<Integer> list = Arrays.asList(unsortedIntegers);
        Quicksort.sort(list, ((o1, o2) -> o1.compareTo(o2)));

        for(int i = 0; i < list.size(); i++) {
            assertEquals(sortedIntegers[i], list.get(i));
        }
    }

    @Test
    public void sortShouldSortStringList() {
        List<String> list = Arrays.asList(unsortedStrings);
        Quicksort.sort(list, ((o1, o2) -> o1.compareTo(o2)));

        for(int i = 0; i < list.size(); i++) {
            assertEquals(sortedStrings[i], list.get(i));
        }
    }

    @Test
    public void sortShouldSortSpellList() {
        List<Spell> list = Arrays.asList(unorderedSpells);
        Quicksort.sort(list, ((o1, o2) -> o1.getSchool().compareTo(o2.getSchool())));

        for(int i = 0; i < list.size(); i++) {
            assertEquals(sortedSpells[i].getSchool(), list.get(i).getSchool());
        }
    }

}