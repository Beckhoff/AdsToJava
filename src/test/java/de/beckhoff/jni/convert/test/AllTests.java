package de.beckhoff.jni.convert.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author Beckhoff Automation
 */
public class AllTests extends TestCase {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(AllTests.class);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(ConvertByteTest.class);
        suite.addTestSuite(ConvertShortTest.class);
        suite.addTestSuite(ConvertIntegerTest.class);
        suite.addTestSuite(ConvertLongTest.class);
        suite.addTestSuite(ConvertFloatTest.class);
        suite.addTestSuite(ConvertDoubleTest.class);
        suite.addTestSuite(ConvertBoolTest.class);
        suite.addTestSuite(ConvertCharTest.class);
        suite.addTestSuite(ConvertStringTest.class);
        suite.addTestSuite(ConvertExceptionTest.class);

        return suite;
    }
}
