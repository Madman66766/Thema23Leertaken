import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Random;

/**
 * Created by Christopher on 17/02/2016.
 */
public class Main {

    private static Random random = new Random();

    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        int arrayData[] = initArray(5);//{40, 32, 25, 78, 10};//initArray(5);

        for (int data: arrayData) {
            tree.insert(data);
        }

        System.out.println();
        tree.printInorder();
        System.out.println();
        tree.printPreOrder();
        System.out.println();
        tree.printPostOrder();
    }

    private static int[] initArray(int length){
        System.out.print("Numbers: ");
        int arrayData[] = new int [length];
        int min = 0, max = 1000;

        for (int i = 0; i < arrayData.length - 1; i++) {
            arrayData[i] = random.nextInt((max - min) + 1) + min;
            System.out.print(" [" + arrayData[i] + "] ");
        }
        return arrayData;
    }
}
