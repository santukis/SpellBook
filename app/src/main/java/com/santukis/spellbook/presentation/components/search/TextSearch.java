package com.santukis.spellbook.presentation.components.search;

import android.support.v7.widget.SearchView;

import java.util.Observable;


public class TextSearch extends Observable implements SearchView.OnQueryTextListener {

    ////////////SearchView.OnQueryTextListener implementation//////////////
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String currentText) {
        setChanged();
        notifyObservers(currentText);
        return true;
    }
}
