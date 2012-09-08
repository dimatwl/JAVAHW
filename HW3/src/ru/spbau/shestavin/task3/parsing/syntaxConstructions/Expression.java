package ru.spbau.shestavin.task3.parsing.syntaxConstructions;

public class Expression {
    private SyntaxTree value;

    public Expression(SyntaxTree tree) {
        this.value = tree;
    }

    public SyntaxTree getTree() {
        return value;
    }
}
