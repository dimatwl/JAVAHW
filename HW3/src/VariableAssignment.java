public class VariableAssignment implements Statement {
    private String name;
    private SyntaxTree value;

    public VariableAssignment(String name, SyntaxTree value) {
        this.name = name;
        this.value = value;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public SyntaxTree getValue() {
        return value;
    }
}
