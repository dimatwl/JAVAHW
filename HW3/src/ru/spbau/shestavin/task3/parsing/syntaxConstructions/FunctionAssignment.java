package ru.spbau.shestavin.task3.parsing.syntaxConstructions;

import ru.spbau.shestavin.task3.parsing.syntaxPrimitives.Variable;

import java.util.List;

public class FunctionAssignment implements Statement {
    private Variable name;
    private List<Variable> params;
    private Expression expression;

    public FunctionAssignment(Variable name, List<Variable> params, Expression expression) {
        this.name = name;
        this.params = params;
        this.expression = expression;
    }


    @Override
    public Variable getVariable() {
        return name;
    }

    public List<Variable> getParams() {
        return params;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FunctionAssignment)) return false;

        FunctionAssignment that = (FunctionAssignment) o;

        if (expression != null ? !expression.equals(that.expression) : that.expression != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;

        return true;
    }

}


