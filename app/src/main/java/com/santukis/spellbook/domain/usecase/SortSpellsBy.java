package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.ShowSpellsUseCaseOutput;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.domain.sort.SpellSort;
import com.santukis.spellbook.domain.sort.SpellSortFactory;

import java.util.List;

public class SortSpellsBy extends UseCase<SortSpellsBy.RequestValues, List<Spell>> {

    private final ShowSpellsUseCaseOutput presenter;

    public SortSpellsBy(UseCaseScheduler useCaseScheduler, ShowSpellsUseCaseOutput presenter) {
        super(useCaseScheduler);
        this.presenter = presenter;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        SpellSort spellSort = SpellSortFactory.create(requestValues.getCriteria());
        List<Spell> orderedSpells = spellSort.sort(requestValues.getUnorderedSpells());

        if(orderedSpells.isEmpty()) {
            submitResponse(Response.error("Error ordering"));

        } else {
            submitResponse(Response.success(orderedSpells));
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

    public static class RequestValues {
        private List<Spell> unorderedSpells;
        private int criteria;

        public RequestValues(List<Spell> unorderedSpells, int criteria) {
            this.unorderedSpells = unorderedSpells;
            this.criteria = criteria;
        }

        List<Spell> getUnorderedSpells() {
            return unorderedSpells;
        }

        int getCriteria() {
            return criteria;
        }
    }
}
