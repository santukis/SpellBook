package com.santukis.spellbook.data.filter;

import java.util.List;

public interface Criteria<T> {

    List<T> filter(List<T> spells);
}
