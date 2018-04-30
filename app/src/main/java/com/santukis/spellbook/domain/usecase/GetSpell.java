package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.ShowSpellUseCaseOutput;
import com.santukis.spellbook.domain.boundary.SpellsGateway;
import com.santukis.spellbook.domain.model.Spell;

public class GetSpell extends UseCase<Void, Spell> {

    private final SpellsGateway gateway;
    private final ShowSpellUseCaseOutput presenter;

    public GetSpell(UseCaseScheduler useCaseScheduler,
                    SpellsGateway gateway,
                    ShowSpellUseCaseOutput presenter) {

        super(useCaseScheduler);
        this.gateway = gateway;
        this.presenter = presenter;
    }

    @Override
    protected void executeUseCase(Void aVoid) {
        Spell spell = gateway.loadSpell();

        if (spell == Spell.EMPTY_SPELL) {
            submitResponse(Response.error("Error loading Spell"));

        } else {
            submitResponse(Response.success(spell));
        }
    }

    @Override
    protected void onResponse(Response<Spell> response) {

        if(response.isSuccessful()) {
            presenter.showSpell(response.getBody());

        } else {
            presenter.showError(response.getError());
        }
    }
}
