package de.beckhoff.jni.tcads.amsaddr.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import de.beckhoff.jni.tcads.AmsNetId;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class AmsAddrStringTest extends TestCase {
    static final AmsNetId NEW_AMSADDR_NET_ID =
        new AmsNetId(new char[] {172, 16, 4, 220, 1, 1});
    static final String NEW_AMSADDR_NET_ID_STRING = "172.16.4.220.1.1";

    private AmsAddr addr;

    public AmsAddrStringTest(String name) { super(name); }

    @Override
    protected void setUp() {
        addr = new AmsAddr();
    }

    @Override
    protected void tearDown() {}

    public void testSetNetIdString0() {
        boolean exceptionThrown = false;
        try {
            addr.setNetIdStringEx("X");
        } catch (IllegalArgumentException ex) {
            exceptionThrown = true;
        }
        assertTrue(
            "Expect the exception to be thrown when the format is invalid",
            exceptionThrown);
    }

    public void testSetNetIdString1() {
        addr.setNetIdString(NEW_AMSADDR_NET_ID_STRING);

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            assertArrayEquals("Test new AmsNetId value (set via string).",
                              NEW_AMSADDR_NET_ID.getCharArr(),
                              addr.getNetId().getCharArr());
        } else {
            assertEquals("Test new AmsNetId value (set via string).",
                         NEW_AMSADDR_NET_ID_STRING, addr.getNetIdString());
        }
    }

    public void testSetNetIdString2() {
        addr.setNetIdStringEx(NEW_AMSADDR_NET_ID_STRING);

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            assertArrayEquals("Test new AmsNetId value (set via string).",
                              NEW_AMSADDR_NET_ID.getCharArr(),
                              addr.getNetId().getCharArr());
        } else {
            assertEquals("Test new AmsNetId value (set via string).",
                         NEW_AMSADDR_NET_ID_STRING, addr.getNetIdString());
        }
    }

    public void testGetNetIdString() {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            addr.setNetId(NEW_AMSADDR_NET_ID);

            assertEquals("Test AmsNetId string conversion",
                         NEW_AMSADDR_NET_ID_STRING, addr.getNetIdString());
        } else {
            // Nothing to test here
        }
    }
}