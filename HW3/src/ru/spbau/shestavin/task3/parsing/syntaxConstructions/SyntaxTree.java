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
}
