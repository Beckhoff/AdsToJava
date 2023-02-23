package de.beckhoff.jni.tcads.adscalldllfunction.test;

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

        suite.addTestSuite(PortOpenCloseTest.class);
        suite.addTestSuite(GetLocalAmsAddressTest.class);
        suite.addTestSuite(ReadRequestTest.class);
        suite.addTestSuite(WriteRequestTest.class);
        suite.addTestSuite(WriteRequestArrayTest.class);
        suite.addTestSuite(ReadWriteRequestTest.class);
        suite.addTestSuite(ReadStateRequestTest.class);
        suite.addTestSuite(ReadDeviceInfoRequestTest.class);
        suite.addTestSuite(WriteControlRequestTest.class);
        suite.addTestSuite(GetSetTimeoutTest.class);
        suite.addTestSuite(DeviceNotificationTest.class);
        suite.addTestSuite(RouterNotificationTest.class);
        suite.addTestSuite(CallbackObjectTest.class);
        suite.addTestSuite(AdsAmsPortEnabledTest.class);
        suite.addTestSuite(FailPortOpenCloseTest.class);

        return suite;
    }
}
