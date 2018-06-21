package com.santukis.spellbook.domain.sort.algorithms;

import com.santukis.spellbook.domain.model.Spell;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HeapsortTest {

    @Test
    public void sortShouldSortNumberList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(FakeData.unsortedIntegers));
        new Heapsort().sort(list, Integer::compareTo);

        for(int i = 0; i < list.size(); i++) {
            assertEquals(FakeData.sortedIntegers[i], list.get(i));
        }
    }

    @Test
    public void sortShouldSortStringList() {
        List<String> list = new ArrayList<>(Arrays.asList(FakeData.unsortedStrings));
        new Heapsort().sort(list, String::compareTo);

        for(int i = 0; i < list.size(); i++) {
            assertEquals(FakeData.sortedStrings[i], list.get(i));
        }
    }

    @Test
    public void sortShouldSortSpellList() {
        List<Spell> list = new ArrayList<>(Arrays.asList(FakeData.unorderedSpells));
        new Heapsort().sort(list, ((o1, o2) -> o1.getSchool().compareTo(o2.getSchool())));

        for(int i = 0; i < list.size(); i++) {
            assertEquals(FakeData.sortedSpells[i].getSchool(), list.get(i).getSchool());
        }
    }

}