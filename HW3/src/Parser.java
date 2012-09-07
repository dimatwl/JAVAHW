import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parser {
    private List<Statement> statements = null;

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

    public Integer evaluate() throws IOException, LexicalException {
        List<String> codeLines = readCode(fileName);
        Lexer lexer = new Lexer(codeLines);
        List<List<Token>> tokens = lexer.Tokenize();
        int i = 0;
        return i;
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
            result = new VariableAssignment(new Identifier(name, false), expression);
        } else if (line.get(1).getValue().equals("(") && line.get(2).getValue().equals(")")){ //if variable assignment (function without params is variable)
            String name = line.get(0).getValue();
            line.subList(0, 3).clear();
            Expression expression = parseExpression(line);
            result = new VariableAssignment(new Identifier(name, false), expression);
        } else if (line.get(1).getValue().equals("(")){ //if function assignment
            String name = line.get(0).getValue();
            List<Identifier> params = new LinkedList<Identifier>();
            int index = 2;
            while (! line.get(index).getValue().equals(")")) {
                if (line.get(index).getTokenType().equals(Token.TokenType.VARIABLE)) {
                    params.add(new Identifier(line.get(index).getValue(), false));
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
            result = new FunctionAssignment(new Identifier(name, false), params, expression);
        }
        return result;
    }

    private Expression parseExpression(List<Token> line) {
        Expression result = null;
        return result;
    }


}
