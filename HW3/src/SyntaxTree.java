public class SyntaxTree {
    Item item;

    SyntaxTree leftChild = null;
    SyntaxTree rightChild = null;

    public SyntaxTree(Item item) {
        this.item = item;
    }

    public SyntaxTree getRightChild() {
        return rightChild;
    }

    public void setRightChild(SyntaxTree rightChild) {
        this.rightChild = rightChild;
    }

    public SyntaxTree getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(SyntaxTree leftChild) {
        this.leftChild = leftChild;
    }
}
