package ru.spbau.shestavin.task3.parsing.syntaxPrimitives;

import ru.spbau.shestavin.task3.parsing.exceptions.SyntaxException;

public class Operator implements AbstractSyntaxPrimitive {

    public enum OperatorType {UNARY_MINUS, MINUS, PLUS, MUL, DIV}

    private OperatorType type;

    public Operator(String value) throws SyntaxException {
        if (value.equals("+")) {
            type = OperatorType.PLUS;
        } else if (value.equals("-")) {
            type = OperatorType.MINUS;
        } else if (value.equals("*")) {
            type = OperatorType.MUL;
        } else if (value.equals("/")) {
            type = OperatorType.DIV;
        } else if (value.equals("-u")) {
            type = OperatorType.UNARY_MINUS;
        } else {
            throw new SyntaxException("Unsupported operator. Got '" + value + "'.");
        }
    }

    public OperatorType getType() {
        return type;
    }

    public int getPriority() {
        if (type.equals(OperatorType.PLUS) || type.equals(OperatorType.MINUS)) {
            return 0;
        } else if (type.equals(OperatorType.MUL) || type.equals(OperatorType.DIV)) {
            return 1;
        } else {
            return 2;
        }
    }

    public Integer performOperation(Integer leftArg, Integer rightArg) throws SyntaxException {
        if (type.equals(OperatorType.PLUS)) {
            return leftArg + rightArg;
        } else if (type.equals(OperatorType.MINUS)) {
            return leftArg - rightArg;
        } else if (type.equals(OperatorType.MUL)) {
            return leftArg * rightArg;
        } else if (type.equals(OperatorType.DIV)) {
            return leftArg / rightArg;
        } else {
            throw new SyntaxException("Unary minus can't be applied to two arguments.");
        }
    }

    public Integer performOperation(Integer arg) throws SyntaxException {
        if (type.equals(OperatorType.UNARY_MINUS)) {
            return -arg;
        } else {
            throw new SyntaxException("Binary operator can't be applied to one argument.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operator)) return false;

        Operator operator = (Operator) o;

        if (type != operator.type) return false;

        return true;
    }

}
