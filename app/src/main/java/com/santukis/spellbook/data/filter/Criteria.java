package com.santukis.spellbook.data.filter;

import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public interface Criteria<T> {

    List<T> filter(List<T> spells);
}
