package com.santukis.spellbook.data.filter;

import com.santukis.spellbook.domain.model.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellFilter implements Filter<Spell> {

    private List<Spell> unfilteredList;
    private List<Spell> filteredList = new ArrayList<>();

    public SpellFilter(List<Spell> unfilteredList) {
        this.unfilteredList = unfilteredList;
    }

    @Override
    public List<Spell> toList() {
        return filteredList;
    }

    @Override
    public SpellFilter filterBy(Criteria<Spell> criteria) {
        filteredList = criteria.filter(unfilteredList);
        return this;
    }

    @Override
    public SpellFilter and(Criteria<Spell> criteria) {
        filteredList = criteria.filter(filteredList);
        return this;
    }

    @Override
    public SpellFilter or(Criteria<Spell> criteria) {
        filteredList.addAll(criteria.filter(unfilteredList));
        return this;
    }
}
