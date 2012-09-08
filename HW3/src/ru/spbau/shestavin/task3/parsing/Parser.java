/*
* Dmitriy Shestavin
*
* Copyright (c) Dmitriy Shestavin & co, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of
* Dmitriy Shestavin & co, Inc. ("Confidential Information").  You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with Dmitriy Shestavin & co, Inc.
*
* DMITRIY SHESTAVIN & CO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
* THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
* TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
* PARTICULAR PURPOSE, OR NON-INFRINGEMENT. DMITRIY SHESTAVIN & CO SHALL NOT BE LIABLE FOR
* ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
* DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*/
package ru.spbau.shestavin.task3.parsing;

import ru.spbau.shestavin.task3.parsing.exceptions.LexicalException;
import ru.spbau.shestavin.task3.parsing.exceptions.SyntaxException;
import ru.spbau.shestavin.task3.parsing.lexic_tools.Lexer;
import ru.spbau.shestavin.task3.parsing.lexic_tools.Token;
import ru.spbau.shestavin.task3.parsing.syntaxConstructions.*;
import ru.spbau.shestavin.task3.parsing.syntaxPrimitives.FunctionCall;
import ru.spbau.shestavin.task3.parsing.syntaxPrimitives.Literal;
import ru.spbau.shestavin.task3.parsing.syntaxPrimitives.Operator;
import ru.spbau.shestavin.task3.parsing.syntaxPrimitives.Variable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Parser for simple formulas like:
 * a = -3
 * b = (3 + a) * l
 * f(x, y) = x * x
 * f (b)/10
 *
 * Support evaluation of last expression.
 *
 * @author Dmitriy shestavin
 * @version 1.0 7 Sep 2012
 */
public class Parser {
    private List<Statement> statements = null;

    /**
     * Constructs Parser object with specified name of file.
     *
     * @param fileName - name of input file with source code.
     */
    public Parser(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

    /**
     * Evaluates last expression.
     *
     * @return result of evaluation.
     */
    public Integer evaluate() throws IOException, LexicalException, SyntaxException {
        List<String> codeLines = readCode(fileName);
        Lexer lexer = new Lexer(codeLines);
        List<List<Token>> tokens = lexer.Tokenize();
        statements = parse(tokens);
        Expression toEval = parseExpression(tokens.get(tokens.size() - 1));
        return evaluate(toEval);
    }

    private Integer evaluate(Expression expression) throws SyntaxException {
        Integer result = null;
        if (expression.getTree().getAbstractSyntaxPrimitive() instanceof Literal) {
            result = ((Literal) expression.getTree().getAbstractSyntaxPrimitive()).getValue();
        } else if (expression.getTree().getAbstractSyntaxPrimitive() instanceof Variable) {
            result = evaluate((Variable) expression.getTree().getAbstractSyntaxPrimitive());
        } else if (expression.getTree().getAbstractSyntaxPrimitive() instanceof Operator) {
            Operator operator = (Operator) expression.getTree().getAbstractSyntaxPrimitive();
            Expression leftArg = new Expression(expression.getTree().getLeftChild());
            Integer leftArgVal = evaluate(leftArg);
            if (leftArgVal.equals(0) && (operator.getType().equals(Operator.OperatorType.MUL) || operator.getType().equals(Operator.OperatorType.DIV))) {
                result = 0;
            } else if (operator.getType().equals(Operator.OperatorType.UNARY_MINUS)) {
                result = operator.apply(leftArgVal);
            } else {
                Expression rightArg = new Expression(expression.getTree().getRightChild());
                Integer rightArgVal = evaluate(rightArg);
                result = operator.apply(leftArgVal, rightArgVal);
            }
        } else if (expression.getTree().getAbstractSyntaxPrimitive() instanceof FunctionCall) {
            result = evaluate((FunctionCall) expression.getTree().getAbstractSyntaxPrimitive());
        } else {
            throw new SyntaxException("Unknown syntax primitive.");
        }
        return result;
    }

    private Integer evaluate(Variable variable) throws SyntaxException {
        VariableAssignment assignment = findVariableAssignment(variable);
        if (null != assignment) {
            int indexInList = statements.indexOf(assignment);
            Statement stmt = statements.remove(indexInList);
            Integer val = evaluate(assignment.getExpression());
            statements.add(indexInList, stmt);
            return val;
        } else {
            throw new SyntaxException("Variable '" + variable.getName() + "' not defined.");
        }
    }

    private Integer evaluate(FunctionCall call) throws SyntaxException {
        FunctionAssignment assignment = findFunctionAssignment(call);
        if (null != assignment) {
            int indexInList = statements.indexOf(assignment);
            Statement stmt = statements.remove(indexInList);
            int paramNumber = 0;
            for (Expression param : call.getParams()) {
                Integer paramVal = evaluate(param);
                statements.add(new VariableAssignment(assignment.getParams().get(paramNumber), new Expression(new SyntaxTree(new Literal(paramVal)))));
                ++paramNumber;
            }
            Integer val = evaluate(assignment.getExpression());
            for (Expression param : call.getParams()) {
                statements.remove(statements.size() - 1);
            }
            statements.add(indexInList, stmt);
            return val;
        } else {
            throw new SyntaxException("Function '" + call.getName() + "' not defined.");
        }
    }

    private VariableAssignment findVariableAssignment(Variable variable) {
        for (int i = statements.size() - 1; i >= 0; --i) {
            if (statements.get(i) instanceof VariableAssignment && statements.get(i).getVariable().equals(variable)) {
                return (VariableAssignment) statements.get(i);
            }
        }
        return null;
    }

    private FunctionAssignment findFunctionAssignment(FunctionCall functionCall) {
        for (int i = statements.size() - 1; i >= 0; --i) {
            if (statements.get(i) instanceof FunctionAssignment && statements.get(i).getVariable().equals(new Variable(functionCall.getName()))) {
                return (FunctionAssignment) statements.get(i);
            }
        }
        return null;
    }

    private List<String> readCode(String fileName) throws IOException {
        List<String> lines = new ArrayList<String>();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;

        while ((line = reader.readLine()) != null) {
            lines.add(line.replaceAll("\\s", ""));
        }

        return lines;
    }

    private List<Statement> parse(List<List<Token>> tokens) throws SyntaxException {
        List<Statement> result = new LinkedList<Statement>();
        for (List<Token> line : tokens) {
            if (line.contains(new Token("=", Token.TokenType.DELIMETER))) {
                result.add(parseStatement(line));
            }
        }
        return result;
    }

    private Statement parseStatement(List<Token> line) throws SyntaxException {
        Statement result = null;
        if (line.get(1).getValue().equals("=")) { //if variable assignment
            String name = line.get(0).getValue();
            line.subList(0, 2).clear();
            Expression expression = parseExpression(line);
            result = new VariableAssignment(new Variable(name), expression);
        } else if (line.get(1).getValue().equals("(") && line.get(2).getValue().equals(")")) { //if variable assignment (function without params is variable)
            String name = line.get(0).getValue();
            line.subList(0, 3).clear();
            Expression expression = parseExpression(line);
            result = new VariableAssignment(new Variable(name), expression);
        } else if (line.get(1).getValue().equals("(")) { //if function assignment
            String name = line.get(0).getValue();
            List<Variable> params = new LinkedList<Variable>();
            int index = 2;
            while (!line.get(index).getValue().equals(")")) {
                if (line.get(index).getTokenType().equals(Token.TokenType.VARIABLE)) {
                    params.add(new Variable(line.get(index).getValue()));
                    ++index;
                    if (line.get(index).getValue().equals(")")) {
                        break;
                    } else if (line.get(index).getValue().equals(",")) {
                        ++index;
                    } else {
                        throw new SyntaxException("'(' or ',' expected. Got '" + line.get(index).getValue() + "'.");
                    }
                } else {
                    throw new SyntaxException("Variable expected. Got '" + line.get(index).getValue() + "'.");
                }
            }
            ++index;
            if (line.get(index).getValue().equals("=")) {
                ++index;
            } else {
                throw new SyntaxException("'=' expected. Got '" + line.get(index).getValue() + "'.");
            }
            line.subList(0, index).clear();
            Expression expression = parseExpression(line);
            result = new FunctionAssignment(new Variable(name), params, expression);
        }
        return result;
    }

    private Expression parseExpression(List<Token> line) throws SyntaxException {
        int index = 0;
        boolean isLeftmost = getLeftmostSubExpression(line).size() == line.size();
        if (line.size() == 0) {
            throw new SyntaxException("Empty expression detected");
        } else if (line.size() == 1) {
            if (line.get(0).getTokenType().equals(Token.TokenType.VARIABLE)) {
                return new Expression(new SyntaxTree(new Variable(line.get(0).getValue())));
            } else if (line.get(0).getTokenType().equals(Token.TokenType.LITERAL)) {
                return new Expression(new SyntaxTree(new Literal(Integer.parseInt(line.get(0).getValue()))));
            } else {
                throw new SyntaxException("Variable or literal expected. Got '" + line.get(index).getValue() + "'.");
            }
        } else if (line.get(0).getValue().equals("-u")) {
            Operator op = new Operator(line.get(0).getValue());
            List<Token> toParsing = removeOuterParentheses(line.subList(1, line.size()));
            Expression expr = parseExpression(toParsing);
            SyntaxTree tree = new SyntaxTree(op);
            tree.setLeftChild(expr.getTree());
            return new Expression(tree);
        } else if (line.get(0).getTokenType().equals(Token.TokenType.FUNCTION) && isLeftmost) {
            String funcName = line.get(0).getValue();
            int parenthesesLevel = 1;
            index += 2;
            int exprStartIndex = index;
            List<Expression> params = new LinkedList<Expression>();
            try {
                while (parenthesesLevel > 0) {
                    if (line.get(index).getValue().equals("(")) {
                        ++parenthesesLevel;
                    } else if (line.get(index).getValue().equals(")")) {
                        --parenthesesLevel;
                    }
                    if (line.get(index).getValue().equals(",") || (line.get(index).getValue().equals(")") && parenthesesLevel < 1)) {
                        int exprEndIndex = index;
                        List<Token> toParsing = removeOuterParentheses(line.subList(exprStartIndex, exprEndIndex));
                        Expression expr = parseExpression(toParsing);
                        params.add(expr);

                        ++index;
                        exprStartIndex = index;
                    }
                    ++index;
                }
            } catch (IndexOutOfBoundsException e) {
                throw new SyntaxException("Parentheses not balanced.");
            }
            FunctionCall func = new FunctionCall(funcName, params);
            return new Expression(new SyntaxTree(func));
        }

        Stack<Expression> exprStack = new Stack<Expression>();
        Stack<Operator> opStack = new Stack<Operator>();
        List<Token> tokenizedExpr = getLeftmostSubExpression(line);
        List<Token> toParsing = removeOuterParentheses(tokenizedExpr);
        Expression expr = parseExpression(toParsing);
        exprStack.push(expr);
        index += tokenizedExpr.size();
        while (index < line.size()) {
            if (line.get(index).getTokenType().equals(Token.TokenType.OPERATOR)) {
                Operator op = new Operator(line.get(index).getValue());
                ++index;
                tokenizedExpr = getLeftmostSubExpression(line.subList(index, line.size()));
                toParsing = removeOuterParentheses(tokenizedExpr);
                expr = parseExpression(toParsing);
                exprStack.push(expr);
                index += tokenizedExpr.size();
                if (opStack.isEmpty()) {
                    opStack.push(op);
                } else {
                    while (!opStack.isEmpty() && opStack.peek().getPriority() >= op.getPriority()) {
                        Expression tmp = exprStack.pop();
                        compose(exprStack, opStack);
                        exprStack.push(tmp);
                    }
                    opStack.push(op);
                }
            } else {
                throw new SyntaxException("Operator expected. Got '" + line.get(index).getValue() + "'.");
            }
        }
        while (!opStack.empty()) {
            compose(exprStack, opStack);
        }
        return exprStack.pop();
    }

    private void compose(Stack<Expression> exprStack, Stack<Operator> opStack) {
        Operator operator = opStack.pop();
        Expression right = exprStack.pop();
        Expression left = exprStack.pop();
        SyntaxTree tree = new SyntaxTree(operator);
        tree.setLeftChild(left.getTree());
        tree.setRightChild(right.getTree());
        Expression composition = new Expression(tree);
        exprStack.push(composition);
    }

    private List<Token> removeOuterParentheses(List<Token> line) {
        if (line.size() > 1 && line.get(0).getValue().equals("(") && line.get(line.size() - 1).getValue().equals(")")) {
            return line.subList(1, line.size() - 1);
        } else {
            return line;
        }
    }

    private List<Token> getLeftmostSubExpression(List<Token> line) throws SyntaxException {
        int index = 0;
        if (line.get(index).getValue().equals("-")) {
            line.get(index).setValue("-u");
            ++index;
        }
        if (line.get(index).getTokenType().equals(Token.TokenType.FUNCTION)) {
            ++index;
        }
        if (line.get(index).getTokenType().equals(Token.TokenType.VARIABLE)) {
            ++index;
        } else if (line.get(index).getTokenType().equals(Token.TokenType.LITERAL)) {
            ++index;
        } else if (line.get(index).getValue().equals("(")) {
            int parenthesesLevel = 1;
            ++index;
            try {
                while (parenthesesLevel > 0) {
                    if (line.get(index).getValue().equals("(")) {
                        ++parenthesesLevel;
                    } else if (line.get(index).getValue().equals(")")) {
                        --parenthesesLevel;
                    }
                    ++index;
                }
            } catch (IndexOutOfBoundsException e) {
                throw new SyntaxException("Parentheses not balanced.");
            }
        } else {
            throw new SyntaxException("Expression expected. Got '" + line.get(index).getValue() + "'.");
        }
        return line.subList(0, index);
    }

}
