import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Christopher on 11/03/2016.
 */
public class CTask implements Runnable {

    private final Lock lock = new ReentrantLock();
    private final Condition smaller = lock.newCondition();
    private final Condition bigger = lock.newCondition();
    private static int next;
    private int number;
    private CPrinter printer = new CPrinter();

    public CTask(int number, int next){
        super();
        this.number = number;
        this.next = next;
    }

    @Override
    public void run() {
       print();

    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    private void print(){
        lock.lock();
        try {
            if (number < next){
                smaller.await();
            } else {
                printer.print(this);
                smaller.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
