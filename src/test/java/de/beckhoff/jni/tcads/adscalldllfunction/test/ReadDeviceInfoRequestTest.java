package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AdsDevName;
import de.beckhoff.jni.tcads.AdsVersion;
import de.beckhoff.jni.tcads.AmsAddr;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ReadDeviceInfoRequestTest extends TestCase {
    AmsAddr addr;
    JNIByteBuffer buffer;
    long port, err;

    AdsDevName deviceName;
    AdsVersion version;

    public ReadDeviceInfoRequestTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();
        deviceName = new AdsDevName();
        version = new AdsVersion();

        port = AdsCallDllFunction.adsPortOpen();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortClose();
    }

    public void testReadDeviceInfoRequest() {
        err = AdsCallDllFunction.adsSyncReadDeviceInfoReq(addr, deviceName,
                                                          version);

        assertEquals("ReadDeviceInfoRequest test",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("ReadDeviceInfoRequest (DeviceName)", AllTests.DEVICE_NAME,
                     deviceName.getDevName());
    }

    public void tetsReadStateRequestAddrNull() {
        addr = null;
        err = AdsCallDllFunction.adsSyncReadDeviceInfoReq(addr, deviceName,
                                                          version);

        assertEquals("ReadDeviceInfoRequest address null test",
                     AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
    }

    public void testReadStateRequestAdsStateNull() {
        deviceName = null;
        err = AdsCallDllFunction.adsSyncReadDeviceInfoReq(addr, deviceName,
                                                          version);

        assertEquals("ReadDeviceInfoRequest ads device name null",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
    }

    public void testReadStateRequestDeviceStateNull() {
        version = null;
        err = AdsCallDllFunction.adsSyncReadDeviceInfoReq(addr, deviceName,
                                                          version);

        assertEquals("ReadDeviceInfoRequest ads version null",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
    }
}