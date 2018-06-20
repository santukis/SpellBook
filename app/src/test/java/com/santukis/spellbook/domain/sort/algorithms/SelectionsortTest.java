package com.santukis.spellbook.domain.sort.algorithms;

import com.santukis.spellbook.domain.model.Spell;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SelectionsortTest {

    @Test
    public void sortShouldSortNumberList() {
        List<Integer> list = Arrays.asList(FakeData.unsortedIntegers);
        new Selectionsort().sort(list, Integer::compareTo);

        for(int i = 0; i < list.size(); i++) {
            assertEquals(FakeData.sortedIntegers[i], list.get(i));
        }
    }

    @Test
    public void sortShouldSortStringList() {
        List<String> list = Arrays.asList(FakeData.unsortedStrings);
        new Selectionsort().sort(list, String::compareTo);

        for(int i = 0; i < list.size(); i++) {
            assertEquals(FakeData.sortedStrings[i], list.get(i));
        }
    }

    @Test
    public void sortShouldSortSpellList() {
        List<Spell> list = Arrays.asList(FakeData.unorderedSpells);
        new Selectionsort().sort(list, ((o1, o2) -> o1.getSchool().compareTo(o2.getSchool())));

        for(int i = 0; i < list.size(); i++) {
            assertEquals(FakeData.sortedSpells[i].getSchool(), list.get(i).getSchool());
        }
    }

}