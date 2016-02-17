/**
 * Created by Christopher on 15/02/2016.
 */
public class ArraySizeException extends Exception {

    public ArraySizeException(int arrayInt1[], int arrayInt2[]) {
        super("Arrays are not the same size. Sizes are: " + arrayInt1.length + " and " + arrayInt2.length);
    }
}
