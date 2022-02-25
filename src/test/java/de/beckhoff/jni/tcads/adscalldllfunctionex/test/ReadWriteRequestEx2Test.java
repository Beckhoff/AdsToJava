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
public class ReadWriteRequestEx2Test extends TestCase {
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer request_buffer, response_buffer;
    JNILong lengthReturn;
    long port, err;

    public ReadWriteRequestEx2Test(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();
        response_buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
        lengthReturn = new JNILong();

        port = AdsCallDllFunction.adsPortOpenEx();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortCloseEx(port);
    }

    public void testReadWriteRequestEx2Handle1() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_BYTE_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_BYTE_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx2 hdl-test (byte)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2Handle2() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_SHORT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_SHORT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx2 hdl-test (short)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2Handle3() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx2 hdl-test (int)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2Handle4() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_BOOL_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_BOOL_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx2 hdl-test (bool)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2Handle5() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_FLOAT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_FLOAT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx2 hdl-test (float)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2Handle6() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_DOUBLE_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_DOUBLE_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx2 hdl-test (double)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2Handle7() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_STRING_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_STRING_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx2 hdl-test (string)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2SymbolInfo() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_INFOBYNAMEEX, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx2", AdsCallDllFunction.ADSERR_NO_ERR,
                     err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength",
                     response_buffer.getUsedBytesCount(),
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2InvalidIGroup() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
                port, addr, ZERO_VAL, ZERO_VAL,
                response_buffer.getUsedBytesCount(), response_buffer,
                AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
                AllTests.NAME_BUFFER_INT_VALUE, lengthReturn);

            assertEquals("ReadWriteRequestEx2 invalid index group test",
                         AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);

            assertEquals("Test ReadWriteRequestEx2 pcbLength", ZERO_VAL,
                         lengthReturn.getLong());
        } else {
            fail();
        }
    }

    public void testReadWriteRequestEx2InvSymName() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INVALID_SYMNAME.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INVALID_SYMNAME, lengthReturn);

        assertEquals("ReadWriteRequestEx2 invalid symbol name test",
                     AdsCallDllFunction.ADSERR_SYMB_NOT_FOUND, err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength", ZERO_VAL,
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2AddrNull() {
        addr = null;
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx2 AmsAddr null test",
                     AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength", ZERO_VAL,
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2BuffNull1() {
        response_buffer = null;
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            ZERO_VAL, response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE, lengthReturn);

        assertEquals("ReadWriteRequestEx2 AmsAddr null test",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength", ZERO_VAL,
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2BuffNull2() {
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer, ZERO_VAL,
            AllTests.NAME_BUFFER_NULL, lengthReturn);

        assertEquals("ReadWriteRequestEx2 AmsAddr null test",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);

        assertEquals("Test ReadWriteRequestEx2 pcbLength", ZERO_VAL,
                     lengthReturn.getLong());
    }

    public void testReadWriteRequestEx2HandleDeprecated() {
        long lengthReturnLong = 0;
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE, lengthReturnLong);

        assertEquals("ReadWriteRequestEx2Deprecated hdl-test (int)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);

        assertEquals("Test ReadWriteRequestEx2Deprecated pcbLength", ZERO_VAL,
                     lengthReturnLong);
    }
}