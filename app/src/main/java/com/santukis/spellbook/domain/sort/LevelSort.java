package com.santukis.spellbook.domain.sort;

import com.santukis.spellbook.domain.model.Spell;

import java.util.Collections;
import java.util.List;

public class LevelSort implements SpellSort {

    @Override
    public List<Spell> sort(List<Spell> unorderedSpells) {
        Collections.sort(unorderedSpells, (o1, o2) -> {
                if(o1.getLevel() > o2.getLevel()) return 1;
                else if (o1.getLevel() == o2.getLevel()) return 0;
                return -1;
            });

        return unorderedSpells;
    }
}
