package ru.spbau.shestavin.task3.parsing.syntaxConstructions;

import ru.spbau.shestavin.task3.parsing.syntaxPrimitives.AbstractSyntaxPrimitive;

public class SyntaxTree {

    private AbstractSyntaxPrimitive abstractSyntaxPrimitive;

    private SyntaxTree leftChild = null;
    private SyntaxTree rightChild = null;

    public AbstractSyntaxPrimitive getAbstractSyntaxPrimitive() {
        return abstractSyntaxPrimitive;
    }

    public SyntaxTree(AbstractSyntaxPrimitive abstractSyntaxPrimitive) {
        this.abstractSyntaxPrimitive = abstractSyntaxPrimitive;
    }

    public SyntaxTree getRightChild() {
        return rightChild;
    }

    public void setRightChild(SyntaxTree rightChild) {
        this.rightChild = rightChild;
    }

    public SyntaxTree getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(SyntaxTree leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SyntaxTree)) return false;

        SyntaxTree that = (SyntaxTree) o;

        if (abstractSyntaxPrimitive != null ? !abstractSyntaxPrimitive.equals(that.abstractSyntaxPrimitive) : that.abstractSyntaxPrimitive != null)
            return false;
        if (leftChild != null ? !leftChild.equals(that.leftChild) : that.leftChild != null) return false;
        if (rightChild != null ? !rightChild.equals(that.rightChild) : that.rightChild != null) return false;

        return true;
    }
}
