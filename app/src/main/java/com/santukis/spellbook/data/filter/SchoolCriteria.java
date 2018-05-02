package com.santukis.spellbook.data.filter;

import com.santukis.spellbook.domain.model.School;
import com.santukis.spellbook.domain.model.Spell;

import java.util.ArrayList;
import java.util.List;


public class SchoolCriteria implements Criteria<Spell> {

    private List<School> schools;

    public SchoolCriteria(List<School> schools) {
        this.schools = schools;
    }

    public List<Spell> filter(List<Spell> unfilteredList) {
        List<Spell> filteredList = new ArrayList<>();

        if(schools.size() == School.values().length - 1) return unfilteredList;

        if(schools.size() == 0) return filteredList;

        for(Spell spell : unfilteredList) {
            if(schools.contains(spell.getSchool())) {
                filteredList.add(spell);
            }
        }
        return filteredList;
    }
}