package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.AdsConstants;
import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.JNILong;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AdsNotificationAttrib;
import de.beckhoff.jni.tcads.AmsAddr;
import java.util.Random;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class DeviceNotificationTest extends TestCase {
    AmsAddr addr;
    AdsNotificationAttrib attr;
    JNILong notification;
    long port, err;
    int user;

    public DeviceNotificationTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();

        port = AdsCallDllFunction.adsPortOpen();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);

        user = new Random(System.currentTimeMillis()).nextInt();
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

    public void testAddDeleteDeviceNotification1() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2, attr, user,
                notification);

            assertEquals("Test AddDeviceNotification error value (short)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DeleteDeviceNotification error value (short)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testAddDeleteDeviceNotification2() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4, attr, user,
                notification);

            assertEquals("Test AddDeviceNotification error value (bool)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DeleteDeviceNotification error value (bool)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testAddDeleteDeviceNotification3() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6, attr, user,
                notification);

            assertEquals("Test AddDeviceNotification error value (double)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DeleteDeviceNotification error value (double)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testAddDeleteDeviceNotification4() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7, attr, user,
                notification);

            assertEquals("Test AddDeviceNotification error value (string)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DeleteDeviceNotification error value (string)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testAddDeleteDeviceNotificationAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            addr = null;
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3, attr, user,
                notification);

            assertEquals("Fail SetTimeout error value (Address null)",
                         AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testAddDeleteDeviceNotificationNotificationAttrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            attr = null;
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3, attr, user,
                notification);

            assertEquals(
                "Fail SetTimeout error value (NotificationAttrib null)",
                AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testAddDeleteDeviceNotificationNotificationNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            notification = null;
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3, attr, user,
                notification);

            assertEquals(
                "Fail SetTimeout error value (NotificationAttrib null)",
                AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testAddDeleteDeviceNotificationInvIGroup() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            attr = null;
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, Long.MAX_VALUE, AllTests.INDEX_OFF3, attr, user,
                notification);

            assertEquals(
                "Fail SetTimeout error value (NotificationAttrib null)",
                AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testAddDeleteDeviceNotificationInvIOffs() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            attr = null;
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, Long.MAX_VALUE, attr, user,
                notification);

            assertEquals(
                "Fail SetTimeout error value (NotificationAttrib null)",
                AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }
}