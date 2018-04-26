package com.santukis.spellbook.presentation.view;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.santukis.spellbook.R;
import com.santukis.spellbook.data.gateway.AvatarsGatewayImp;
import com.santukis.spellbook.data.gateway.SpellsGatewayImp;
import com.santukis.spellbook.domain.UseCaseDefaultScheduler;
import com.santukis.spellbook.domain.UseCaseThreadPoolExecutor;
import com.santukis.spellbook.domain.usecase.GetAvatars;
import com.santukis.spellbook.domain.usecase.GetSpell;
import com.santukis.spellbook.domain.usecase.SaveSpell;
import com.santukis.spellbook.presentation.boundary.SpellDetailController;
import com.santukis.spellbook.presentation.boundary.SpellDetailView;
import com.santukis.spellbook.presentation.controller.SpellDetailControllerImp;
import com.santukis.spellbook.presentation.presenter.SpellDetailPresenter;

import java.util.ArrayList;
import java.util.List;

public class SpellDetailFragment extends Fragment implements SpellDetailView {

    private CoordinatorLayout mainLayout;
    private TextView nameView;
    private TextView schoolView;
    private TextView castingTitleView;
    private TextView castingView;
    private TextView rangeTitleView;
    private TextView rangeView;
    private TextView componentsTitleView;
    private TextView componentsView;
    private TextView componentsDescriptionTitleView;
    private TextView componentsDescriptionView;
    private TextView durationTitleView;
    private TextView durationView;
    private WebView descriptionView;
    private TextView higherLevelsTitleView;
    private WebView higherLevelsView;
    private TextView classesView;
    private FloatingActionButton addSpellButton;

    private SpellDetailController controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_spell_detail, container, false);

        initializeViewComponents(view);
        initializeViewListeners();

        controller.loadSpell();

        return view;
    }

    private void initializeViewComponents(View view) {
        SpellDetailPresenter presenter = new SpellDetailPresenter(this);
        controller = new SpellDetailControllerImp(
                new GetSpell(UseCaseDefaultScheduler.getInstance(), SpellsGatewayImp.getInstance(getActivity()), presenter),
                new GetAvatars(UseCaseThreadPoolExecutor.getInstance(), AvatarsGatewayImp.getInstance(getActivity().getApplicationContext()), presenter),
                new SaveSpell(UseCaseThreadPoolExecutor.getInstance(), SpellsGatewayImp.getInstance(getActivity()), presenter));

        mainLayout = view.findViewById(R.id.cl_main);
        nameView = view.findViewById(R.id.tv_name);
        schoolView = view.findViewById(R.id.tv_school);
        castingTitleView = view.findViewById(R.id.tv_casting_time_title);
        castingView = view.findViewById(R.id.tv_casting_time);
        rangeTitleView = view.findViewById(R.id.tv_range_title);
        rangeView = view.findViewById(R.id.tv_range);
        componentsTitleView = view.findViewById(R.id.tv_components_title);
        componentsView = view.findViewById(R.id.tv_components);
        componentsDescriptionTitleView = view.findViewById(R.id.tv_component_description_title);
        componentsDescriptionView = view.findViewById(R.id.tv_component_description);
        durationTitleView = view.findViewById(R.id.tv_duration_title);
        durationView = view.findViewById(R.id.tv_duration);
        descriptionView = view.findViewById(R.id.wv_description);
        higherLevelsTitleView = view.findViewById(R.id.tv_higher_levels_title);
        higherLevelsView = view.findViewById(R.id.wv_higher_levels);
        classesView = view.findViewById(R.id.tv_classes);
        addSpellButton = view.findViewById(R.id.fab_add_spell);
    }

    private void initializeViewListeners() {
        addSpellButton.setOnClickListener((v -> controller.loadAvatars()));
    }

    @Override
    public void closeView() {
        ((MainActivity) getActivity()).closeView();
    }

    @Override
    public void showAvatars(List<String> names) {
        List<String> selectedAvatars = new ArrayList<>();
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.avatar_selection_title)
                .setMultiChoiceItems(names.toArray(new String[names.size()]), null,
                        (dialog, which, isChecked) -> {
                        String completeName = names.get(which);
                        String name = completeName.substring(0, completeName.indexOf("(") - 1);
                            if(isChecked) {
                                selectedAvatars.add(name);
                            } else {
                                selectedAvatars.remove(name);
                            }
                            Log.d("AVATARS", String.valueOf(selectedAvatars.size()));
                        })
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    controller.saveSpellTo(selectedAvatars);
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();

    }

    @Override
    public void showNoCharactersMessage() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.no_avatars_title)
                .setMessage(R.string.no_avatars_message)
                .setPositiveButton(android.R.string.ok, ((dialog, which) -> {
                    ((MainActivity) getActivity()).openView(new AvatarCreationFragment());
                }))
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    @Override
    public void showName(String name) {
        nameView.setText(name);
    }

    @Override
    public void showLevel(String level) {
        schoolView.setText(level);
    }

    @Override
    public void showSchool(int schoolId) {
        String text = schoolView.getText().toString();
        text = text.concat(", ").concat(getString(schoolId));
        schoolView.setText(text);
    }

    @Override
    public void showCasting(String time) {
        castingView.setText(time);
    }

    @Override
    public void showRange(String range) {
        rangeView.setText(range);
    }

    @Override
    public void showComponents(String components) {
        componentsView.setText(components);
    }

    @Override
    public void showComponentsDescription(String description) {
        if (description.isEmpty()) {
            componentsDescriptionTitleView.setVisibility(View.GONE);
            componentsDescriptionView.setVisibility(View.GONE);

        } else {
            componentsDescriptionView.setText(description);
        }
    }

    @Override
    public void showDuration(String duration) {
        durationView.setText(duration);
    }

    @Override
    public void showDescription(String description) {
        description = "<html><head><style>body {color:#444444; line-height: 22px} br {display: block; content: \"\"; margin-top: 10px;}</style></head><body align=\"justify\";>"
                .concat(description)
                .concat("</body></html>");
        descriptionView.loadDataWithBaseURL(null, description, "text/html", "ISO-8859-1", null);
    }

    @Override
    public void showHigherLevels(String description) {
        if (description.isEmpty()) {
            higherLevelsView.setVisibility(View.GONE);
            higherLevelsTitleView.setVisibility(View.GONE);

        } else {
            description = ("<html><head><style>body {color:#444444; line-height: 22px} br {display: block; content: \"\"; margin-top: 10px;}</style></head><body align=\"justify\";>")
                    .concat(description)
                    .concat("</body></html>");
            higherLevelsView.loadDataWithBaseURL(null, description, "text/html", "ISO-8859-1", null);
        }
    }

    @Override
    public void showProfessions(int classId) {
        String classes = classesView.getText().toString();

        if (!classes.isEmpty()) {
            classes = classes.concat(", ");
        }

        classesView.setText(classes.concat(getString(classId)));
    }

    @Override
    public void setupColorViews(int color) {
        mainLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), color));
        nameView.setTextColor(ContextCompat.getColor(getActivity(), color));
        castingTitleView.setTextColor(ContextCompat.getColor(getActivity(), color));
        rangeTitleView.setTextColor(ContextCompat.getColor(getActivity(), color));
        componentsTitleView.setTextColor(ContextCompat.getColor(getActivity(), color));
        durationTitleView.setTextColor(ContextCompat.getColor(getActivity(), color));
    }
}
