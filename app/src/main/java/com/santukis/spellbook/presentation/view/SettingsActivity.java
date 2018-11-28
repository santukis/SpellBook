package com.santukis.spellbook.presentation.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.MenuItem;

import com.santukis.spellbook.R;

import java.util.List;


public class SettingsActivity extends PreferenceActivity {

    private AppCompatDelegate delegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDelegate().getSupportActionBar().setTitle(R.string.filter_by);
        getDelegate().getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private AppCompatDelegate getDelegate() {
        if (delegate == null) {
            delegate = AppCompatDelegate.create(this, null);
        }
        return delegate;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStackImmediate();

            } else {
                finish();
            }
        }
        return true;
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return SchoolSettingsFragment.class.getName().equals(fragmentName)
                || ProfessionSettingsFragment.class.getName().equals(fragmentName);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    public static class BaseSettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Log.d("PreferenceChange", key);
        }
    }

    public static class SchoolSettingsFragment extends BaseSettingsFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.school_preferences);
        }
    }

    public static class ProfessionSettingsFragment extends BaseSettingsFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.profession_preferences);
        }
    }
}
