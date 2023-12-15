/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.beckhoff.jni.tcads.adscalldllfunctionex.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import java.util.Arrays;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class GetLocalAmsAddressExTest extends TestCase {
    AmsAddr addr;
    long port, err;

    public GetLocalAmsAddressExTest(String name) { super(name); }

    @Override
    protected void setUp() {
        addr = new AmsAddr();
        err = 0;
        port = AdsCallDllFunction.adsPortOpenEx();
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortCloseEx(port);
    }

    public void testGetLocalAddressEx() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            err = AdsCallDllFunction.getLocalAddressEx(port, addr);

            assertEquals("Test GetLocalAmsAddrEx error value",
                         AdsCallDllFunction.ADSERR_NO_ERR, err);

            String netId = addr.getNetIdString();
            String[] parts = netId.split("\\.");
            assertEquals("Test number of parts of the AMS Net ID", 6,
                         parts.length);
            assertFalse("127.0.0.1.1.1" == netId);
            assertFalse("0.0.0.0.1.1" == netId);
            assertFalse("0.0.0.0.0.0" == netId);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }

    public void testGetLocalAddressExPortClosed() {
        AdsCallDllFunction.adsPortCloseEx(port);

        err = AdsCallDllFunction.getLocalAddressEx(port, addr);
        assertEquals("Fail GetLocalAmsAddrEx (port is closed)",
                     AdsCallDllFunction.ADSERR_ADSPORT_CLOSED, err);
    }

    public void testGetLocalAddressExAddrNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX)) {
            addr = null;

            err = AdsCallDllFunction.getLocalAddressEx(port, addr);
            assertEquals("Fail GetLocalAmsAddrEx (addr is null)",
                         AdsCallDllFunction.ADSERR_INV_AMS_NETID, err);
        } else {
            fail("ADS port out of range: " + Long.toString(port));
        }
    }
}