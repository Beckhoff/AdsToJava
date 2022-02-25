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
public class WriteRequestTest extends TestCase {
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer buffer;
    long port, err;

    public WriteRequestTest(String name) { super(name); }

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

        AdsCallDllFunction.adsPortClose();
    }

    public void testWriteRequestTest1() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer =
                new JNIByteBuffer(Convert.ByteToByteArr(AllTests.BYTE_VAL));
            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF1,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequest-Check (byte)", AllTests.BYTE_VAL,
                         Convert.ByteArrToByte(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testWriteRequestTest2() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer =
                new JNIByteBuffer(Convert.ShortToByteArr(AllTests.SHORT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF2,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequest-Check (short)", AllTests.SHORT_VAL,
                         Convert.ByteArrToShort(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testWriteRequestTest3() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(Convert.IntToByteArr(AllTests.INT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer);

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
            fail();
        }
    }

    public void testWriteRequestTest4() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer =
                new JNIByteBuffer(Convert.BoolToByteArr(AllTests.BOOL_VAL));
            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF4,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequest-Check (bool)", AllTests.BOOL_VAL,
                         Convert.ByteArrToBool(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    public void testWriteRequestTest5() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer =
                new JNIByteBuffer(Convert.FloatToByteArr(AllTests.FLOAT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF5,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequest-Check (float)", AllTests.FLOAT_VAL,
                         Convert.ByteArrToFloat(buffer.getByteArray()),
                         AllTests.FLOAT_COMPARE_DELTA);
        } else {
            fail();
        }
    }

    public void testWriteRequestTest6() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer =
                new JNIByteBuffer(Convert.DoubleToByteArr(AllTests.DOUBLE_VAL));
            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF6,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequest-Check (double)",
                         AllTests.DOUBLE_VAL,
                         Convert.ByteArrToDouble(buffer.getByteArray()),
                         AllTests.FLOAT_COMPARE_DELTA);
        } else {
            fail();
        }
    }

    public void testWriteRequestTest7() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(
                Convert.StringToByteArr(AllTests.STRING_VAL, true));
            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            // Check whether this had effect on the PLC or not
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF7,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest-Check error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            assertEquals("Test WriteRequest-Check (string)",
                         AllTests.STRING_VAL,
                         Convert.ByteArrToString(buffer.getByteArray()));
        } else {
            fail();
        }
    }

    //    public void testWriteRequestMemoryLeakTest()
    //    {
    //        if ((port >= AllTests.ADS_PORTNUMBER_MIN)
    //                & (port <= AllTests.ADS_PORTNUMBER_MAX))
    //        {
    //
    //            for (int i = 0; i < 1000000; ++i) {
    //
    //                buffer = new
    //                JNIByteBuffer(Convert.DoubleToByteArr(AllTests.DOUBLE_VAL));
    //                err = AdsCallDllFunction.adsSyncWriteReq(
    //                        addr,
    //                        AllTests.INDEX_GROUP,
    //                        AllTests.INDEX_OFF6,
    //                        buffer.getUsedBytesCount(),
    //                        buffer);
    //
    //                assertEquals("Test WriteRequest error value",
    //                        AdsCallDllFunction.ADSERR_NO_ERR,
    //                        err);
    //            }
    //
    //            // Check whether this had effect on the PLC or not
    //            err = AdsCallDllFunction.adsSyncReadReq(
    //                    addr,
    //                    AllTests.INDEX_GROUP,
    //                    AllTests.INDEX_OFF6,
    //                    buffer.getUsedBytesCount(),
    //                    buffer);
    //
    //            assertEquals("Test WriteRequest-Check error value",
    //                    AdsCallDllFunction.ADSERR_NO_ERR,
    //                    err);
    //
    //            assertEquals("Test WriteRequest-Check (double)",
    //                    AllTests.DOUBLE_VAL,
    //                    Convert.ByteArrToDouble(buffer.getByteArray()),
    //                    AllTests.FLOAT_COMPARE_DELTA);
    //        } else {
    //            fail();
    //        }
    //    }

    public void testWriteRequestInvalidIGroup() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = new JNIByteBuffer(Convert.IntToByteArr(AllTests.INT_VAL));
            err = AdsCallDllFunction.adsSyncReadReq(
                addr, ZERO_VAL, AllTests.INDEX_OFF7 * 2,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);
        } else {
            fail();
        }
    }

    public void testWriteRequestAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            addr = null;

            buffer = new JNIByteBuffer(Convert.IntToByteArr(AllTests.INT_VAL));
            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AllTests.INDEX_GROUP, AllTests.INDEX_OFF3,
                buffer.getUsedBytesCount(), buffer);

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
        } else {
            fail();
        }
    }

    public void testWriteRequestInvalidBuff() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            buffer = null;
            err = AdsCallDllFunction.adsSyncWriteReq(addr, AllTests.INDEX_GROUP,
                                                     AllTests.INDEX_OFF3,
                                                     ZERO_VAL, buffer);

            assertEquals("Test WriteRequest error value",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }
}