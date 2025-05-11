package uniquindio.edu.co.TallerArboles2.model;

public class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int item) {
        this.data = item;
        this.left = null;
        this.right = null;
    }
}
