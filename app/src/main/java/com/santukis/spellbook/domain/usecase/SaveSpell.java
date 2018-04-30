package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.SpellsGateway;
import com.santukis.spellbook.domain.boundary.UseCaseOutput;
import com.santukis.spellbook.domain.model.Spell;

import java.util.List;

public class SaveSpell extends UseCase<SaveSpell.RequestValues, Boolean> {

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
    protected void executeUseCase(RequestValues requestValues) {

        if (gateway.saveSpell(requestValues.getSpell(), requestValues.getAvatars())) {
            submitResponse(Response.success(true));

        } else {
            submitResponse(Response.error("Error saving Spell"));
        }

    }

    @Override
    protected void onResponse(Response<Boolean> response) {
        if (response.isSuccessful()) {
            presenter.closeView();

        } else {
            presenter.showError(response.getError());
        }
    }

    public static class RequestValues {
        private Spell spell;
        private List<String> avatars;

        public RequestValues(Spell spell, List<String> avatars) {
            this.spell = spell;
            this.avatars = avatars;
        }

        Spell getSpell() {
            return spell;
        }

        List<String> getAvatars() {
            return avatars;
        }
    }
}
