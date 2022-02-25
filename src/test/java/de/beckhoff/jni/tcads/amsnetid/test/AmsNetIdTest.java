package de.beckhoff.jni.tcads.amsnetid.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.tcads.AmsNetId;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class AmsNetIdTest extends TestCase {
    static final char[] INIT_AMS_PARTS_EMPTY = new char[6];
    static final char[] INIT_AMS_PARTS = new char[] {172, 16, 4, 220, 1, 1};
    static final char[] NEW_AMS_PARTS = new char[] {172, 16, 4, 42, 1, 1};

    public AmsNetIdTest(String name) { super(name); }

    @Override
    protected void setUp() {}

    @Override
    protected void tearDown() {}

    public void testInitialization1() {
        AmsNetId amsNetId = new AmsNetId();
        assertArrayEquals("Test initial empty amsNetId parts",
                          INIT_AMS_PARTS_EMPTY, amsNetId.getCharArr());
    }

    public void testInitialization2() {
        AmsNetId amsNetId = new AmsNetId(INIT_AMS_PARTS);
        assertArrayEquals("Test initial amsNetID parts", INIT_AMS_PARTS,
                          amsNetId.getCharArr());
    }

    public void testSetAmsNetId() {
        AmsNetId amsNetId = new AmsNetId(INIT_AMS_PARTS);
        amsNetId.setCharArr(NEW_AMS_PARTS);
        assertArrayEquals("Test new amsNetID parts", NEW_AMS_PARTS,
                          amsNetId.getCharArr());
    }
}
