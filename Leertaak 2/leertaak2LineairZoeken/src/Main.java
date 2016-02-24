import java.util.Random;

/**
 * Created by Christopher on 22/02/2016.
 */
public class Main {

    private static long startTime, endTime;
    private static Random random = new Random();
    private static int number, max = 200000, min = 0;

    public static void main(String args[]){
        GetalRij getalRij = new GetalRij( 100000, 200000);
        number = random.nextInt((max - min) + 1) + min;
        System.out.println("Number: " + number);
        startTime = tijd();
        getalRij.zitErinA(number);
        endTime = tijd();
        printTime();
        startTime = tijd();
        getalRij.zitErinB(number);
        endTime = tijd();
        printTime();
        startTime = tijd();
        getalRij.zitErinC(number);
        endTime = tijd();
        printTime();
        startTime = tijd();
        getalRij.zitErinD(number);
        endTime = tijd();
        printTime();
    }

    private static long tijd(){
        return System.nanoTime();
    }

    private static void printTime(){
        System.out.println(endTime - startTime);
    }

}
