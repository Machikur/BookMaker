package com.app.exception;

public class MatchNotFoundException extends Exception {

    private static final String MESSAGE = "Nie znaleziono meczu";

    public MatchNotFoundException() {
        super(MESSAGE);
    }
}
