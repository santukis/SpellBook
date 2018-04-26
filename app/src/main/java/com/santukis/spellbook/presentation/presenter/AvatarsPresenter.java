package com.santukis.spellbook.presentation.presenter;

import com.santukis.spellbook.domain.boundary.AvatarUseCaseOutput;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.presentation.boundary.AvatarsView;

import java.util.List;

public class AvatarsPresenter implements AvatarUseCaseOutput {

    private final AvatarsView view;

    public AvatarsPresenter(AvatarsView view) {
        this.view = view;
    }

    @Override
    public void showAvatars(List<Avatar> avatars) {
        view.showAvatars(avatars);
    }
}
