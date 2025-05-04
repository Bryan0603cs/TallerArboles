package uniquindio.edu.co.TallerArboles2.model;


public class Node {
    int data;
    Node left, right;

    public Node(int item) {
        this.data = item;
        this.left = this.right = null;
    }
}
