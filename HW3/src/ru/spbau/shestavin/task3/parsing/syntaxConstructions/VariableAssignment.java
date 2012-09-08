package ru.spbau.shestavin.task3.parsing.syntaxConstructions;

import ru.spbau.shestavin.task3.parsing.syntaxPrimitives.Variable;

public class VariableAssignment implements Statement {
    private Variable name;
    private Expression expression;

    public VariableAssignment(Variable name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }


    @Override
    public Variable getName() {
        return name;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }
}
