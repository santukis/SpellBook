package com.santukis.spellbook.presentation.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santukis.spellbook.R;
import com.santukis.spellbook.data.gateway.SpellsGatewayImp;
import com.santukis.spellbook.domain.UseCaseDefaultScheduler;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.domain.usecase.CacheSpell;
import com.santukis.spellbook.domain.usecase.GetSpells;
import com.santukis.spellbook.presentation.boundary.SpellsController;
import com.santukis.spellbook.presentation.boundary.SpellsView;
import com.santukis.spellbook.presentation.components.OnSpellClick;
import com.santukis.spellbook.presentation.controller.SpellsControllerImp;
import com.santukis.spellbook.presentation.presenter.SpellsPresenter;

import java.io.IOException;
import java.util.List;

public class SpellsListFragment extends Fragment implements OnSpellClick, SpellsView {

    private SpellsController spellsController;
    private SpellsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lists, container, false);

        initializeViewComponents(view);

        try {
            spellsController.loadSpells(getResources().getAssets().open("spells.csv"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void initializeViewComponents(View view) {
        spellsController = new SpellsControllerImp(
                new GetSpells(UseCaseDefaultScheduler.getInstance(), SpellsGatewayImp.getInstance(getActivity()), new SpellsPresenter(this)),
                new CacheSpell(UseCaseDefaultScheduler.getInstance(), SpellsGatewayImp.getInstance(getActivity())));

        RecyclerView recyclerView = view.findViewById(R.id.rv_spells);
        adapter = new SpellsAdapter(getActivity());
        adapter.setOnSpellClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(Spell spell) {
        spellsController.cacheSpell(spell);
        ((MainActivity) getActivity()).openView(new SpellDetailFragment());
    }

    @Override
    public void showSpells(List<Spell> spells) {
        adapter.updateSpells(spells);
    }
}
