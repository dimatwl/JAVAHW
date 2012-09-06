public class Literal implements Item {
    private int value;

    public Literal(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
