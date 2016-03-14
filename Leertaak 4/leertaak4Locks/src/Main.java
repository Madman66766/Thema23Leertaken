/**
 * Created by Christopher on 10/03/2016.
 */
public class Main {

    public static void main(String args[]) {
        for (int i = 0; i < 4; i++){
            new Thread(new CTask(String.valueOf(i))).start();
        }
    }
}
