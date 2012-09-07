public class Identifier implements Item{
    private String name;
    private boolean isFunction;

    public Identifier(String name, boolean function) {
        this.name = name;
        isFunction = function;
    }

    public String getName() {
        return name;
    }

    public boolean isFunction() {
        return isFunction;
    }
}
