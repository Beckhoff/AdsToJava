package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.JNILong;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class GetSetTimeoutTest extends TestCase {
    static final long ADS_TIMEOUT_DEFAULT = 5000;
    static final long ADS_TIMEOUT_CHANGED = 2500;
    static final long ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer buffer;
    JNILong timeout;
    long port, err;

    public GetSetTimeoutTest(String name) { super(name); }

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

    public void testGetTimeout() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
                timeout = new JNILong();
                err = AdsCallDllFunction.adsSyncGetTimeout(timeout);

                assertEquals("Test SetTimeout error value",
                             AdsCallDllFunction.ADSERR_NO_ERR, err);

                assertEquals("Test GetTimeout timeout-value",
                             ADS_TIMEOUT_DEFAULT, timeout.getLong());
            } else {
                timeout = new JNILong();
                err = AdsCallDllFunction.adsSyncGetTimeout(timeout);

                assertEquals(
                    "Test SetTimeout error value (service not supported)",
                    AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);
            }
        }
    }

    public void testSetTimeout() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
                err = AdsCallDllFunction.adsSyncSetTimeout(ADS_TIMEOUT_CHANGED);

                assertEquals("Test SetTimeout error value",
                             AdsCallDllFunction.ADSERR_NO_ERR, err);

                timeout = new JNILong();
                err = AdsCallDllFunction.adsSyncGetTimeout(timeout);

                assertEquals("Test GetTimeout timeout-value",
                             ADS_TIMEOUT_CHANGED, timeout.getLong());
            } else {
                // Do no test since it's already covered above
            }
        } else {
            fail();
        }
    }

    public void testSetTimeoutBuffNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            timeout = null;
            err = AdsCallDllFunction.adsSyncGetTimeout(timeout);

            assertEquals("SetTimeout buffer null error value",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }

    public void testSetTimeoutInvTimeout() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncSetTimeout(0);

            assertEquals("Fail SetTimeout error value",
                         AdsCallDllFunction.ADSERR_INV_CLIENT_TIMEOUT, err);
        } else {
            fail();
        }
    }
}