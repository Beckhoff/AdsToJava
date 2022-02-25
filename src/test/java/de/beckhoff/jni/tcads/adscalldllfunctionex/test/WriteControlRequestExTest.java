package de.beckhoff.jni.tcads.adscalldllfunctionex.test;

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
public class WriteControlRequestExTest extends TestCase {
    static final short RUN_STATE = AdsState.ADSSTATE_RUN;
    static final short STOP_STATE = AdsState.ADSSTATE_STOP;
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer buffer;
    long port, err;

    public WriteControlRequestExTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        buffer = new JNIByteBuffer();
        addr = new AmsAddr();

        port = AdsCallDllFunction.adsPortOpenEx();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        // Restore initial device state and ads state
        err = AdsCallDllFunction.adsSyncWriteControlReqEx(
            port, addr, RUN_STATE, ZERO_VAL, port, buffer);

        AdsCallDllFunction.adsPortCloseEx(port);
    }

    public void testWriteControlRequestEx() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            err = AdsCallDllFunction.adsSyncWriteControlReqEx(
                port, addr, STOP_STATE, ZERO_VAL, buffer.getUsedBytesCount(),
                buffer);

            assertEquals("Test WriteControlRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            AdsState adsState = new AdsState();
            AdsState deviceState = new AdsState();
            err = AdsCallDllFunction.adsSyncReadStateReqEx(port, addr, adsState,
                                                           deviceState);

            assertEquals("Test WriteControlRequestEx check ads-state",
                         STOP_STATE, adsState.getState());
        } else {
            fail();
        }
    }

    public void testWriteControlRequestExInvalidState() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            // Setting the same state twice results in a invalid server state
            // error.
            err = AdsCallDllFunction.adsSyncWriteControlReqEx(
                port, addr, RUN_STATE, ZERO_VAL, buffer.getUsedBytesCount(),
                buffer);

            assertEquals("Fail WriteControlRequestEx error value",
                         AdsCallDllFunction.ADSERR_INV_SERVER_STATE, err);
        } else {
            fail();
        }
    }

    public void testWriteControlRequestExAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            addr = null;
            err = AdsCallDllFunction.adsSyncWriteControlReqEx(
                port, addr, AdsState.ADSSTATE_STOP, ZERO_VAL,
                buffer.getUsedBytesCount(), buffer);

            assertEquals(
                "Fail WriteControlRequestEx error value (Address null)",
                AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
        } else {
            fail();
        }
    }

    public void testWriteControlRequestExBuffNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = null;
            err = AdsCallDllFunction.adsSyncWriteControlReqEx(
                port, addr, AdsState.ADSSTATE_STOP, ZERO_VAL, ZERO_VAL, buffer);

            assertEquals("Fail WriteControlRequestEx error value (Buffer null)",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }
}