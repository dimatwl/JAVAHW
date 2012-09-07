
public class Main {

    public static class A {
        public A() {
            String res = this.test();
            System.out.println(res);
        }

        public String test () {
            return "A";
        }
    }

    public static class B extends A {
        public B() {
            super();
        }

        @Override
        public String test() {
            return "B";
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
    }
}
