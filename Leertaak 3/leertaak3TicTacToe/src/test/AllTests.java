package test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by Serkan on 7-3-2016.
 */
public class AllTests {

    public static Test suite(){
        TestSuite suite = new TestSuite();
        //suite.addTest(TicTacToeTest.class);

        return suite;
    }
}
