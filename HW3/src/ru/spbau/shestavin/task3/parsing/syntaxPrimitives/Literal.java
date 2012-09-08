package ru.spbau.shestavin.task3.parsing.syntaxPrimitives;

public class Literal implements AbstractSyntaxPrimitive {
    private int value;

    public Literal(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Literal)) return false;

        Literal literal = (Literal) o;

        if (value != literal.value) return false;

        return true;
    }

}
