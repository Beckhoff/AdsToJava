package de.beckhoff.jni.tcads.adscalldllfunctionex.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.JNIBool;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class AdsAmsPortEnabledExTest extends TestCase {
    private final static boolean PORT_OPEN = true;

    long port;
    long err;
    AmsAddr addr;
    JNIBool bool;

    public AdsAmsPortEnabledExTest(String name) { super(name); }

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

    public void testPortEnabledEx1() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
                bool = new JNIBool();
                err = AdsCallDllFunction.adsAmsPortEnabledEx(port, bool);

                assertEquals("PortEnabledEx1 check error",
                             AdsCallDllFunction.ADSERR_NO_ERR, err);

                assertEquals("PortEnabledEx1 check value", PORT_OPEN,
                             bool.getBool());
            } else {
                bool = new JNIBool();
                err = AdsCallDllFunction.adsAmsPortEnabledEx(port, bool);

                assertEquals(
                    "PortEnabledEx1 check error (service not supported)",
                    AdsCallDllFunction.ADSERR_SRVICE_NOT_SUPP, err);
            }
        } else {
            fail();
        }
    }

    public void testPortEnabledEx2() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
                // Close the port
                err = AdsCallDllFunction.adsPortCloseEx(port);

                bool = new JNIBool();
                err = AdsCallDllFunction.adsAmsPortEnabledEx(port, bool);

                assertEquals("PortEnabledEx2 check error",
                             AdsCallDllFunction.ADSERR_ADSPORT_CLOSED, err);

                assertEquals("PortEnabledEx2 check value", !PORT_OPEN,
                             bool.getBool());
            } else {
                // Do no test since it's already covered above
            }
        } else {
            fail();
        }
    }

    public void testPortEnabledExLongNull() {
        if ((port >= AllTests.ADS_PORTNUMBER_MIN) &
            (port <= AllTests.ADS_PORTNUMBER_MAX) & (err == 0)) {
            bool = null;
            err = AdsCallDllFunction.adsAmsPortEnabledEx(port, bool);

            assertEquals("PortEnabledEx check error",
                         AdsCallDllFunction.ADSERR_INV_PARAM_VALS2, err);
        } else {
            fail();
        }
    }
}