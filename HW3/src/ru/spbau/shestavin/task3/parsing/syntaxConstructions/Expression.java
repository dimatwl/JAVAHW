package ru.spbau.shestavin.task3.parsing.syntaxConstructions;

public class Expression {
    private SyntaxTree tree;

    public Expression(SyntaxTree tree) {
        this.tree = tree;
    }

    public SyntaxTree getTree() {
        return tree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expression)) return false;

        Expression that = (Expression) o;

        if (tree != null ? !tree.equals(that.tree) : that.tree != null) return false;

        return true;
    }
}
