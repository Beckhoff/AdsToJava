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
public class WriteRequestExArrayTest extends TestCase {
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer buffer;
    JNILong lengthReturn;
    long port, err;

    public WriteRequestExArrayTest(String name) { super(name); }

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

    public void testWriteRequestArrayExTest1() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer =
                new JNIByteBuffer(Convert.ByteToByteArr(AllTests.BYTE_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqExArray(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequestExArray error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestExArray-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestExArray-Check (byte)",
                         AllTests.BYTE_VAL,
                         Convert.ByteArrToByte(buffer.getByteArray()));

            assertEquals("Test WriteRequestExArray-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestArrayExTest2() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer =
                new JNIByteBuffer(Convert.ShortToByteArr(AllTests.SHORT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqExArray(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequestExArray error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestExArray-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestExArray-Check (short)",
                         AllTests.SHORT_VAL,
                         Convert.ByteArrToShort(buffer.getByteArray()));

            assertEquals("Test WriteRequestExArray-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestArrayExTest3() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Convert.IntToByteArr(AllTests.INT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqExArray(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequestExArray error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestExArray-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestExArray-Check (int)",
                         AllTests.INT_VAL,
                         Convert.ByteArrToInt(buffer.getByteArray()));

            assertEquals("Test WriteRequestExArray-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestArrayExTest4() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer =
                new JNIByteBuffer(Convert.BoolToByteArr(AllTests.BOOL_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqExArray(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequestExArray error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestExArray-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestExArray-Check (bool)",
                         AllTests.BOOL_VAL,
                         Convert.ByteArrToBool(buffer.getByteArray()));

            assertEquals("Test WriteRequestExArray-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestArrayExTest5() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer =
                new JNIByteBuffer(Convert.FloatToByteArr(AllTests.FLOAT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqExArray(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequestExArray error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestExArray-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestExArray-Check (float)",
                         AllTests.FLOAT_VAL,
                         Convert.ByteArrToFloat(buffer.getByteArray()));

            assertEquals("Test WriteRequestExArray-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestArrayExTest6() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer =
                new JNIByteBuffer(Convert.DoubleToByteArr(AllTests.DOUBLE_VAL));
            err = AdsCallDllFunction.adsSyncWriteReqExArray(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequestExArray error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestExArray-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestExArray-Check (double)",
                         AllTests.DOUBLE_VAL,
                         Convert.ByteArrToDouble(buffer.getByteArray()));

            assertEquals("Test WriteRequestExArray-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestArrayExTest7() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(
                Convert.StringToByteArr(AllTests.STRING_VAL, true));
            err = AdsCallDllFunction.adsSyncWriteReqExArray(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequestExArray error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test WriteRequestExArray-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequestExArray-Check (string)",
                         AllTests.STRING_VAL,
                         Convert.ByteArrToString(buffer.getByteArray()));

            assertEquals("Test WriteRequestExArray-Check lengthReturn",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testWriteRequestExArrayInvalidIGroup() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            err = AdsCallDllFunction.adsSyncWriteReqExArray(
                port, addr, ZERO_VAL, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequestExArray error value",
                         AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);
        } else {
            fail();
        }
    }

    public void testWriteRequestExArrayAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            addr = null;

            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            err = AdsCallDllFunction.adsSyncWriteReqExArray(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer.getByteArray());

            assertEquals("Test WriteRequestExArray error value",
                         AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);

            assertEquals("Fail WriteRequestExArray (int)", ZERO_VAL,
                         Convert.ByteArrToInt(buffer.getByteArray()));
        } else {
            fail();
        }
    }
}