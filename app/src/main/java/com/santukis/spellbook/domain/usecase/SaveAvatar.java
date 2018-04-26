package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.AvatarsGateway;
import com.santukis.spellbook.domain.boundary.UseCaseOutput;
import com.santukis.spellbook.domain.model.Avatar;

public class SaveAvatar extends UseCase<Avatar, Boolean> {

    private final AvatarsGateway gateway;
    private final UseCaseOutput presenter;

    public SaveAvatar(UseCaseScheduler useCaseScheduler,
                      AvatarsGateway gateway,
                      UseCaseOutput presenter) {
        super(useCaseScheduler);
        this.gateway = gateway;
        this.presenter = presenter;
    }

    @Override
    protected void executeUseCase(Avatar avatar) {

        if(gateway.saveAvatar(avatar)) {
            submitResponse(Response.success(true));

        } else {
            submitResponse(Response.error("Error saving avatar"));
        }
    }

    @Override
    protected void onResponse(Response<Boolean> response) {
        if(response.isSuccessful()) {
            presenter.closeView();

        } else {
            presenter.showError(response.getError());
        }
    }
}
