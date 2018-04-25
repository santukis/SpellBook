package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.SpellsGateway;
import com.santukis.spellbook.domain.model.Spell;

public class CacheSpell extends UseCase<Spell, Void> {

    private final SpellsGateway gateway;

    public CacheSpell(UseCaseScheduler useCaseScheduler, SpellsGateway gateway) {
        super(useCaseScheduler);
        this.gateway = gateway;
    }

    @Override
    protected void executeUseCase(Spell spell) {
        gateway.cacheSpell(spell);
    }

    @Override
    protected void onResponse(Response<Void> response) {

    }
}
