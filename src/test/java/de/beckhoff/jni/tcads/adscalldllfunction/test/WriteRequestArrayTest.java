package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class WriteRequestArrayTest extends TestCase {
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer buffer;
    long port, err;

    public WriteRequestArrayTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();

        port = AdsCallDllFunction.adsPortOpen();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
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

        AdsCallDllFunction.adsPortClose();
    }

    public void testWriteRequestArrayTest() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(Convert.IntToByteArr(AllTests.INT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqArray(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequest-Check (byte)", AllTests.INT_VAL,
                         Convert.ByteArrToInt(buffer.getByteArray()));
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testWriteRequestArrayInvalidIGroup() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(Convert.IntToByteArr(AllTests.INT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqArray(
                addr, ZERO_VAL, AllTests.INDEX_OFF7 * 2,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testWriteRequestArrayAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            addr = null;
            buffer = new JNIByteBuffer(Convert.IntToByteArr(AllTests.INT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqArray(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }
}