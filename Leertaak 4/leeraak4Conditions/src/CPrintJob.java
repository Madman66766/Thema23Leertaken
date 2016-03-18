import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Christopher on 18/03/2016.
 */
public class CPrintJob implements Runnable{

    // Create a new lock
    private static Lock lock = new ReentrantLock();

    // Create a condition
    private static Condition bigger = lock.newCondition();

    private static int next;

    private CPrinter printer;
    private int number;

    public CPrintJob(CPrinter printer, int number, int next){
        this.printer = printer;
        this.number = number;
        CPrintJob.next = next;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            while (number < CPrintJob.next) {
                bigger.await();
            }
            printer.print(number);
            CPrintJob.next--;
            bigger.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
