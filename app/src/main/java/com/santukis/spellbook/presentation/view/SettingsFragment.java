package com.santukis.spellbook.presentation.view;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.santukis.spellbook.R;
import com.santukis.spellbook.data.gateway.SettingsGatewayImp;
import com.santukis.spellbook.domain.UseCaseDefaultScheduler;
import com.santukis.spellbook.domain.usecase.SavePreference;
import com.santukis.spellbook.presentation.boundary.SettingsController;
import com.santukis.spellbook.presentation.controller.SettingsControllerImp;


public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    private SettingsController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        controller = new SettingsControllerImp(
                new SavePreference(UseCaseDefaultScheduler.getInstance(), SettingsGatewayImp.getInstance(getActivity()))
        );
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        controller.save(preference.getKey(), newValue);
        return true;
    }
}
