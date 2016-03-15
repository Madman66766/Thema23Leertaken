/**
 * Created by Christopher on 10/03/2016.
 */
public class Main {

    public static int max = 3;

    public static void main(String args[]) {

        for (int i = 0; i < max + 1; i++){
            new Thread(new CTask(i)).start();
        }
    }
}
