import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Christopher on 11/03/2016.
 */
public class CPrinter {

    private final Lock lock = new ReentrantLock(); // Create a lock

    public void print(CTask task){
        lock.lock(); // Acquire the lock
        System.out.print(task);
        System.out.print(task);
        System.out.println();
        lock.unlock(); // Release the lock
    }
}
