package com.santukis.spellbook.presentation.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.santukis.spellbook.R;
import com.santukis.spellbook.data.gateway.AvatarsGatewayImp;
import com.santukis.spellbook.domain.UseCaseThreadPoolExecutor;
import com.santukis.spellbook.domain.usecase.SaveAvatar;
import com.santukis.spellbook.presentation.boundary.AvatarCreationController;
import com.santukis.spellbook.presentation.boundary.BaseView;
import com.santukis.spellbook.presentation.controller.AvatarCreationControllerImp;
import com.santukis.spellbook.presentation.presenter.AvatarCreationPresenter;

public class AvatarCreationFragment extends Fragment implements BaseView {

    private EditText nameView;
    private TextView professionView;
    private ImageButton[] buttons;
    private ImageButton selectedButton;
    private FloatingActionButton saveButton;

    private AvatarCreationController controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avatar_creation, container, false);

        initializeViewComponents(view);
        initializeViewListeners();

        return view;
    }

    private void initializeViewComponents(View view) {
        controller = new AvatarCreationControllerImp(
                new SaveAvatar(UseCaseThreadPoolExecutor.getInstance(), AvatarsGatewayImp.getInstance(getActivity()), new AvatarCreationPresenter(this)));
        nameView = view.findViewById(R.id.et_name);
        professionView = view.findViewById(R.id.tv_profession);
        buttons = new ImageButton[]{
                view.findViewById(R.id.bt_paladin),
                view.findViewById(R.id.bt_bard),
                view.findViewById(R.id.bt_cleric),
                view.findViewById(R.id.bt_druid),
                view.findViewById(R.id.bt_warlock),
                view.findViewById(R.id.bt_wizard),
                view.findViewById(R.id.bt_sorcerer),
                view.findViewById(R.id.bt_ranger)
        };
        saveButton = view.findViewById(R.id.fab_save);
        enableSaveButton(false);
    }

    @Override
    public void closeView() {
        ((MainActivity) getActivity()).closeView();
    }

    private void initializeViewListeners() {

        nameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSaveButton(!s.toString().isEmpty() && !professionView.getText().toString().isEmpty());
            }
        });

        for(ImageButton button : buttons) {
            button.setOnClickListener(v -> {
                professionView.setText(button.getContentDescription());
                selectedButton = (ImageButton) v;
                enableSaveButton(!nameView.getText().toString().isEmpty());
            });
        }

        saveButton.setOnClickListener(v -> {
            controller.saveAvatar(nameView.getText().toString(),
                    selectedButton.getTag().toString());
        });
    }

    private void enableSaveButton(boolean isEnabled) {
        saveButton.setEnabled(isEnabled);
    }
}
