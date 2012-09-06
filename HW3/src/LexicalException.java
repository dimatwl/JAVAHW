public class LexicalException extends Exception {
    private Token token;

    public LexicalException (Token token) {
        this.token = token;
    }

    @Override
    public String getMessage () {
        return "Lexical error on: " + token.getValue();
    }
}
