package ru.spbau.shestavin.task3.parsing.syntaxPrimitives;

public class Variable implements AbstractSyntaxPrimitive {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
