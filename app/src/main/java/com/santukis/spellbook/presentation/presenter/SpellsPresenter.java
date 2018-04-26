package com.santukis.spellbook.presentation.presenter;

import com.santukis.spellbook.domain.boundary.SpellsUseCaseOutput;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.presentation.boundary.SpellsView;

import java.util.List;

public class SpellsPresenter implements SpellsUseCaseOutput {

    private final SpellsView view;

    public SpellsPresenter(SpellsView view) {
        this.view = view;
    }


    @Override
    public void showSpells(List<Spell> spells) {
        view.showSpells(spells);
    }

}
