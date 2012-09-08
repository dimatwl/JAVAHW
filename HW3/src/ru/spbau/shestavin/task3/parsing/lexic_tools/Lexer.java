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
package ru.spbau.shestavin.task3.parsing.lexic_tools;

import ru.spbau.shestavin.task3.parsing.exceptions.LexicalException;

import java.util.LinkedList;
import java.util.List;

public class Lexer {
    private int positionInLine;
    private List<String> lines;

    public Lexer(List<String> lines) {
        this.lines = lines;
    }

    public List<List<Token>> Tokenize() throws LexicalException {
        List<List<Token>> result = new LinkedList<List<Token>>();
        for (String line : lines) {
            positionInLine = 0;
            result.add(TokenizeLine(line));
        }
        return result;
    }

    private List<Token> TokenizeLine(String line) throws LexicalException {
        List<Token> result = new LinkedList<Token>();
        while (positionInLine < line.length()) {
            result.add(getNextToken(line));
        }
        return result;
    }

    private Token getNextToken(String line) throws LexicalException {
        Token result = new Token("", Token.TokenType.NOTHING);
        if (isDelimeter(line.charAt(positionInLine))) {
            result.setTokenType(Token.TokenType.DELIMETER);
            result.setValue(Character.toString(line.charAt(positionInLine)));
            ++positionInLine;
            return result;
        } else if (isOperator(line.charAt(positionInLine))) {
            result.setTokenType(Token.TokenType.OPERATOR);
            result.setValue(Character.toString(line.charAt(positionInLine)));
            ++positionInLine;
            return result;
        } else if (isParenthesis(line.charAt(positionInLine))) {
            result.setTokenType(Token.TokenType.PARENTHESIS);
            result.setValue(Character.toString(line.charAt(positionInLine)));
            ++positionInLine;
            return result;
        } else if (isDigit(line.charAt(positionInLine))) {
            result.setTokenType(Token.TokenType.LITERAL);
            StringBuilder token = new StringBuilder();
            while (positionInLine < line.length() && isDigit(line.charAt(positionInLine))) {
                token.append(line.charAt(positionInLine));
                ++positionInLine;
            }
            result.setValue(token.toString());
            return result;
        } else if (isAlpha(line.charAt(positionInLine))) {
            StringBuilder token = new StringBuilder();
            while (positionInLine < line.length() && isAlpha(line.charAt(positionInLine))) {
                token.append(line.charAt(positionInLine));
                ++positionInLine;
            }
            result.setValue(token.toString());
            if (positionInLine < line.length() && line.charAt(positionInLine) == '(') {
                result.setTokenType(Token.TokenType.FUNCTION);
            } else {
                result.setTokenType(Token.TokenType.VARIABLE);
            }
            return result;
        } else {
            result.setTokenType(Token.TokenType.UNKNOWN);
            StringBuilder token = new StringBuilder();
            while (positionInLine < line.length()) {
                token.append(line.charAt(positionInLine));
                ++positionInLine;
            }
            result.setValue(token.toString());
            throw new LexicalException(result);
        }
    }


    private boolean isDelimeter(char c) {
        return "=,".indexOf(c) != -1;
    }

    private boolean isOperator(char c) {
        return "+-*/".indexOf(c) != -1;
    }

    private boolean isParenthesis(char c) {
        return "()".indexOf(c) != -1;
    }

    private boolean isAlpha(final char c) {
        char cUpper = Character.toUpperCase(c);
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ_".indexOf(cUpper) != -1;
    }

    private boolean isDigit(final char c) {
        return "0123456789".indexOf(c) != -1;
    }
}
