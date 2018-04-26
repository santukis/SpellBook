package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.AvatarUseCaseOutput;
import com.santukis.spellbook.domain.boundary.AvatarsGateway;
import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public class GetAvatarSpells extends UseCase<String, List<Spell>> {

    private final AvatarsGateway gateway;
    private final AvatarUseCaseOutput presenter;


    public GetAvatarSpells(UseCaseScheduler useCaseScheduler,
                           AvatarsGateway gateway,
                           AvatarUseCaseOutput presenter) {
        super(useCaseScheduler);
        this.gateway = gateway;
        this.presenter = presenter;
    }

    @Override
    protected void executeUseCase(String name) {
        List<Spell> spells = gateway.loadAvatarSpells(name);

        if(spells.isEmpty()) {
            submitResponse(Response.error("No spells"));

        } else {
            submitResponse(Response.success(spells));
        }
    }

    @Override
    protected void onResponse(Response<List<Spell>> response) {

        if(response.isSuccessful()) {
            presenter.showSpellsFromAvatar(response.getBody());

        } else {
            presenter.showError(response.getError());
        }
    }
}
