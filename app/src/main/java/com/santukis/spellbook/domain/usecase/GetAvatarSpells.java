package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.SpellsGateway;
import com.santukis.spellbook.domain.boundary.SpellsUseCaseOutput;
import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public class GetAvatarSpells extends UseCase<String, List<Spell>> {

    private final SpellsGateway gateway;
    private final SpellsUseCaseOutput presenter;


    public GetAvatarSpells(UseCaseScheduler useCaseScheduler,
                           SpellsGateway gateway,
                           SpellsUseCaseOutput presenter) {
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
            presenter.showSpells(response.getBody());

        } else {
            presenter.showError(response.getError());
        }
    }
}
