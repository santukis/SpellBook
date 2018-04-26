package com.santukis.spellbook.presentation.presenter;

import com.santukis.spellbook.domain.boundary.UseCaseOutput;
import com.santukis.spellbook.presentation.boundary.BaseView;

public class AvatarCreationPresenter implements UseCaseOutput {

    private final BaseView view;

    public AvatarCreationPresenter(BaseView view) {
        this.view = view;
    }

    @Override
    public void closeView() {
        view.closeView();
    }
}
