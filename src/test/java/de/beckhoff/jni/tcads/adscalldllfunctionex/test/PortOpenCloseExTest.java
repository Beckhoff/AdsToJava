package de.beckhoff.jni.tcads.adscalldllfunctionex.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class PortOpenCloseExTest extends TestCase {
    long port, err;

    public PortOpenCloseExTest(String name) { super(name); }

    @Override
    protected void setUp() {
        port = 0;
        err = 0;
    }

    @Override
    protected void tearDown() {}

    public void testPortOpenCloseEx() {
        port = AdsCallDllFunction.adsPortOpenEx();
        assertTrue("Test Ads PortOpenEx",
                   ((port >= AllTests.ADS_PORTNUMBER_MIN) &
                    (port <= AllTests.ADS_PORTNUMBER_MAX)));

        err = AdsCallDllFunction.adsPortCloseEx(port);
        assertEquals("Test Ads PortCloseEx", AdsCallDllFunction.ADSERR_NO_ERR,
                     err);
    }

    public void testPortCloseExInvalidPort() {
        err =
            AdsCallDllFunction.adsPortCloseEx(AllTests.ADS_PORTNUMBER_MAX + 1);

        assertEquals("Test Ads PortCloseEx invalid port err",
                     AdsCallDllFunction.ADSERR_ADSPORT_CLOSED, err);
    }
}