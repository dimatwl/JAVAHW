package ru.spbau.shestavin.task3.parsing.exceptions;

public class SyntaxException extends Exception {
    private String message;

    public SyntaxException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
