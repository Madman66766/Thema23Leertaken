public class Main {

    public static void main(String[] args) {

        int arrayInt1[] = {1,2};
        int arrayInt2[] = {1, 2, 0};

        try {
            printArray(calculate(arrayInt1, arrayInt2));
        } catch (ArraySizeException ex){
            System.out.print(ex.getMessage());
        }
    }

    private static int[] calculate(int arrayInt1[], int arrayInt2[]) throws ArraySizeException {
        if (arrayInt1.length == arrayInt2.length) {
            int arrayReturn[] =  new int[arrayInt1.length];
            for (int i = 0; i < arrayInt1.length; i++) {
                arrayReturn[i] = arrayInt1[i] +  arrayInt2[i];
            }
            return arrayReturn;
        } else {
            throw new ArraySizeException(arrayInt1, arrayInt2);
        }
    }

    private static void printArray(int array[]){
        for (int i : array) {
            System.out.println(i);
        }
    }
}
