package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class PortOpenCloseTest extends TestCase {
    long port, err;

    public PortOpenCloseTest(String name) { super(name); }

    @Override
    protected void setUp() {
        port = 0;
        err = 0;
    }

    @Override
    protected void tearDown() {}

    public void testPortOpenClose() {
        port = AdsCallDllFunction.adsPortOpen();
        assertTrue("Test Ads PortOpen",
                   ((port >= AllTests.ADS_PORTNUMBER_MIN) &
                    (port <= AllTests.ADS_PORTNUMBER_MAX)));

        err = AdsCallDllFunction.adsPortClose();
        assertEquals("Test Ads PortClose", AdsCallDllFunction.ADSERR_NO_ERR,
                     err);
    }
}