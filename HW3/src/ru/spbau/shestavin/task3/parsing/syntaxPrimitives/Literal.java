package ru.spbau.shestavin.task3.parsing.syntaxPrimitives;

public class Literal implements AbstractSyntaxPrimitive {
    private int value;

    public Literal(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
