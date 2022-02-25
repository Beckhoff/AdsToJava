/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.JNIBool;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class AdsAmsPortEnabledTest extends TestCase {
    private final static boolean PORT_OPEN = true;

    long port;
    long err;
    AmsAddr addr;
    JNIBool bool;

    public AdsAmsPortEnabledTest(String name) { super(name); }

    @Override
    protected void setUp() {
        err = 0;
        addr = new AmsAddr();

        port = AdsCallDllFunction.adsPortOpen();
        err = AdsCallDllFunction.getLocalAddress(addr);
        addr.setPort(AllTests.DEVICE_PORT);
    }

    @Override
    protected void tearDown() {
        AdsCallDllFunction.adsPortClose();
    }

    public void testPortEnabled1() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
                bool = new JNIBool();
                err = AdsCallDllFunction.adsAmsPortEnabled(bool);

                assertEquals("PortEnabled1 check error",
                             AdsCallDllFunction.ADSERR_NO_ERR, err);

                assertEquals("PortEnabled1 check value", PORT_OPEN,
                             bool.getBool());
            } else {
                bool = new JNIBool();
                err = AdsCallDllFunction.adsAmsPortEnabled(bool);

                assertEquals("PortEnabled1 check error (service not supported)",
                             AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);
            }
        } else {
            fail();
        }
    }

    public void testPortEnabled2() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
                // Close the port
                err = AdsCallDllFunction.adsPortClose();

                bool = new JNIBool();
                err = AdsCallDllFunction.adsAmsPortEnabled(bool);

                assertEquals("PortEnabled2 check error",
                             AdsCallDllFunction.ADSERR_ADSPORT_CLOSED, err);

                assertEquals("PortEnabled2 check value", !PORT_OPEN,
                             bool.getBool());
            } else {
                // Do no test since it's already covered above
            }
        } else {
            fail();
        }
    }

    public void testPortEnabledLongNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            bool = null;
            err = AdsCallDllFunction.adsAmsPortEnabled(bool);

            assertEquals("PortEnabled check error",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }
}