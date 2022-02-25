package de.beckhoff.jni.tcads.adsversion.test;

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
        suite.addTestSuite(AdsVersionTest.class);

        return suite;
    }
}
