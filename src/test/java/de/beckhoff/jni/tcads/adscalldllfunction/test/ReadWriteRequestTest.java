package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ReadWriteRequestTest extends TestCase {
    static final int ZERO_VAL = 0;

    AmsAddr addr;
    JNIByteBuffer request_buffer, response_buffer;
    long port, err;

    public ReadWriteRequestTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();
        response_buffer = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);

        port = AdsCallDllFunction.adsPortOpen();
        addr.setNetIdStringEx(AllTests.DEVICE_AMSADDR);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortClose();
    }

    public void testReadWriteRequestHandle1() {
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_BYTE_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_BYTE_VALUE);

        assertEquals("ReadWriteRequest hdl-test (sintVar)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);
    }

    public void testReadWriteRequestHandle2() {
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_SHORT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_SHORT_VALUE);

        assertEquals("ReadWriteRequest hdl-test (intVar)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);
    }

    public void testReadWriteRequestHandle3() {
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE);

        assertEquals("ReadWriteRequest hdl-test (dintVar)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);
    }

    public void testReadWriteRequestHandle4() {
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_BOOL_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_BOOL_VALUE);

        assertEquals("ReadWriteRequest hdl-test (boolVar)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);
    }

    public void testReadWriteRequestHandle5() {
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_FLOAT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_FLOAT_VALUE);

        assertEquals("ReadWriteRequest hdl-test (floatVar)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);
    }

    public void testReadWriteRequestHandle6() {
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_DOUBLE_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_DOUBLE_VALUE);

        assertEquals("ReadWriteRequest hdl-test (doubleVar)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);
    }

    public void testReadWriteRequestHandle7() {
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_STRING_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_STRING_VALUE);

        assertEquals("ReadWriteRequest hdl-test (stringVar)",
                     AdsCallDllFunction.ADSERR_NO_ERR, err);
    }

    public void testReadWriteRequestSymbolInfo() {
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_INFOBYNAMEEX, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE);

        assertEquals("ReadWriteRequest", AdsCallDllFunction.ADSERR_NO_ERR, err);
    }

    public void testReadWriteRequestInvalidIGroup() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.adsSyncReadWriteReq(
                addr, ZERO_VAL, ZERO_VAL, response_buffer.getUsedBytesCount(),
                response_buffer,
                AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
                AllTests.NAME_BUFFER_INT_VALUE);

            assertEquals("ReadWriteRequest invalid index group test",
                         AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testReadWriteRequestInvSymName() {
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INVALID_SYMNAME.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INVALID_SYMNAME);

        assertEquals("ReadWriteRequest invalid symbol name test",
                     AdsCallDllFunction.ADSERR_SYMB_NOT_FOUND, err);
    }

    public void testReadWriteRequestAddrNull() {
        addr = null;
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer,
            AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE);

        assertEquals("ReadWriteRequest AmsAddr null test",
                     AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
    }

    public void testReadWriteRequestBuffNull1() {
        response_buffer = null;
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL, ZERO_VAL,
            response_buffer, AllTests.NAME_BUFFER_INT_VALUE.getUsedBytesCount(),
            AllTests.NAME_BUFFER_INT_VALUE);

        assertEquals("ReadWriteRequest AmsAddr null test",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
    }

    public void testReadWriteRequestBuffNull2() {
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, ZERO_VAL,
            response_buffer.getUsedBytesCount(), response_buffer, ZERO_VAL,
            AllTests.NAME_BUFFER_NULL);

        assertEquals("ReadWriteRequest AmsAddr null test",
                     AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
    }
}