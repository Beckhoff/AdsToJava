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
public class ReadRequestTest extends TestCase {
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer buffer;
    long port, err;

    public ReadRequestTest(String name) { super(name); }

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

    public void testReadRequestTest1() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(Byte.SIZE / Byte.SIZE);
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test ReadRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequest (byte)", AllTests.BYTE_VALUE_DEFAULT,
                         Convert.ByteArrToByte(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testReadRequestTest2() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(Short.SIZE / Byte.SIZE);
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test ReadRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequest (short)",
                         AllTests.SHORT_VALUE_DEFAULT,
                         Convert.ByteArrToShort(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testReadRequestTest3() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test ReadRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequest (int)", AllTests.INT_VALUE_DEFAULT,
                         Convert.ByteArrToInt(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testReadRequestTest4() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(Byte.SIZE / Byte.SIZE);
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test ReadRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequest (bool)", AllTests.BOOL_VALUE_DEFAULT,
                         Convert.ByteArrToBool(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testReadRequestTest5() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(Float.SIZE / Byte.SIZE);

            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test ReadRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequest (float)",
                         AllTests.FLOAT_VALUE_DEFAULT,
                         Convert.ByteArrToFloat(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testReadRequestTest6() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(Double.SIZE / Byte.SIZE);
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test ReadRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequest (double)",
                         AllTests.DOUBLE_VALUE_DEFAULT,
                         Convert.ByteArrToDouble(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testReadRequestTest7() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(AllTests.STRING_VAL.getBytes().length);
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test ReadRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test ReadRequest (string)",
                         AllTests.STRING_VALUE_DEFAULT,
                         Convert.ByteArrToString(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testReadRequestInvalidIGroup() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, ZERO_VAL, AllTests.INDEX_OFF3, buffer.getUsedBytesCount(),
                buffer);

            assertEquals("Test ReadRequest error value",
                         AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);
        } else {
            fail();
        }
    }

    public void testReadRequestAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            addr = null;

            buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test ReadRequest error value",
                         AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
        } else {
            fail();
        }
    }

    public void testReadRequestBuffNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = null;
            err = AdsCallDllFunction.adsSyncReadReq(addr, AllTests.INDEX_GROUP,
                                                    AllTests.INDEX_OFF3,
                                                    ZERO_VAL, buffer);

            assertEquals("Test ReadRequest error value",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }
}