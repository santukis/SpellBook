package com.santukis.spellbook.presentation.controller;


import com.santukis.spellbook.domain.UseCase;
import com.santukis.spellbook.domain.usecase.SavePreference;
import com.santukis.spellbook.presentation.boundary.SettingsController;

public class SettingsControllerImp implements SettingsController{


    private final UseCase<SavePreference.RequestValues, Boolean> savePreference;


    public SettingsControllerImp(UseCase<SavePreference.RequestValues, Boolean> savePreference) {
        this.savePreference = savePreference;
    }

    @Override
    public void save(String key, Object value) {
        savePreference.execute(new SavePreference.RequestValues(key, value));
    }
}
