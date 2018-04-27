package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.SpellsGateway;
import com.santukis.spellbook.domain.model.Spell;

public class DeleteSpell extends UseCase<Spell, Boolean> {

    private final SpellsGateway gateway;

    public DeleteSpell(UseCaseScheduler useCaseScheduler, SpellsGateway gateway) {
        super(useCaseScheduler);
        this.gateway = gateway;
    }

    @Override
    protected void executeUseCase(Spell spell) {
        gateway.deleteSpell(spell);
    }

    @Override
    protected void onResponse(Response<Boolean> response) {

    }
}
