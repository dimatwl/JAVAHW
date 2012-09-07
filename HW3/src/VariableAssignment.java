public class VariableAssignment implements Statement {
    private Identifier name;
    private Expression expression;

    public VariableAssignment(Identifier name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }


    @Override
    public Identifier getName() {
        return name;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }
}
