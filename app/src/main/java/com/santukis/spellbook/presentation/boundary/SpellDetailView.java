package com.santukis.spellbook.presentation.boundary;


import java.util.List;

public interface SpellDetailView extends BaseView {

    void showName(String name);

    void showLevel(String level);

    void showSchool(int schoolId);

    void showCasting(String time);

    void showRange(String range);

    void showComponents(String components);

    void showComponentsDescription(String description);

    void showDuration(String duration);

    void showDescription(String description);

    void showHigherLevels(String description);

    void showProfessions(int classId);

    void setupColorViews(int color);

    void showAvatars(List<String> names);

    void showNoCharactersMessage();
}
