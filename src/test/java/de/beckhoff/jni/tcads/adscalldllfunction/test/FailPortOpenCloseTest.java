package de.beckhoff.jni.tcads.adscalldllfunction.test;

import de.beckhoff.jni.tcads.AdsCallDllFunction;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class FailPortOpenCloseTest extends TestCase {
    long port, err;

    public FailPortOpenCloseTest(String name) { super(name); }

    @Override
    protected void setUp() {
        port = 0;
        err = 0;
    }

    @Override
    protected void tearDown() {}

    public void testCloseWithoutOpen() {
        err = AdsCallDllFunction.adsPortClose();
        assertEquals("Fail Ads PortClose (without prior PortOpen)",
                     AdsCallDllFunction.ADSERR_ADSPORT_CLOSED, err);
    }
}