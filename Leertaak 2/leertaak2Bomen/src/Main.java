import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;

/**
 * Created by Christopher on 21/02/2016.
 */
public class Main {



    public static void main(String args[]){

        DefaultMutableTreeNode localCustomers = new DefaultMutableTreeNode("Local customers");
        DefaultMutableTreeNode regionalCustomers = new DefaultMutableTreeNode("Regional customers");

        DefaultMutableTreeNode usCustomer = new DefaultMutableTreeNode("US customer");
        usCustomer.add(localCustomers);
        usCustomer.add(regionalCustomers);

        DefaultMutableTreeNode nonUsCustomer = new DefaultMutableTreeNode("Non US customer");
        DefaultMutableTreeNode customer = new DefaultMutableTreeNode("Customer");
        customer.add(usCustomer);
        customer.add(nonUsCustomer);

        DefaultMutableTreeNode salesRep = new DefaultMutableTreeNode("Sales rep");
        DefaultMutableTreeNode engineer = new DefaultMutableTreeNode("Engineer");
        DefaultMutableTreeNode employee = new DefaultMutableTreeNode("Employee");
        employee.add(salesRep);
        employee.add(engineer);

        DefaultMutableTreeNode person = new DefaultMutableTreeNode("Person");
        person.add(employee);
        person.add(customer);

        System.out.println("Breadth First: ");
        printTree(person.breadthFirstEnumeration());
        System.out.println("Pre order: ");
        printTree(person.preorderEnumeration());
        System.out.println("Post order: ");
        printTree(person.postorderEnumeration());
    }

    public static void printTree(Enumeration tree) {

        while (tree.hasMoreElements()) {
            System.out.println(tree.nextElement().toString());
        }
        System.out.println();
    }
}
