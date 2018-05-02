package com.santukis.spellbook.data.filter;

import com.santukis.spellbook.domain.model.Profession;
import com.santukis.spellbook.domain.model.Spell;

import java.util.ArrayList;
import java.util.List;

public class ProfessionCriteria implements Criteria<Spell> {

    private List<Profession> professions;

    public ProfessionCriteria(List<Profession> professions) {
        this.professions = professions;
    }

    public List<Spell> filter(List<Spell> unfilteredList) {
        List<Spell> filteredList = new ArrayList<>();

        if(professions.size() == Profession.values().length - 1) return unfilteredList;

        if(professions.size() == 0) return filteredList;

        for(Spell spell : unfilteredList) {
            for(Profession profession : professions) {
                if(spell.getProfessions().contains(profession))
                    filteredList.add(spell);
            }
        }
        return filteredList;
    }
}
