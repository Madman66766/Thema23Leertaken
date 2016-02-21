import java.util.Stack;

/**
 * Created by Christopher on 20/02/2016.
 */
public class BinaryTree {

    private Node root;
    private Stack<Node> stackNodes = new Stack();

    public BinaryTree() {
        root = null;
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
        } else {
            if (data <= node.data) {
                node.left = insert(node.left, data);
            }
            else {
                node.right = insert(node.right, data);
            }
        }

        return node;
    }

    public void printInorder(){
        System.out.print("Inorder:");
        Node current = root;
        while (current != null || !stackNodes.isEmpty()){
            if (current != null) {
                stackNodes.push(current);
                current = current.left;
            } else {
                current = stackNodes.pop();
                System.out.print(" [" + current.data + "] ");
                current = current.right;
            }
        }
    }

    public void printPreOrder(){
        System.out.print("Pre order:");
        Node current = root;
        while (current != null || !stackNodes.isEmpty()) {
            if (current != null) {
                System.out.print(" [" + current.data + "] ");
                stackNodes.push(current);
                current = current.right;
            } else {
                current = stackNodes.pop();
                current = current.right;
            }
        }
    }

    public void printPostOrder() {
        System.out.print("Post order:");
        Node current = root;
        while (current != null || !stackNodes.isEmpty()) {
            if (current != null) {
                stackNodes.push(current);
                current = current.left;
            } else {
                current = stackNodes.pop();
                if (current.secondPop) {
                    System.out.print(" [" + current.data + "] ");
                    current = current.right;
                } else {
                    current.secondPop = true;
                    stackNodes.push(current);
                    current = current.right;
                }
            }
        }
    }
}
