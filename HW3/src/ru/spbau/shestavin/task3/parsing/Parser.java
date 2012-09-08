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
import java.util.*;

public class Parser {
    private List<Statement> statements = null;

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

    public Integer evaluate() throws IOException, LexicalException, SyntaxException {
        List<String> codeLines = readCode(fileName);
        Lexer lexer = new Lexer(codeLines);
        List<List<Token>> tokens = lexer.Tokenize();
        List<Statement> statements = parse(tokens);
        Expression toEval = parseExpression(tokens.get(tokens.size() - 1));
        return null;
    }

    private Integer evaluate(Expression expression) {
        if (expression.getTree().getAbstractSyntaxPrimitive() instanceof Literal) {
            return ((Literal)expression.getTree().getAbstractSyntaxPrimitive()).getValue();
        } if (expression.getTree().getAbstractSyntaxPrimitive() instanceof Variable) {
            return evaluate((Variable)expression.getTree().getAbstractSyntaxPrimitive());
        }
    }

    private Integer evaluate(Variable variable) {

    }

    private List<String> readCode(String fileName) throws IOException{
        List<String> lines = new ArrayList<String>();

        BufferedReader reader = new BufferedReader( new FileReader(fileName));
        String line = null;

        while( ( line = reader.readLine() ) != null ) {
            lines.add(line.replaceAll("\\s", ""));
        }

        return lines;
    }

    private List<Statement> parse (List<List<Token>> tokens) throws SyntaxException{
        List<Statement> result = new LinkedList<Statement>();
        for (List<Token> line : tokens) {
            if (line.contains(new Token("=", Token.TokenType.DELIMETER))) {
                parseStatement(line);
            }
        }
        return result;
    }

    private Statement parseStatement(List<Token> line) throws SyntaxException{
        Statement result = null;
        if (line.get(1).getValue().equals("=")) { //if variable assignment
            String name = line.get(0).getValue();
            line.subList(0, 2).clear();
            Expression expression = parseExpression(line);
            result = new VariableAssignment(new Variable(name), expression);
        } else if (line.get(1).getValue().equals("(") && line.get(2).getValue().equals(")")){ //if variable assignment (function without params is variable)
            String name = line.get(0).getValue();
            line.subList(0, 3).clear();
            Expression expression = parseExpression(line);
            result = new VariableAssignment(new Variable(name), expression);
        } else if (line.get(1).getValue().equals("(")){ //if function assignment
            String name = line.get(0).getValue();
            List<Variable> params = new LinkedList<Variable>();
            int index = 2;
            while (! line.get(index).getValue().equals(")")) {
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
                    throw new SyntaxException("ru.spbau.shestavin.task3.parsing.syntaxPrimitives.Variable expected. Got '" + line.get(index).getValue() + "'.");
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

    private Expression parseExpression(List<Token> line) throws SyntaxException{
        int index = 0;
        if (line.size() == 0) {
            throw new SyntaxException("Empty expression detected");
        } else if (line.size() == 1) {
            if (line.get(0).getTokenType().equals(Token.TokenType.VARIABLE)) {
                return new Expression(new SyntaxTree(new Variable(line.get(0).getValue())));
            } else if (line.get(0).getTokenType().equals(Token.TokenType.LITERAL)) {
                return new Expression(new SyntaxTree(new Literal(Integer.parseInt(line.get(0).getValue()))));
            } else {
                throw new SyntaxException("ru.spbau.shestavin.task3.parsing.syntaxPrimitives.Variable or literal expected. Got '" + line.get(index).getValue() + "'.");
            }
        } else if (line.get(0).getValue().equals("-u")) {
            Operator op = new Operator(line.get(0).getValue());
            List<Token> toParsing = removeOuterParentheses(line.subList(1, line.size()));
            Expression expr = parseExpression(toParsing);
            SyntaxTree tree = new SyntaxTree(op);
            tree.setLeftChild(expr.getTree());
            return new Expression(tree);
        } else if (line.get(0).getTokenType().equals(Token.TokenType.FUNCTION)) {
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
                    } else if (line.get(index).getValue().equals(",")) {
                        int exprEndIndex = index;
                        List<Token> toParsing = removeOuterParentheses(line.subList(exprStartIndex, exprEndIndex));
                        Expression expr = parseExpression(toParsing);
                        params.add(expr);
                        exprStartIndex = exprEndIndex;
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
                    while (! opStack.isEmpty() && opStack.peek().getPriority() >= op.getPriority()) {
                        Expression tmp = exprStack.pop();
                        compose(exprStack, opStack);
                        exprStack.push(tmp);
                    }
                    opStack.push(op);
                }
            } else {
                throw new SyntaxException("ru.spbau.shestavin.task3.parsing.syntaxPrimitives.Operator expected. Got '" + line.get(index).getValue() + "'.");
            }
        }
        while (! opStack.empty()) {
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
        if (line.size() > 1 && line.get(0).getValue().equals("(") && line.get(line.size()-1).getValue().equals(")")) {
            return line.subList(1, line.size()-1);
        } else {
            return line;
        }
    }

    private List<Token> getLeftmostSubExpression(List<Token> line) throws SyntaxException{
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
            throw new SyntaxException("ru.spbau.shestavin.task3.parsing.syntaxConstructions.Expression expected. Got '" + line.get(index).getValue() + "'.");
        }
        return line.subList(0, index);
    }

}
