package test;

import junit.framework.TestCase;
import multiformat.*;
import org.junit.Before;

/**
 * Junit
 */
public class TestBase extends TestCase {

    public TestBase(String arg0) {
        super(arg0);
    }

    Calculator calc;

    @Before
    public void setUp(){
        calc = new Calculator();
    }

    public void testOctalBase(){
        calc.setBase(new OctalBase());
        assertEquals("oct", calc.getBase().getName());
        Rational rational = new Rational(2.0);
        rational = rational.plus(new Rational(7.0));
        assertEquals(9.0, rational.getNumerator());
    }

    public void testBaseNumber(){
        String message = null;
        try {
            calc.setBase(new HexBase());
            calc.addOperand("1a");
        } catch (FormatException ex) {
            ex.getMessage();
        } catch (NumberBaseException ex) {
            message =  ex.getMessage();
        }

        assertNotNull(message);
    }
}
