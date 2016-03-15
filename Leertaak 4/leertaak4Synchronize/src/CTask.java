/**
 * Created by Christopher on 11/03/2016.
 */
public class CTask implements Runnable {

    public static int next;

    private static CPrinter printer = new CPrinter();
    private int number;

    public CTask(int number) {
        super();
        this.number = number;
        next = number == Main.max ? number : number + 1;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void run() {
        try {
            printer.print(this);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
