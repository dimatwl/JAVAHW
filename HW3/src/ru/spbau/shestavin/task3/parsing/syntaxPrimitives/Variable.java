package ru.spbau.shestavin.task3.parsing.syntaxPrimitives;

public class Variable implements AbstractSyntaxPrimitive {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable)) return false;

        Variable variable = (Variable) o;

        if (name != null ? !name.equals(variable.name) : variable.name != null) return false;

        return true;
    }
}
