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
    public Variable getVariable() {
        return name;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VariableAssignment)) return false;

        VariableAssignment that = (VariableAssignment) o;

        if (expression != null ? !expression.equals(that.expression) : that.expression != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }
}
