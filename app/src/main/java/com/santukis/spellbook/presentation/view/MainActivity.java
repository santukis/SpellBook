package com.santukis.spellbook.presentation.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.santukis.spellbook.R;
import com.santukis.spellbook.data.gateway.SpellsGatewayImp;
import com.santukis.spellbook.domain.UseCaseDefaultScheduler;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.domain.usecase.CacheSpell;
import com.santukis.spellbook.domain.usecase.GetSpells;
import com.santukis.spellbook.presentation.boundary.SpellsController;
import com.santukis.spellbook.presentation.boundary.SpellsView;
import com.santukis.spellbook.presentation.controller.SpellsControllerImp;
import com.santukis.spellbook.presentation.presenter.SpellsPresenter;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnSpellClick, SpellsView{

    private SpellsController spellsController;
    private SpellsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewComponents();

        try {
            spellsController.loadSpells(getResources().getAssets().open("spells.csv"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeViewComponents() {
        spellsController = new SpellsControllerImp(
                new GetSpells(UseCaseDefaultScheduler.getInstance(), SpellsGatewayImp.getInstance(), new SpellsPresenter(this)),
                new CacheSpell(UseCaseDefaultScheduler.getInstance(), SpellsGatewayImp.getInstance()));

        RecyclerView recyclerView = findViewById(R.id.rv_spells);
        adapter = new SpellsAdapter(this);
        adapter.setOnSpellClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(Spell spell) {
        spellsController.cacheSpell(spell);
        openView(new SpellDetailFragment());
    }

    private void openView(SpellDetailFragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getTag())
                .commit();
    }

    @Override
    public void showSpells(List<Spell> spells) {
        adapter.updateSpells(spells);
    }
}
