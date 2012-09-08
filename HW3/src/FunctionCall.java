import java.util.List;

public class FunctionCall implements Item {
    private String name;
    List<Expression> params;

    public FunctionCall(String name, List<Expression> params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public List<Expression> getParams() {
        return params;
    }
}
