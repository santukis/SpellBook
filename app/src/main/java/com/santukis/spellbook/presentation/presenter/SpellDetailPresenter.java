package com.santukis.spellbook.presentation.presenter;

import com.santukis.spellbook.domain.boundary.AvatarUseCaseOutput;
import com.santukis.spellbook.domain.boundary.SpellDetailUseCaseOutput;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.model.Profession;
import com.santukis.spellbook.domain.model.School;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.presentation.boundary.SpellDetailView;

import java.util.ArrayList;
import java.util.List;

public class SpellDetailPresenter implements SpellDetailUseCaseOutput, AvatarUseCaseOutput {

    private final SpellDetailView view;

    public SpellDetailPresenter(SpellDetailView view) {
        this.view = view;
    }
    @Override
    public void showSpell(Spell spell) {
        showName(spell.getName());
        showLevel(spell.getLevel());
        showSchool(spell.getSchool());
        showCasting(spell.getCastingTime());
        showRange(spell.getRange());
        showComponents(spell);
        showComponentsDescription(spell.getComponentDescription());
        showDuration(spell.getDuration());
        showDescription(spell.getDescription());
        showHigherLevels(spell.getHigherLevelsDescription());
        showProfessions(spell.getProfessions());
        setupColorViews(spell.getSchool().getColor());
    }

    @Override
    public void showError(String error) {
        if(error.equals("No characters")) {
            view.showNoCharactersMessage();
        }
    }

    @Override
    public void showAvatars(List<Avatar> avatars) {
        List<String> names = new ArrayList<>();
        for(Avatar avatar : avatars) {
            names.add(avatar.getName().concat(" (").concat(avatar.getProfession().name().concat(")")));
        }
        view.showAvatars(names);
    }

    @Override
    public void closeView() {
        view.closeView();
    }

    private void showName(String name) {
        view.showName(name);
    }

    private void showLevel(int level) {
        String textLevel = level == 0 ? "Truco" : "Nivel: " + level;
        view.showLevel(textLevel);
    }

    private void showSchool(School school) {
        view.showSchool(school.getName());
    }

    private void showCasting(String castingTime) {
        view.showCasting(castingTime);
    }

    private void showRange(int range) {
        if (range != 0) {
            view.showRange(String.valueOf(range).concat(" pies"));
        }
    }

    private void showComponents(Spell spell) {
        String components = "";

        if (spell.isVerbalComponent())
            components += "V ";

        if (spell.isSomaticComponent())
            components += "S ";

        if (spell.isMaterialComponent())
            components += "M ";

        view.showComponents(components);
    }

    private void showComponentsDescription(String description) {
        if(!description.isEmpty()) {
            description = description.substring(0, 1).toUpperCase() + description.substring(1);
        }
        view.showComponentsDescription(description);
    }

    private void showDuration(String duration) {
        view.showDuration(duration);
    }

    private void showDescription(String description) {
        view.showDescription(description);
    }

    private void showHigherLevels(String description) {
        view.showHigherLevels(description);
    }

    private void showProfessions(List<Profession> classes) {

        for(Profession profession : classes) {
            view.showProfessions(profession.getName());
        }
    }

    private void setupColorViews(int color) {
        view.setupColorViews(color);
    }
}
