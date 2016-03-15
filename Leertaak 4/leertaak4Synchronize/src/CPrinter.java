import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Christopher on 11/03/2016.
 */
public class CPrinter {

    private static final Lock lock = new ReentrantLock(); // Create a lock
    private static final Condition smaller = lock.newCondition();
    private static final Condition bigger = lock.newCondition();

    public void print(CTask task) throws InterruptedException {
        lock.lock(); // Acquire the lock
        if (task.getNumber() < CTask.next){
            smaller.await();
        } else {
            System.out.print(task);
            System.out.print(task);
            System.out.println();
            CTask.next--;
            bigger.signalAll();
        }
        lock.unlock(); // Release the lock
    }
}
