package ru.spbau.shestavin.task3.parsing.lexic_tools;

public class Token {
    public enum TokenType {NOTHING, DELIMETER, LITERAL, VARIABLE, FUNCTION, OPERATOR, PARENTHESIS, UNKNOWN}

    private String value;
    private TokenType tokenType;

    public Token(String value, TokenType tokenType) {
        this.value = value;
        this.tokenType = tokenType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

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
