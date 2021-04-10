package com.app.exception;

public class NotEnoughCashException extends Exception {

    private final static String MESSAGE = "Nie posiadasz wystarczająco środków na koncie";

    public NotEnoughCashException() {
        super(MESSAGE);
    }

}
