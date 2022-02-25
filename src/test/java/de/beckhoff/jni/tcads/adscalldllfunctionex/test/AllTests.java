package de.beckhoff.jni.tcads.adscalldllfunctionex.test;

import de.beckhoff.jni.tcads.AdsCallDllFunction;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 *
 * @author Beckhoff Automation
 */
public class AllTests {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(de.beckhoff.jni.AllTests.class);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            suite.addTestSuite(AdsAmsPortEnabledExTest.class);
            suite.addTestSuite(DeviceNotificationRequestExTest.class);
            suite.addTestSuite(GetLocalAmsAddressExTest.class);
            suite.addTestSuite(GetSetTimeoutExTest.class);
            suite.addTestSuite(PortOpenCloseExTest.class);
            suite.addTestSuite(ReadDeviceInfoRequestExTest.class);
            suite.addTestSuite(ReadRequestEx2Test.class);
            suite.addTestSuite(ReadRequestExTest.class);
            suite.addTestSuite(ReadStateRequestExTest.class);
            suite.addTestSuite(ReadWriteRequestExTest.class);
            suite.addTestSuite(ReadWriteRequestEx2Test.class);
            suite.addTestSuite(WriteControlRequestExTest.class);
            suite.addTestSuite(WriteRequestExArrayTest.class);
            suite.addTestSuite(WriteRequestExTest.class);
        }

        return suite;
    }
}