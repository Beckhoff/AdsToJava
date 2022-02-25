package de.beckhoff.jni.jnibytebuffer.test;

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
        suite.addTestSuite(JNIByteBufferInitializationTest.class);
        suite.addTestSuite(JNIByteBufferInitializationExceptionTest.class);
        suite.addTestSuite(JNIByteBufferSetTest.class);

        return suite;
    }
}
