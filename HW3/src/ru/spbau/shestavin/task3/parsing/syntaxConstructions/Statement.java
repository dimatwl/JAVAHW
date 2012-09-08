package ru.spbau.shestavin.task3.parsing.syntaxConstructions;

import ru.spbau.shestavin.task3.parsing.syntaxPrimitives.Variable;

public interface Statement {
    public Variable getVariable();

    public Expression getExpression();
}
