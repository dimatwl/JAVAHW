package ru.spbau.shestavin.task3.parsing.syntaxConstructions;

import ru.spbau.shestavin.task3.parsing.syntaxPrimitives.Variable;

import java.util.List;

public class FunctionAssignment implements Statement {
    private Variable name;
    List<Variable> params;
    private Expression expression;

    public FunctionAssignment(Variable name, List<Variable> params, Expression expression) {
        this.name = name;
        this.params = params;
        this.expression = expression;
    }


    @Override
    public Variable getName() {
        return name;
    }

    public List<Variable> getParams() {
        return params;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }
}


