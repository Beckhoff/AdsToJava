package de.beckhoff.jni.tcads.amsaddr.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import de.beckhoff.jni.tcads.AmsNetId;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class AmsAddrTest extends TestCase {
    static final AmsNetId INIT_AMSADDR_NET_ID = new AmsNetId();
    static final int INIT_AMSADDR_PORT = 0;

    static final int NEW_AMSADDR_PORT = 801;
    static final AmsNetId NEW_AMSADDR_NET_ID =
        new AmsNetId(new char[] {172, 16, 4, 220, 1, 1});
    static final char NEW_AMSADDR_NET_ID_PART = 255;

    private AmsAddr addr;

    public AmsAddrTest(String name) { super(name); }

    @Override
    protected void setUp() {
        addr = new AmsAddr();
    }

    @Override
    protected void tearDown() {}

    public void testInitialization1() {
        assertArrayEquals("Test initial AmsNetId value",
                          INIT_AMSADDR_NET_ID.getCharArr(),
                          addr.getNetId().getCharArr());

        assertEquals("Test initial port value", INIT_AMSADDR_PORT,
                     addr.getPort());
    }

    public void testSetPort() {
        addr.setPort(NEW_AMSADDR_PORT);
        assertEquals("Test new AmsAddr port", NEW_AMSADDR_PORT, addr.getPort());
    }

    public void testSetNetId() {
        addr.setNetId(NEW_AMSADDR_NET_ID);
        assertEquals("Test new AmdNetId value", NEW_AMSADDR_NET_ID,
                     addr.getNetId());
    }

    public void testSetNetIdPart1() {
        AmsNetId netId = new AmsNetId();

        for (int i = 0; i < netId.getCharArr().length; ++i) {
            addr.setNetIDPart(NEW_AMSADDR_NET_ID_PART, i);

            char[] arr = netId.getCharArr();
            arr[i] = NEW_AMSADDR_NET_ID_PART;
            netId.setCharArr(arr);

            assertEquals("Test partially new AmsNetId values. Part: " + i,
                         netId.getCharArr()[i], addr.getNetIDPart(i));

            if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
                assertArrayEquals("Test new AmsNetId values. Part: " + i,
                                  netId.getCharArr(),
                                  addr.getNetId().getCharArr());
            } else {
                // Nothing to test here
            }
        }
    }

    public void testSetNetIdPart2() {
        for (int i = 0; i < addr.getNetId().getCharArr().length; i++) {
            switch (i) {
            case 0:
                addr.setNetIdPart0(NEW_AMSADDR_NET_ID_PART);
                assertEquals("Test partially new AmsNetId values",
                             NEW_AMSADDR_NET_ID_PART, addr.getNetIdPart0());
                break;
            case 1:
                addr.setNetIdPart1(NEW_AMSADDR_NET_ID_PART);
                assertEquals("Test partially new AmsNetId values",
                             NEW_AMSADDR_NET_ID_PART, addr.getNetIdPart1());
                break;
            case 2:
                addr.setNetIdPart2(NEW_AMSADDR_NET_ID_PART);
                assertEquals("Test partially new AmsNetId values",
                             NEW_AMSADDR_NET_ID_PART, addr.getNetIdPart2());
                break;
            case 3:
                addr.setNetIdPart3(NEW_AMSADDR_NET_ID_PART);
                assertEquals("Test partially new AmsNetId values",
                             NEW_AMSADDR_NET_ID_PART, addr.getNetIdPart3());
                break;
            case 4:
                addr.setNetIdPart4(NEW_AMSADDR_NET_ID_PART);
                assertEquals("Test partially new AmsNetId values",
                             NEW_AMSADDR_NET_ID_PART, addr.getNetIdPart4());
                break;
            case 5:
                addr.setNetIdPart5(NEW_AMSADDR_NET_ID_PART);
                assertEquals("Test partially new AmsNetId values",
                             NEW_AMSADDR_NET_ID_PART, addr.getNetIdPart5());
                break;
            default:
                break;
            }
        }
    }

    private long changeJniWrapperDllVersion(long newValue) {
        try {
            Field field = AdsCallDllFunction.class.getDeclaredField(
                "jniWrapperDllVersion");
            boolean wasAccessible = field.canAccess(null);
            field.setAccessible(true);
            long oldValue = field.getLong(null);
            field.setLong(null, newValue);
            field.setAccessible(wasAccessible);
            return oldValue;
        } catch (Exception ex) {
            assertFalse("The field should exist", true);
            return 0;
        }
    }

    public void testSetNetIdPart10() {
        long oldValue = changeJniWrapperDllVersion(1);
        testSetNetIdPart1();
        changeJniWrapperDllVersion(oldValue);
    }

    public void testSetNetIdPart20() {
        long oldValue = changeJniWrapperDllVersion(1);
        testSetNetIdPart2();
        changeJniWrapperDllVersion(oldValue);
    }
}
