package de.beckhoff.jni.tcads.adscalldllfunctionex.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.JNILong;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class WriteRequestExTest extends TestCase {
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer buffer;
    JNILong lengthReturn;
    long port, err;

    public WriteRequestExTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();

        port = AdsCallDllFunction.adsPortOpenEx();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        // Restore initial PLC-values
        buffer = new JNIByteBuffer(
            Convert.ByteToByteArr(AllTests.BYTE_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReqEx(
            port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
            buffer.getUsedBytesCount(), buffer);

        buffer = new JNIByteBuffer(
            Convert.ShortToByteArr(AllTests.SHORT_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReqEx(
            port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
            buffer.getUsedBytesCount(), buffer);

        buffer =
            new JNIByteBuffer(Convert.IntToByteArr(AllTests.INT_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReqEx(
            port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
            buffer.getUsedBytesCount(), buffer);

        buffer = new JNIByteBuffer(
            Convert.BoolToByteArr(AllTests.BOOL_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReqEx(
            port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
            buffer.getUsedBytesCount(), buffer);

        buffer = new JNIByteBuffer(
            Convert.FloatToByteArr(AllTests.FLOAT_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReqEx(
            port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
            buffer.getUsedBytesCount(), buffer);

        buffer = new JNIByteBuffer(
            Convert.DoubleToByteArr(AllTests.DOUBLE_VALUE_DEFAULT));
        err = AdsCallDllFunction.adsSyncWriteReqEx(
            port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
            buffer.getUsedBytesCount(), buffer);

        buffer = new JNIByteBuffer(
            Convert.StringToByteArr(AllTests.STRING_VALUE_DEFAULT, true));
        err = AdsCallDllFunction.adsSyncWriteReqEx(
            port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
            buffer.getUsedBytesCount(), buffer);

        AdsCallDllFunction.adsPortCloseEx(port);
    }

    public void testWriteRequestExTest1() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer =
                new JNIByteBuffer(Convert.ByteToByteArr(AllTests.BYTE_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestEx-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestEx-Check (byte)", AllTests.BYTE_VAL,
                         Convert.ByteArrToByte(buffer.getByteArray()));

            assertEquals("Test WriteRequestEx-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestExTest2() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer =
                new JNIByteBuffer(Convert.ShortToByteArr(AllTests.SHORT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestEx-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestEx-Check (short)",
                         AllTests.SHORT_VAL,
                         Convert.ByteArrToShort(buffer.getByteArray()));

            assertEquals("Test WriteRequestEx-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestExTest3() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Convert.IntToByteArr(AllTests.INT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestEx-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestEx-Check (int)", AllTests.INT_VAL,
                         Convert.ByteArrToInt(buffer.getByteArray()));

            assertEquals("Test WriteRequestEx-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestExTest4() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer =
                new JNIByteBuffer(Convert.BoolToByteArr(AllTests.BOOL_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestEx-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestEx-Check (bool)", AllTests.BOOL_VAL,
                         Convert.ByteArrToBool(buffer.getByteArray()));

            assertEquals("Test WriteRequestEx-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestExTest5() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer =
                new JNIByteBuffer(Convert.FloatToByteArr(AllTests.FLOAT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestEx-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestEx-Check (float)",
                         AllTests.FLOAT_VAL,
                         Convert.ByteArrToFloat(buffer.getByteArray()));

            assertEquals("Test WriteRequestEx-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestExTest6() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer =
                new JNIByteBuffer(Convert.DoubleToByteArr(AllTests.DOUBLE_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestEx-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestEx-Check (double)",
                         AllTests.DOUBLE_VAL,
                         Convert.ByteArrToDouble(buffer.getByteArray()));

            assertEquals("Test WriteRequestEx-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestExTest7() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(
                Convert.StringToByteArr(AllTests.STRING_VAL, true));
            err = AdsCallDllFunction.adsSyncWriteReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestEx-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestEx-Check (string)",
                         AllTests.STRING_VAL,
                         Convert.ByteArrToString(buffer.getByteArray()));

            assertEquals("Test WriteRequestEx-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestExInvalidIGroup() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            err = AdsCallDllFunction.adsSyncWriteReqEx(
                port, addr, ZERO_VAL, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequestEx error value",
                         AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);
        } else {
            fail();
        }
    }

    public void testWriteRequestExAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            addr = null;

            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            err = AdsCallDllFunction.adsSyncWriteReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequestEx error value",
                         AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);

            assertEquals("Fail WriteRequestEx (int)", ZERO_VAL,
                         Convert.ByteArrToInt(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testWriteRequestExBuffNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = null;
            err = AdsCallDllFunction.adsSyncWriteReqEx(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3, ZERO_VAL,
                buffer);

            assertEquals("Test WriteRequestEx error value",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }
}
