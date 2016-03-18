/**
 * Created by Christopher on 10/03/2016.
 */
public class Main {

    public static void main(String args[]) {
        for (int i = 1; i < 5; i++){
            new Thread(new CTask(String.valueOf(i))).start();
        }
    }
}
