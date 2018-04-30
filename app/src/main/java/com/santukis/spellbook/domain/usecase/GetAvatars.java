package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.AvatarsGateway;
import com.santukis.spellbook.domain.boundary.ShowAvatarsUseCaseOutput;
import com.santukis.spellbook.domain.model.Avatar;

import java.util.List;

public class GetAvatars extends UseCase<Void, List<Avatar>> {

    private final AvatarsGateway gateway;
    private final ShowAvatarsUseCaseOutput presenter;

    public GetAvatars(UseCaseScheduler useCaseScheduler,
                      AvatarsGateway gateway,
                      ShowAvatarsUseCaseOutput presenter) {
        super(useCaseScheduler);
        this.gateway = gateway;
        this.presenter = presenter;
    }

    @Override
    protected void executeUseCase(Void aVoid) {
        List<Avatar> characters = gateway.loadAvatars();

        if(characters.isEmpty()) {
            submitResponse(Response.error("No characters"));

        } else {
            submitResponse(Response.success(characters));
        }
    }

    @Override
    protected void onResponse(Response<List<Avatar>> response) {

        if(response.isSuccessful()) {
            presenter.showAvatars(response.getBody());

        } else {
            presenter.showError(response.getError());
        }
    }
}
