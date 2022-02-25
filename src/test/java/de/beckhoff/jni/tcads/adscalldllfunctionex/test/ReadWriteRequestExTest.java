package de.beckhoff.jni.tcads.adscalldllfunctionex.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.JNILong;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ReadWriteRequestExTest extends TestCase {
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer request_buffer, response_buffer;
    JNILong lengthReturn;
    long port, err;

    public ReadWriteRequestExTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();
        response_buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
        lengthReturn = new JNILong();

        port = AdsCallDllFunction.adsPortOpen();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortClose();
    }

    public void testReadWriteRequestExHandle1() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_BYTE_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_BYTE_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx hdl-test (byte)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExHandle2() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_SHORT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_SHORT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx hdl-test (short)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExHandle3() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx hdl-test (int)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExHandle4() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_BOOL_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_BOOL_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx hdl-test (bool)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExHandle5() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_FLOAT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_FLOAT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx hdl-test (float)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExHandle6() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_DOUBLE_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_DOUBLE_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx hdl-test (double)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExHandle7() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_STRING_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_STRING_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx hdl-test (string)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExSymbolInfo() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_INFOBYNAMEEX, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx", AdsCallDllFunction.ADSERR_NO_ERR,
                     err);

        assertEquals("Test ReadWriteRequestEx pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExInvalidIGroup() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            err = AdsCallDllFunction.adsSyncReadWriteReqEx(
                addr, ZERO_VAL, ZERO_VAL, response_buffer.getUsedBytesCount(),
                response_buffer,
                AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
                AllTests.NAME_BUFFER_INT_VALUE, lengthReturn);

            assertEquals("ReadWriteRequestEx invalid index group test",
                         AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);

            assertEquals("Test ReadWriteRequestEx pcbLength", ZERO_VAL,
                         lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadWriteRequestExInvSymName() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INVALID_SYMNAME.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INVALID_SYMNAME, lengthReturn);

        assertEquals("ReadWriteRequestEx invalid symbol name test",
                     AdsCallDllFunction.ADSERR_SYMB_NOT_FOUND, err);

        assertEquals("Test ReadWriteRequestEx pcbLength", ZERO_VAL,
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExAddrNull() {
        addr = null;
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx AmsAddr null test",
                     AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);

        assertEquals("Test ReadWriteRequestEx pcbLength", ZERO_VAL,
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExBuffNull1() {
        response_buffer = null;
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL, ZERO_VAL,
            response_buffer, AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx AmsAddr null test",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);

        assertEquals("Test ReadWriteRequestEx pcbLength", ZERO_VAL,
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExBuffNull2() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_NULL, lengthReturn);

        assertEquals("ReadWriteRequestEx AmsAddr null test",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);

        assertEquals("Test ReadWriteRequestEx pcbLength", ZERO_VAL,
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestExHandleDeprecated() {
        long lengthReturnLong = 0;
        err = AdsCallDllFunction.adsSyncReadWriteReqEx(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE, lengthReturnLong);

        assertEquals("ReadWriteRequestExDeprecated hdl-test (int)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestExDeprecated pcbLength", ZERO_VAL,
                     lengthReturnLong);
    }
}