package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.SpellsGateway;
import com.santukis.spellbook.domain.boundary.UseCaseOutput;

import java.util.List;

public class SaveSpell extends UseCase<List<String>, Boolean> {

    private final SpellsGateway gateway;
    private final UseCaseOutput presenter;

    public SaveSpell(UseCaseScheduler useCaseScheduler,
                     SpellsGateway gateway,
                     UseCaseOutput presenter) {
        super(useCaseScheduler);
        this.gateway = gateway;
        this.presenter = presenter;
    }

    @Override
    protected void executeUseCase(List<String> avatars) {

        for(String avatar : avatars) {
            if(!gateway.saveSpell(avatar)) {
                submitResponse(Response.error("Error saving Spell"));
                return;
            }
        }

        submitResponse(Response.success(true));
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
