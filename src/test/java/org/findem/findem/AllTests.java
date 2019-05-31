package org.findem.findem;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.findem.findem.saya_test.SayaTest;

/**
 * Unit SayaTest for simple App.
 */
import org.findem.findem.saya_test.SayaTest2;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SayaTest.class, SayaTest2.class })
public class AllTests {

}
