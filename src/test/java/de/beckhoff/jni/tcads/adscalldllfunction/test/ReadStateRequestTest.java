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
public class ReadStateRequestTest extends TestCase {
    AmsAddr addr;
    JNIByteBuffer buffer;
    long port, err;

    private final int ZERO_VAL = 0;
    AdsState ads_state, device_state;

    public ReadStateRequestTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();
        ads_state = new AdsState();
        device_state = new AdsState();

        port = AdsCallDllFunction.adsPortOpen();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortClose();
    }

    public void testReadStateRequest() {
        err = AdsCallDllFunction.adsSyncReadStateReq(addr, ads_state,
                                                     device_state);

        assertEquals("ReadStateRequest test", AdsCallDllFunction.ADSERR_NO_ERR,
                     err);

        assertEquals("ReadStateRequest ads-state", AdsState.ADSSTATE_RUN,
                     ads_state.getState());

        assertEquals("ReadStateRequest ads-state", ZERO_VAL,
                     device_state.getState());
    }

    public void tetsReadStateRequestAddrNull() {
        addr = null;
        err = AdsCallDllFunction.adsSyncReadStateReq(addr, ads_state,
                                                     device_state);

        assertEquals("ReadStateRequest address null test",
                     AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
    }

    public void testReadStateRequestAdsStateNull() {
        ads_state = null;
        err = AdsCallDllFunction.adsSyncReadStateReq(addr, ads_state,
                                                     device_state);

        assertEquals("ReadStateRequest ads state null",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
    }

    public void testReadStateRequestDeviceStateNull() {
        ads_state = null;
        err = AdsCallDllFunction.adsSyncReadStateReq(addr, ads_state,
                                                     device_state);

        assertEquals("ReadStateRequest device state null",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
    }
}