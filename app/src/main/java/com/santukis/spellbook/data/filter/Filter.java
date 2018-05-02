package com.santukis.spellbook.data.filter;

import java.util.List;

public interface Filter<T> {

    List<T> filter();

    Filter<T> by(Criteria<T> criteria);

    Filter<T> and(Criteria<T> criteria);

    Filter<T> or(Criteria<T> criteria);
}
