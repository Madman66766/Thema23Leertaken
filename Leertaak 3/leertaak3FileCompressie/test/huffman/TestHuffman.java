package huffman;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Christopher on 09/03/2016.
 */
public class TestHuffman extends TestCase {

    public void testHuffman(){

        File original = new File("data/TestA.dat");
        File uncompressed = new File("data/TestA.dat.uc");
        assertEquals(fileToString(original), fileToString(uncompressed));
    }


    private String fileToString(File file){
        String string = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            string = sb.toString();
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return string;
    }
}
