package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AdsState;
import de.beckhoff.jni.tcads.AmsAddr;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class WriteControlRequestTest extends TestCase {
    static final short RUN_STATE = AdsState.ADSSTATE_RUN;
    static final short STOP_STATE = AdsState.ADSSTATE_STOP;
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer buffer;
    long port, err;

    public WriteControlRequestTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        buffer = new JNIByteBuffer();
        addr = new AmsAddr();

        port = AdsCallDllFunction.adsPortOpen();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        // Restore initial device state and ads state
        err = AdsCallDllFunction.adsSyncWriteControlReq(addr, RUN_STATE,
                                                        ZERO_VAL, port, buffer);

        AdsCallDllFunction.adsPortClose();
    }

    public void testWriteControlRequest() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncWriteControlReq(
                addr, STOP_STATE, ZERO_VAL, buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteControlRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            AdsState adsState = new AdsState();
            AdsState deviceState = new AdsState();
            err = AdsCallDllFunction.adsSyncReadStateReq(addr, adsState,
                                                         deviceState);

            assertEquals("Test WriteControlRequest check ads-state", STOP_STATE,
                         adsState.getState());
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testWriteControlRequestInvalidState() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            // Setting the same state twice results in a invalid server state
            // error.
            err = AdsCallDllFunction.adsSyncWriteControlReq(
                addr, RUN_STATE, ZERO_VAL, buffer.getUsedBytesCount(), buffer);

            assertEquals("Fail WriteControlRequest error value",
                         AdsCallDllFunction.ADSERR_INV_SERVER_STATE, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testWriteControlRequestAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            addr = null;
            err = AdsCallDllFunction.adsSyncWriteControlReq(
                addr, AdsState.ADSSTATE_STOP, ZERO_VAL,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Fail WriteControlRequest error value (Address null)",
                         AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testWriteControlRequestBuffNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = null;
            err = AdsCallDllFunction.adsSyncWriteControlReq(
                addr, AdsState.ADSSTATE_STOP, ZERO_VAL, ZERO_VAL, buffer);

            assertEquals("Fail WriteControlRequest error value (Buffer null)",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }
}