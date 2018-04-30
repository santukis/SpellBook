package com.santukis.spellbook.presentation.view;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.santukis.spellbook.R;

public class AboutFragment extends Fragment {

    private WebView aboutView;
    private WebView traductionView;

    private TextView githubView;
    private TextView nosolorolView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        initializeViewComponents(view);
        initializeViewListeners();

        return view;
    }

    private void initializeViewComponents(View view) {
        aboutView = view.findViewById(R.id.wv_about);
        traductionView = view.findViewById(R.id.wv_nosolorol_about);
        githubView = view.findViewById(R.id.tv_github_link);
        nosolorolView = view.findViewById(R.id.tv_nosolorol_link);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.about));

        aboutView.loadData(getString(R.string.about_description), "text/html", "ISO-8859-1");
        traductionView.loadData(getString(R.string.nosolorol_description), "text/html", "ISO-8859-1");
    }

    private void initializeViewListeners() {
        githubView.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.github_link))));
        });

        nosolorolView.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.nosolorl_link))));
        });
    }
}
