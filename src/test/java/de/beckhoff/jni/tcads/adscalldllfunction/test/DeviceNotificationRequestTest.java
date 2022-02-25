package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.AdsConstants;
import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.JNILong;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AdsNotificationAttrib;
import de.beckhoff.jni.tcads.AmsAddr;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class DeviceNotificationRequestTest extends TestCase {
    static final short INDEX_GROUP = 0x4020;
    static final short INDEX_OFF1 = 0x7;
    static final short INDEX_OFF2 = 0x8;
    static final short INDEX_OFF3 = 0xA;

    static final int USER = 42;

    AmsAddr addr;
    AdsNotificationAttrib attr;
    JNILong notification;
    long port, err;

    public DeviceNotificationRequestTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();

        port = AdsCallDllFunction.adsPortOpen();
        err = AdsCallDllFunction.getLocalAddress(addr);
        addr.setPort(AllTests.DEVICE_PORT);

        notification = new JNILong();

        attr = new AdsNotificationAttrib();
        attr.setCbLength((Byte.SIZE * 4) / 8);
        attr.setNTransMode(AdsConstants.ADSTRANS_SERVERONCHA);
        attr.setDwChangeFilter(10000000); // 1 sec
        attr.setNMaxDelay(20000000);      // 2 sec
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortClose();
    }

    public void testAddDeleteDeviceNotification() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, INDEX_GROUP, INDEX_OFF1, attr, USER, notification);

            assertEquals("Test AddDeviceNotification error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DelDeviceNotification error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail();
        }
    }

    public void testAddDeleteDeviceNotificationAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            addr = null;
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, INDEX_GROUP, INDEX_OFF1, attr, USER, notification);

            assertEquals("Test AddDeviceNotification error value",
                         AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
        } else {
            fail();
        }
    }

    public void testAddDeleteDeviceNotificationNoteAttrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            attr = null;
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, INDEX_GROUP, INDEX_OFF1, attr, USER, notification);

            assertEquals("Test AddDeviceNotification error value",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }

    public void testAddDeleteDeviceNotificationNoteBuffNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            notification = null;
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, INDEX_GROUP, INDEX_OFF1, attr, USER, notification);

            assertEquals("Test AddDeviceNotification error value",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }
}