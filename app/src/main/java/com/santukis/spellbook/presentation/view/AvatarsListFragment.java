package com.santukis.spellbook.presentation.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.santukis.spellbook.R;
import com.santukis.spellbook.data.gateway.AvatarsGatewayImp;
import com.santukis.spellbook.domain.UseCaseThreadPoolExecutor;
import com.santukis.spellbook.domain.model.Avatar;
import com.santukis.spellbook.domain.usecase.DeleteAvatar;
import com.santukis.spellbook.domain.usecase.GetAvatars;
import com.santukis.spellbook.presentation.adapters.AvatarsAdapter;
import com.santukis.spellbook.presentation.adapters.BaseViewHolder;
import com.santukis.spellbook.presentation.boundary.AvatarsController;
import com.santukis.spellbook.presentation.boundary.AvatarsView;
import com.santukis.spellbook.presentation.components.click.OnAvatarClick;
import com.santukis.spellbook.presentation.components.gestures.RecyclerItemTouchHelper;
import com.santukis.spellbook.presentation.controller.AvatarsControllerImp;
import com.santukis.spellbook.presentation.presenter.AvatarsPresenter;

import java.util.List;

public class AvatarsListFragment extends Fragment implements OnAvatarClick, AvatarsView,
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private TextView messageView;
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
                new GetAvatars(UseCaseThreadPoolExecutor.getInstance(), AvatarsGatewayImp.getInstance(getActivity()), presenter),
                new DeleteAvatar(UseCaseThreadPoolExecutor.getInstance(), AvatarsGatewayImp.getInstance(getActivity())));

        messageView = view.findViewById(R.id.tv_empty_message);

        RecyclerView recyclerView = view.findViewById(R.id.rv_spells);
        adapter = new AvatarsAdapter(getActivity());
        adapter.setOnAvatarClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.characters));
    }

    @Override
    public void onClick(Avatar avatar) {
        ((MainActivity) getActivity()).openView(SpellsListFragment.newInstance(avatar.getName()));
    }

    @Override
    public void showAvatars(List<Avatar> avatars) {
        adapter.updateAvatars(avatars);
    }

    @Override
    public void showMessage() {
        messageView.setText(R.string.no_avatars_message);
        messageView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_add_character,
                0,
                0,
                0);
        messageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMessage() {
        messageView.setVisibility(View.GONE);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof BaseViewHolder) {
            controller.deleteAvatar(adapter.getAvatar(position));
            adapter.removeAvatar(position);

            if(adapter.getItemCount() == 0) {
                showMessage();
            }
        }
    }
}
