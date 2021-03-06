package com.santukis.spellbook.presentation.presenter;

import com.santukis.spellbook.domain.boundary.ShowAvatarsUseCaseOutput;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.presentation.boundary.AvatarsView;

import java.util.List;

public class AvatarsPresenter implements ShowAvatarsUseCaseOutput {

    private final AvatarsView view;

    public AvatarsPresenter(AvatarsView view) {
        this.view = view;
    }

    @Override
    public void showAvatars(List<Avatar> avatars) {
        view.hideMessage();
        view.showAvatars(avatars);
    }

    @Override
    public void showError(String error) {
        view.showMessage();
    }
}
