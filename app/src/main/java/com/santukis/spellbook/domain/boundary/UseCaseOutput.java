package com.santukis.spellbook.domain.boundary;

public interface UseCaseOutput {

    default void closeView() {}

    default void showError(String error) {};
}
