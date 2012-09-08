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

/**
 * Basic lexical entity.
 * @author Dmitriy shestavin
 * @version 1.0 7 Sep 2012
 */
public class Token {
    /**
     * Represents all types of tokens.
     */
    public enum TokenType {NOTHING, DELIMETER, LITERAL, VARIABLE, FUNCTION, OPERATOR, PARENTHESIS, UNKNOWN}

    private String value;
    private TokenType tokenType;

    /**
     * Constructs Token with specified value and type.
     *
     * @param value - string containing text of token.
     * @param tokenType - type of token.
     */
    public Token(String value, TokenType tokenType) {
        this.value = value;
        this.tokenType = tokenType;
    }

    /**
     * Getter for value.
     *
     * @return value of token.
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter for value.
     *
     * @param value - value of token.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Getter for type.
     *
     * @return type of token.
     */
    public TokenType getTokenType() {
        return tokenType;
    }

    /**
     * Setter for type.
     *
     * @param tokenType - type of token.
     */
    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        Token token = (Token) o;

        if (tokenType != token.tokenType) return false;
        if (value != null ? !value.equals(token.value) : token.value != null) return false;

        return true;
    }
}
