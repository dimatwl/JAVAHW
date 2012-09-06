import java.util.List;

public class FunctionAssignment implements Statement {
    private String name;
    List<String> params;
    private SyntaxTree value;

    public FunctionAssignment(String name, List<String> params, SyntaxTree value) {
        this.name = name;
        this.params = params;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<String> getParams() {
        return params;
    }

    @Override
    public SyntaxTree getValue() {
        return value;
    }
}
