package org.findem.findem;

import org.findem.findem.lawson_test.LawsonTest;
import org.findem.findem.lawson_test.LawsonTest2;
import org.findem.findem.saya_test.SayaTest;
import org.findem.findem.saya_test.SayaTest2;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SayaTest.class, SayaTest2.class, LawsonTest.class, LawsonTest2.class })
public class AllTests {

}
