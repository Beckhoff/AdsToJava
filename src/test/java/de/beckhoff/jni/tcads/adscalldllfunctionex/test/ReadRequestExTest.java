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
public class ReadRequestExTest extends TestCase {
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer buffer;
    JNILong lengthReturn;
    long port, err;

    public ReadRequestExTest(String name) { super(name); }

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
        AdsCallDllFunction.adsPortClose();
    }

    public void testReadRequestExTest1() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Byte.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx (byte)",
                         AllTests.BYTE_VALUE_DEFAULT,
                         Convert.ByteArrToByte(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestExTest2() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Short.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx (short)",
                         AllTests.SHORT_VALUE_DEFAULT,
                         Convert.ByteArrToShort(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestExTest3() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx (int)", AllTests.INT_VALUE_DEFAULT,
                         Convert.ByteArrToInt(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestExTest4() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Byte.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx (bool)",
                         AllTests.BOOL_VALUE_DEFAULT,
                         Convert.ByteArrToBool(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestExTest5() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Float.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx (float)",
                         AllTests.FLOAT_VALUE_DEFAULT,
                         Convert.ByteArrToFloat(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestExTest6() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Double.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx (double)",
                         AllTests.DOUBLE_VALUE_DEFAULT,
                         Convert.ByteArrToDouble(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestExTest7() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(
                AllTests.STRING_VALUE_DEFAULT.getBytes().length);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx (string)",
                         AllTests.STRING_VALUE_DEFAULT,
                         Convert.ByteArrToString(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx pcbLength",
                         buffer.getUsedBytesCount(), lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadRequestExInvalidIGroup() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx(
                addr, ZERO_VAL, AllTests.INDEX_OFF3, buffer.getUsedBytesCount(),
                buffer, lengthReturn);

            assertEquals("Test ReadRequestEx error value",
                         AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);
        } else {
            fail();
        }
    }

    public void testReadRequestExAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            addr = null;

            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer, lengthReturn);

            assertEquals("Test ReadRequestEx error value",
                         AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);

            assertEquals("Fail ReadRequestEx (int)", ZERO_VAL,
                         Convert.ByteArrToInt(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testReadRequestExBuffNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = null;
            lengthReturn = new JNILong();
            err = AdsCallDllFunction.adsSyncReadReqEx(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3, ZERO_VAL,
                buffer, lengthReturn);

            assertEquals("Test ReadRequestEx error value",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }

    public void testReadRequestExDeprecated() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            long lengthReturnLong = 0;
            err = AdsCallDllFunction.adsSyncReadReqEx(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer, lengthReturnLong);

            assertEquals("Test ReadRequestExDeprecated error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequestEx deprecated",
                         AllTests.INT_VALUE_DEFAULT,
                         Convert.ByteArrToInt(buffer.getByteArray()));

            assertEquals("Test ReadRequestEx deprecated pcbLength", ZERO_VAL,
                         lengthReturnLong);
        } else {
            fail();
        }
    }
}