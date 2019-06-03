package org.findem.findem;

import org.findem.findem.bishop_test.BishopTest1;
import org.findem.findem.bishop_test.BishopTest2;
import org.findem.findem.ivanov_test.IvanovTest1;
import org.findem.findem.ivanov_test.IvanovTest2;
import org.findem.findem.lawson_test.LawsonTest;
import org.findem.findem.lawson_test.LawsonTest2;
import org.findem.findem.saya_test.SayaTest;
import org.findem.findem.saya_test.SayaTest2;
import org.findem.findem.tschopp_test.TschoppTest1;
import org.findem.findem.tschopp_test.TschoppTest2;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        SayaTest.class, SayaTest2.class,
        LawsonTest.class, LawsonTest2.class,
        BishopTest1.class, BishopTest2.class,
        IvanovTest1.class, IvanovTest2.class,
        TschoppTest1.class, TschoppTest2.class
        })
public class AllTests {

}
