package com.santukis.spellbook.data.gateway;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.santukis.spellbook.domain.boundary.SettingsGateway;
import com.santukis.spellbook.domain.model.School;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SettingsGatewayImp implements SettingsGateway {

    private static SettingsGatewayImp INSTANCE = null;

    private final SharedPreferences preferences;


    private SettingsGatewayImp(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SettingsGatewayImp getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new SettingsGatewayImp(context);
        }
        return INSTANCE;
    }

    public List<School> getFilters() {
        List<School> schools = new ArrayList<>();

        Map<String, ?> values = preferences.getAll();

        for(String key : values.keySet()) {
            if((Boolean) values.get(key)) {
                schools.add(School.valueOf(key));
            }
        }
        return schools;
    }

    @Override
    public void save(String key, Object value) {
        if(value instanceof Boolean)
            preferences.edit().putBoolean(key, (Boolean) value).apply();
    }
}
