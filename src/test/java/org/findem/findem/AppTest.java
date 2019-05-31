package org.findem.findem;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.findem.findem.saya_test.SayaTest;

/**
 * Unit SayaTest for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the SayaTest case
     *
     * @param testName name of the SayaTest case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class, SayaTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
