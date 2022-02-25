package de.beckhoff.jni.tcads.adscalldllfunctionex.test;

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
public class DeviceNotificationRequestExTest extends TestCase {
    AmsAddr addr;
    AdsNotificationAttrib attr;
    JNILong notification;
    long port, err;
    int user;

    public DeviceNotificationRequestExTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();

        port = AdsCallDllFunction.adsPortOpenEx();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);

        notification = new JNILong();
        user = new Random(System.currentTimeMillis()).nextInt();

        attr = new AdsNotificationAttrib();
        attr.setCbLength((Byte.SIZE * 4) / 8);
        attr.setNTransMode(AdsConstants.ADSTRANS_SERVERONCHA);
        attr.setDwChangeFilter(10000000); // 1 sec
        attr.setNMaxDelay(20000000);      // 2 sec
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortCloseEx(port);
    }

    public void testAddDeleteDeviceNotificationEx() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1, attr,
                user, notification);

            assertEquals("Test AddDeviceNotificationEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReqEx(
                port, addr, notification);

            assertEquals("Test DelDeviceNotificationEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail();
        }
    }

    public void testAddDeleteDeviceNotificationExAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            addr = null;
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1, attr,
                user, notification);

            assertEquals("Test AddDeviceNotificationEx error value",
                         AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
        } else {
            fail();
        }
    }

    public void testAddDeleteDeviceNotificationNoteExAttrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            attr = null;
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1, attr,
                user, notification);

            assertEquals("Test AddDeviceNotificationEx error value",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }

    public void testAddDeleteDeviceNotificationNoteExBuffNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            notification = null;
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1, attr,
                user, notification);

            assertEquals("Test AddDeviceNotificationEx error value",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }
}