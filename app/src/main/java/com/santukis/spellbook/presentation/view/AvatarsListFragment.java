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
import com.santukis.spellbook.data.gateway.AvatarsGatewayImp;
import com.santukis.spellbook.domain.UseCaseThreadPoolExecutor;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.usecase.GetAvatars;
import com.santukis.spellbook.presentation.boundary.AvatarsController;
import com.santukis.spellbook.presentation.boundary.AvatarsView;
import com.santukis.spellbook.presentation.components.OnAvatarClick;
import com.santukis.spellbook.presentation.controller.AvatarsControllerImp;
import com.santukis.spellbook.presentation.presenter.AvatarsPresenter;
import com.santukis.spellbook.presentation.presenter.SpellDetailPresenter;

import java.util.List;

public class AvatarsListFragment extends Fragment implements OnAvatarClick, AvatarsView {

    private AvatarsController controller;
    private AvatarsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lists, container, false);

        initializeViewComponents(view);

        controller.loadAvatars();

        return view;
    }

    private void initializeViewComponents(View view) {
        AvatarsPresenter presenter = new AvatarsPresenter(this);
        controller = new AvatarsControllerImp(
                new GetAvatars(UseCaseThreadPoolExecutor.getInstance(), AvatarsGatewayImp.getInstance(getActivity()), presenter));

        RecyclerView recyclerView = view.findViewById(R.id.rv_spells);
        adapter = new AvatarsAdapter(getActivity());
        adapter.setOnAvatarClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(Avatar avatar) {
        ((MainActivity) getActivity()).openView(SpellsListFragment.newInstance(avatar.getName()));
    }

    @Override
    public void showAvatars(List<Avatar> avatars) {
        adapter.updateAvatars(avatars);
    }

}
