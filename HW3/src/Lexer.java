import java.util.ArrayList;
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
        return "=+-*/(),".indexOf(c) != -1;
    }

    private boolean isAlpha(final char c) {
        char cUpper = Character.toUpperCase(c);
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ_".indexOf(cUpper) != -1;
    }

    private boolean isDigit(final char c) {
        return "0123456789".indexOf(c) != -1;
    }
}
