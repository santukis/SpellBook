package com.santukis.spellbook.data.filter;

import com.santukis.spellbook.domain.model.School;
import com.santukis.spellbook.domain.model.Spell;

import java.util.ArrayList;
import java.util.List;


public class SchoolFilter {


    public static List<Spell> filter(List<Spell> unfilteredList, List<School> by) {
        List<Spell> filteredList = new ArrayList<>();

        for(Spell spell : unfilteredList) {
            if(by.contains(spell.getSchool())) {
                filteredList.add(spell);
            }
        }
        return filteredList;
    }
}
