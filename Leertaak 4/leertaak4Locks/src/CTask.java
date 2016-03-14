/**
 * Created by Christopher on 11/03/2016.
 */
public class CTask implements Runnable {

    private String name;
    private CPrinter printer = new CPrinter();

    public CTask(String name){
        super();
        this.name = name;
    }

    @Override
    public void run() {
       printer.print(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
