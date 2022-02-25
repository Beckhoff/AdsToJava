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
public class ReadRequestEx2Test extends TestCase {
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer buffer;
    JNILong lengthReturn;
    long port, err;

    public ReadRequestEx2Test(String name) { super(name); }

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
        AdsCallDllFunction.adsPortCloseEx(port);
    }

    public void testReadRequestEx2Test1() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Byte.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx2 error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx2 (byte)",
                         AllTests.BYTE_VALUE_DEFAULT,
                         Convert.ByteArrToByte(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx2 pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestEx2Test2() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Short.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx2 (short)",
                         AllTests.SHORT_VALUE_DEFAULT,
                         Convert.ByteArrToShort(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx2 pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestEx2Test3() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx2 error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx2 (int)",
                         AllTests.INT_VALUE_DEFAULT,
                         Convert.ByteArrToInt(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx2 pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestEx2Test4() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Byte.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx2 error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx2 (bool)",
                         AllTests.BOOL_VALUE_DEFAULT,
                         Convert.ByteArrToBool(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx2 pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestEx2Test5() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Float.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx2 error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx2 (float)",
                         AllTests.FLOAT_VALUE_DEFAULT,
                         Convert.ByteArrToFloat(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx2 pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestEx2Test6() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Double.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx2 error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx2 (bool)",
                         AllTests.DOUBLE_VALUE_DEFAULT,
                         Convert.ByteArrToDouble(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx2 pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestEx2Test7() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(
                AllTests.STRING_VALUE_DEFAULT.getBytes().length);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx2 error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx2 (string)",
                         AllTests.STRING_VALUE_DEFAULT,
                         Convert.ByteArrToString(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx2 pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestEx2Deprecated() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            long lengthReturnLong = 0;
            err = AdsCallDllFunction.adsSyncReadReqEx2(
                port, addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer, lengthReturnLong);

            assertEquals("Test ReadRequestEx2Deprecated error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx2 deprecated",
                         AllTests.INT_VALUE_DEFAULT,
                         Convert.ByteArrToInt(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx2 deprecated pcbLength", ZERO_VAL,
                         lengthReturnLong);
        } else {
            fail();
        }
    }
}