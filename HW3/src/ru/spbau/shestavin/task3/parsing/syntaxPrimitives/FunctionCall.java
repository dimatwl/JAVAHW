package ru.spbau.shestavin.task3.parsing.syntaxPrimitives;

import ru.spbau.shestavin.task3.parsing.syntaxConstructions.Expression;

import java.util.List;

public class FunctionCall implements AbstractSyntaxPrimitive {
    private String name;
    List<Expression> params;

    public FunctionCall(String name, List<Expression> params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public List<Expression> getParams() {
        return params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FunctionCall)) return false;

        FunctionCall that = (FunctionCall) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;

        return true;
    }

}
