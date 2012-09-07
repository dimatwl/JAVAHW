import java.util.List;

public class FunctionAssignment implements Statement {
    private Identifier name;
    List<Identifier> params;
    private Expression expression;

    public FunctionAssignment(Identifier name, List<Identifier> params, Expression expression) {
        this.name = name;
        this.params = params;
        this.expression = expression;
    }


    @Override
    public Identifier getName() {
        return name;
    }

    public List<Identifier> getParams() {
        return params;
    }

    @Override
    public Expression getExpression() {
        return expression;
    }
}


