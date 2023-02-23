package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.AdsConstants;
import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.JNILong;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AdsCallbackObject;
import de.beckhoff.jni.tcads.AdsNotificationAttrib;
import de.beckhoff.jni.tcads.AdsNotificationHeader;
import de.beckhoff.jni.tcads.AmsAddr;
import de.beckhoff.jni.tcads.CallbackListenerAdsState;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class CallbackObjectTest extends TestCase {
    protected class AdsListener implements CallbackListenerAdsState {
        public void onEvent(AmsAddr pAddr, AdsNotificationHeader pNotification,
                            long hUser) {
            hasEventFired = true;
        }
    }

    // Ads connection variables
    AmsAddr addr;
    long port, err;
    int user;

    // Ads callback variables
    JNILong notification;
    AdsNotificationAttrib attr;
    AdsListener listener;
    AdsCallbackObject callObject;

    // Ads callback test variable
    boolean hasEventFired;

    // Ads write request variables
    JNIByteBuffer buffer;

    CountDownLatch latch;

    public CallbackObjectTest(String name) { super(name); }

    @Override
    protected void setUp() {
        // Initial ads connection setup
        err = 0;
        addr = new AmsAddr();

        port = AdsCallDllFunction.adsPortOpen();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);

        // Initial callback preparation
        hasEventFired = false;
        notification = new JNILong();
        user = new Random(System.currentTimeMillis()).nextInt();

        latch = new CountDownLatch(1);

        attr = new AdsNotificationAttrib();
        attr.setNTransMode(AdsConstants.ADSTRANS_SERVERONCHA);
        attr.setDwChangeFilter(500); //  0.5 sec
        attr.setNMaxDelay(1500);     // 1.5 sec

        listener = new AdsListener();
        callObject = new AdsCallbackObject();
        callObject.addListenerCallbackAdsState(listener);
    }

    @Override
    protected void tearDown() {
        // Stop listening to variable change-events
        callObject.removeListenerCallbackAdsState(listener);

        // Restore initial PLC-values
        buffer = new JNIByteBuffer(
            Convert.ByteToByteArr(AllTests.BYTE_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReq(
            addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
            buffer.getUsedBytesCount(), buffer);

        buffer = new JNIByteBuffer(
            Convert.ShortToByteArr(AllTests.SHORT_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReq(
            addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
            buffer.getUsedBytesCount(), buffer);

        buffer =
            new JNIByteBuffer(Convert.IntToByteArr(AllTests.INT_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReq(
            addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
            buffer.getUsedBytesCount(), buffer);

        buffer =
            new JNIByteBuffer(Convert.IntToByteArr(AllTests.INT_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReq(
            addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
            buffer.getUsedBytesCount(), buffer);

        buffer = new JNIByteBuffer(
            Convert.BoolToByteArr(AllTests.BOOL_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReq(
            addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
            buffer.getUsedBytesCount(), buffer);

        buffer = new JNIByteBuffer(
            Convert.FloatToByteArr(AllTests.FLOAT_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReq(
            addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
            buffer.getUsedBytesCount(), buffer);

        buffer = new JNIByteBuffer(
            Convert.DoubleToByteArr(AllTests.DOUBLE_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReq(
            addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
            buffer.getUsedBytesCount(), buffer);

        buffer = new JNIByteBuffer(
            Convert.StringToByteArr(AllTests.STRING_VALUE_DEFAULT, true));
        err = AdsCallDllFunction.adsSyncWriteReq(
            addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
            buffer.getUsedBytesCount(), buffer);

        err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(addr,
                                                                 notification);

        AdsCallDllFunction.adsPortClose();
    }

    public void testDeviceNotification1() {
        // Read a signed short int from the PLC (1 Byte)
        attr.setCbLength(Byte.SIZE / Byte.SIZE);

        // Setup the buffer that is written to the PLC in order to induce events
        buffer = new JNIByteBuffer(Convert.ByteToByteArr(AllTests.BYTE_VAL));

        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1, attr, user,
                notification);

            assertEquals("Test AddDeviceNotification error value (byte)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test error code (event inducing)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            try {
                latch.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(CallbackObjectTest.class.getName())
                    .log(Level.SEVERE, null, ex);
            }

            assertTrue(hasEventFired);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DeleteDeviceNotification error value (byte)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testDeviceNotification2() {
        // Read a signed short int from the PLC (1 Byte)
        attr.setCbLength(Short.SIZE / Byte.SIZE);

        // Setup the buffer that is written to the PLC in order to induce events
        buffer = new JNIByteBuffer(Convert.ShortToByteArr(AllTests.SHORT_VAL));

        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2, attr, user,
                notification);

            assertEquals("Test AddDeviceNotification error value (short)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
            assertTrue("Test AddDeviceNotification notification handle",
                       notification != null);
            assertTrue("Test AddDeviceNotification notification handle (" +
                       Long.toString(notification.getLong()) + ")",
                       notification.getLong() != 0);

            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test error code (event inducing)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            try {
                latch.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(CallbackObjectTest.class.getName())
                    .log(Level.SEVERE, null, ex);
            }

            assertTrue(hasEventFired);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DeleteDeviceNotification error value (short)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testDeviceNotification3() {
        // Read a signed short int from the PLC (1 Byte)
        attr.setCbLength(Integer.SIZE / Byte.SIZE);

        // Setup the buffer that is written to the PLC in order to induce events
        buffer = new JNIByteBuffer(Convert.IntToByteArr(AllTests.SHORT_VAL));

        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3, attr, user,
                notification);

            assertEquals("Test AddDeviceNotification error value (int)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test error code (event inducing)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            try {
                latch.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(CallbackObjectTest.class.getName())
                    .log(Level.SEVERE, null, ex);
            }

            assertTrue(hasEventFired);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DeleteDeviceNotification error value (int)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testDeviceNotification4() {
        // Read a signed short int from the PLC (1 Byte)
        attr.setCbLength(Byte.SIZE / Byte.SIZE);

        // Setup the buffer that is written to the PLC in order to induce events
        buffer = new JNIByteBuffer(Convert.BoolToByteArr(AllTests.BOOL_VAL));

        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4, attr, user,
                notification);

            assertEquals("Test AddDeviceNotification error value (bool)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test error code (event inducing)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            try {
                latch.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(CallbackObjectTest.class.getName())
                    .log(Level.SEVERE, null, ex);
            }

            assertTrue(hasEventFired);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DeleteDeviceNotification error value (bool)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testDeviceNotification5() {
        // Read a signed short int from the PLC (1 Byte)
        attr.setCbLength(Float.SIZE / Byte.SIZE);

        // Setup the buffer that is written to the PLC in order to induce events
        buffer = new JNIByteBuffer(Convert.FloatToByteArr(AllTests.FLOAT_VAL));

        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5, attr, user,
                notification);

            assertEquals("Test AddDeviceNotification error value (float)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test error code (event inducing)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            try {
                latch.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(CallbackObjectTest.class.getName())
                    .log(Level.SEVERE, null, ex);
            }

            assertTrue(hasEventFired);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DeleteDeviceNotification error value (float)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testDeviceNotification6() {
        // Read a signed short int from the PLC (1 Byte)
        attr.setCbLength(Double.SIZE / Byte.SIZE);

        // Setup the buffer that is written to the PLC in order to induce events
        buffer =
            new JNIByteBuffer(Convert.DoubleToByteArr(AllTests.DOUBLE_VAL));

        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6, attr, user,
                notification);

            assertEquals("Test AddDeviceNotification error value (double)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test error code (event inducing)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            try {
                latch.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(CallbackObjectTest.class.getName())
                    .log(Level.SEVERE, null, ex);
            }

            assertTrue(hasEventFired);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DeleteDeviceNotification error value (double)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testDeviceNotification7() {
        // Read a signed short int from the PLC (1 Byte)
        attr.setCbLength(AllTests.STRING_VAL.getBytes().length);

        // Setup the buffer that is written to the PLC in order to induce events
        buffer = new JNIByteBuffer(
            Convert.StringToByteArr(AllTests.STRING_VAL, true));

        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7, attr, user,
                notification);

            assertEquals("Test AddDeviceNotification error value (string)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test error code (event inducing)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            try {
                latch.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                Logger.getLogger(CallbackObjectTest.class.getName())
                    .log(Level.SEVERE, null, ex);
            }

            assertTrue(hasEventFired);

            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);

            assertEquals("Test DeleteDeviceNotification error value (string)",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }
}