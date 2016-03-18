import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Christopher on 18/03/2016.
 */
public class Main {
    private static CPrinter printer = new CPrinter();
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(new CPrintJob(printer, 1, 2));
        executor.execute(new CPrintJob(printer, 2, 3));
        executor.execute(new CPrintJob(printer, 3, 4));
        executor.execute(new CPrintJob(printer, 4, 4));
        executor.shutdown();
    }
}
