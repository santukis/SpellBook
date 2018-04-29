package com.santukis.spellbook.domain.usecase;

import com.santukis.spellbook.domain.Response;
import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.UseCaseScheduler;
import com.santukis.spellbook.domain.boundary.SpellsUseCaseOutput;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.domain.sort.SpellSort;
import com.santukis.spellbook.domain.sort.SpellSortFactory;

import java.util.List;

public class SortBy extends UseCase<SortBy.RequestValues, Boolean> {

    private final SpellsUseCaseOutput presenter;

    public SortBy(UseCaseScheduler useCaseScheduler, SpellsUseCaseOutput presenter) {
        super(useCaseScheduler);
        this.presenter = presenter;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        SpellSort spellSort = SpellSortFactory.create(requestValues.getCriteria());
        spellSort.sort(requestValues.getUnorderedSpells());
        submitResponse(Response.success(true));
    }

    @Override
    protected void onResponse(Response<Boolean> response) {
        if(response.isSuccessful()) {
            presenter.notifyDataHasChanged();
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
