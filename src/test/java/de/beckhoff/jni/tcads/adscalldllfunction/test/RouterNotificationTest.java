package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class RouterNotificationTest extends TestCase {
    AmsAddr addr;
    long port, err;

    public RouterNotificationTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();

        port = AdsCallDllFunction.adsPortOpen();
        err = AdsCallDllFunction.getLocalAddress(addr);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortClose();
    }

    public void testAddDeleteRouterNotification() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsAmsRegisterRouterNotification();

            assertEquals("Test AddRouterNotification error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsAmsUnRegisterRouterNotification();

            assertEquals("Test DeleteRouterNotification error value (short)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail();
        }
    }
}