package de.beckhoff.jni.tcads.adscalldllfunctionex.test;

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
public class GetSetTimeoutExTest extends TestCase {
    static final int ADS_TIMEOUT_DEFAULT = 5000;
    static final int ADS_TIMEOUT_CHANGED = 2500;
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer buffer;
    JNILong timeout;
    long port;
    long err;

    public GetSetTimeoutExTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();

        port = AdsCallDllFunction.adsPortOpenEx();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsSyncSetTimeoutEx(port, ADS_TIMEOUT_CHANGED);

        AdsCallDllFunction.adsPortCloseEx(port);
    }

    public void testGetTimeoutEx() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            timeout = new JNILong();
            err = AdsCallDllFunction.adsSyncGetTimeoutEx(port, timeout);

            assertEquals("Test SetTimeoutEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test GetTimeoutEx timeout-value", ADS_TIMEOUT_DEFAULT,
                         timeout.getLong());
        }
    }

    public void testSetTimeoutEx() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            err = AdsCallDllFunction.adsSyncSetTimeoutEx(port,
                                                         ADS_TIMEOUT_CHANGED);

            assertEquals("Test SetTimeoutEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            timeout = new JNILong();
            err = AdsCallDllFunction.adsSyncGetTimeoutEx(port, timeout);

            assertEquals("Test GetTimeoutEx timeout-value", ADS_TIMEOUT_CHANGED,
                         timeout.getLong());
        } else {
            fail();
        }
    }

    public void testSetTimeoutExBuffNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            timeout = null;
            err = AdsCallDllFunction.adsSyncGetTimeoutEx(port, timeout);

            assertEquals("SetTimeoutEx buffer null error value",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }

    public void testSetTimeoutExInvTimeout() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            err = AdsCallDllFunction.adsSyncSetTimeoutEx(port, 0);

            assertEquals("Fail SetTimeoutEx error value",
                         AdsCallDllFunction.ADSERR_INV_CLIENT_TIMEOUT, err);
        } else {
            fail();
        }
    }
}