package com.santukis.spellbook.presentation.boundary;

public interface BaseView {

    default void closeView() {}

    default void showMessage() {}

    default void hideMessage() {}
}
