package de.beckhoff.jni.tcads.adscalldllfunctionex.test;

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
public class ReadDeviceInfoRequestExTest extends TestCase {
    AmsAddr addr;
    JNIByteBuffer buffer;
    long port, err;

    AdsDevName deviceName;
    AdsVersion version;

    public ReadDeviceInfoRequestExTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();
        deviceName = new AdsDevName();
        version = new AdsVersion();

        port = AdsCallDllFunction.adsPortOpenEx();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortCloseEx(port);
    }

    public void testReadDeviceInfoRequestEx() {
        err = AdsCallDllFunction.adsSyncReadDeviceInfoReqEx(
            port, addr, deviceName, version);

        assertEquals("ReadDeviceInfoRequestEx test",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("ReadDeviceInfoRequestEx (DeviceName)",
                     AllTests.DEVICE_NAME, deviceName.getDevName());
    }

    public void tetsReadStateRequestExAddrNull() {
        addr = null;
        err = AdsCallDllFunction.adsSyncReadDeviceInfoReqEx(
            port, addr, deviceName, version);

        assertEquals("ReadDeviceInfoRequestEx address null test",
                     AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
    }

    public void testReadStateRequestExAdsStateNull() {
        deviceName = null;
        err = AdsCallDllFunction.adsSyncReadDeviceInfoReqEx(
            port, addr, deviceName, version);

        assertEquals("ReadDeviceInfoRequestEx ads device name null",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
    }

    public void testReadStateRequestExDeviceStateNull() {
        version = null;
        err = AdsCallDllFunction.adsSyncReadDeviceInfoReqEx(
            port, addr, deviceName, version);

        assertEquals("ReadDeviceInfoRequestEx ads version null",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
    }
}