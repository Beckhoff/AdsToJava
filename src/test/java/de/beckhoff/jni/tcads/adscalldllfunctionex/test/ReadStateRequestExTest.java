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
public class ReadStateRequestExTest extends TestCase {
    AmsAddr addr;
    JNIByteBuffer buffer;
    long port, err;

    private final int ZERO_VAL = 0;
    AdsState ads_state, device_state;

    public ReadStateRequestExTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();
        ads_state = new AdsState();
        device_state = new AdsState();

        port = AdsCallDllFunction.adsPortOpenEx();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortCloseEx(port);
    }

    public void testReadStateRequestEx() {
        err = AdsCallDllFunction.adsSyncReadStateReqEx(port, addr, ads_state,
                                                       device_state);

        assertEquals("ReadStateRequestEx test",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("ReadStateRequestEx ads-state", AdsState.ADSSTATE_RUN,
                     ads_state.getState());

        assertEquals("ReadStateRequestEx ads-state", ZERO_VAL,
                     device_state.getState());
    }

    public void tetsReadStateRequestExAddrNull() {
        addr = null;
        err = AdsCallDllFunction.adsSyncReadStateReqEx(port, addr, ads_state,
                                                       device_state);

        assertEquals("ReadStateRequestEx address null test",
                     AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
    }

    public void testReadStateRequestExAdsStateNull() {
        ads_state = null;
        err = AdsCallDllFunction.adsSyncReadStateReqEx(port, addr, ads_state,
                                                       device_state);

        assertEquals("ReadStateRequestEx ads state null",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
    }

    public void testReadStateRequestExDeviceStateNull() {
        ads_state = null;
        err = AdsCallDllFunction.adsSyncReadStateReqEx(port, addr, ads_state,
                                                       device_state);

        assertEquals("ReadStateRequestEx device state null",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
    }
}