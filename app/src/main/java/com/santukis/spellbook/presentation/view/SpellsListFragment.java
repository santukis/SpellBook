package com.santukis.spellbook.presentation.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.santukis.spellbook.R;
import com.santukis.spellbook.data.gateway.SpellsGatewayImp;
import com.santukis.spellbook.domain.UseCaseDefaultScheduler;
import com.santukis.spellbook.domain.UseCaseThreadPoolExecutor;
import com.santukis.spellbook.domain.boundary.SpellsUseCaseOutput;
import com.santukis.spellbook.domain.model.Spell;
import com.santukis.spellbook.domain.usecase.CacheSpell;
import com.santukis.spellbook.domain.usecase.DeleteSpell;
import com.santukis.spellbook.domain.usecase.GetAvatarSpells;
import com.santukis.spellbook.domain.usecase.GetSpells;
import com.santukis.spellbook.presentation.adapters.BaseViewHolder;
import com.santukis.spellbook.presentation.adapters.SpellsAdapter;
import com.santukis.spellbook.presentation.boundary.SpellsController;
import com.santukis.spellbook.presentation.boundary.SpellsView;
import com.santukis.spellbook.presentation.components.click.OnSpellClick;
import com.santukis.spellbook.presentation.components.gestures.RecyclerItemTouchHelper;
import com.santukis.spellbook.presentation.components.search.TextSearch;
import com.santukis.spellbook.presentation.controller.SpellsControllerImp;
import com.santukis.spellbook.presentation.presenter.SpellsPresenter;

import java.io.IOException;
import java.util.List;

public class SpellsListFragment extends Fragment implements OnSpellClick, SpellsView,
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    private TextView messageView;
    private SpellsController controller;
    private SpellsAdapter adapter;

    public static SpellsListFragment newInstance(String avatar) {
        SpellsListFragment fragment = new SpellsListFragment();
        Bundle arguments = new Bundle();
        arguments.putString("Avatar", avatar);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lists, container, false);

        initializeViewComponents(view);

        loadSpells();

        setHasOptionsMenu(true);

        return view;
    }

    private void initializeViewComponents(View view) {
        SpellsUseCaseOutput presenter = new SpellsPresenter(this);
        controller = new SpellsControllerImp(
                new GetSpells(UseCaseDefaultScheduler.getInstance(), SpellsGatewayImp.getInstance(getActivity()), presenter),
                new GetAvatarSpells(UseCaseThreadPoolExecutor.getInstance(), SpellsGatewayImp.getInstance(getActivity()), presenter),
                new CacheSpell(UseCaseDefaultScheduler.getInstance(), SpellsGatewayImp.getInstance(getActivity())),
                new DeleteSpell(UseCaseThreadPoolExecutor.getInstance(), SpellsGatewayImp.getInstance(getActivity())));

        messageView = view.findViewById(R.id.tv_empty_message);

        RecyclerView recyclerView = view.findViewById(R.id.rv_spells);
        adapter = new SpellsAdapter(getActivity());
        adapter.setOnSpellClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    private void loadSpells() {
        try {
            if(getArguments() != null) {
                String name = getArguments().getString("Avatar", "");
                ((MainActivity) getActivity()).setActionBarTitle(name);
                controller.loadSpells(name);

            } else {
                ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.spells));
                controller.loadSpells(getResources().getAssets().open("spells.csv"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        setupSearchMenu(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setupSearchMenu(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        TextSearch textSearch = new TextSearch();
        textSearch.addObserver(adapter);
        searchView.setOnQueryTextListener(textSearch);

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                loadSpells();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                loadSpells();
                return true;
            }
        });
    }

    @Override
    public void onClick(Spell spell) {
        controller.cacheSpell(spell);
        ((MainActivity) getActivity()).openView(new SpellDetailFragment());
    }

    @Override
    public void hideMessage() {
        messageView.setVisibility(View.GONE);
    }

    @Override
    public void showMessage() {
        messageView.setText(R.string.no_spells_message);
        messageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSpells(List<Spell> spells) {
        adapter.updateSpells(spells);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof BaseViewHolder) {
            controller.deleteSpell(adapter.getSpell(position));
            adapter.removeSpell(position);

            if(adapter.getItemCount() == 0) {
                showMessage();
            }
        }
    }
}
