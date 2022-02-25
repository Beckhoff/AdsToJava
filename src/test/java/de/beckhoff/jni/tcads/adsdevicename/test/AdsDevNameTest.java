package de.beckhoff.jni.tcads.adsdevicename.test;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.tcads.AdsDevName;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class AdsDevNameTest extends TestCase {
    private AdsDevName deviceName1;
    private AdsDevName deviceName2;

    public AdsDevNameTest(String name) { super(name); }

    @Override
    protected void setUp() {
        deviceName1 = new AdsDevName();
        deviceName2 = new AdsDevName(AllTests.DEVICE_NAME);
    }

    @Override
    protected void tearDown() {}

    public void testInitialization() {
        assertEquals("Test initial device name (constructor /wo parameter)",
                     AllTests.EMPTY_DEVICE_NAME, deviceName1.getDevName());

        assertEquals("Test initial device name (constructor /w parameter)",
                     AllTests.DEVICE_NAME, deviceName2.getDevName());
    }

    public void testSetDeviceName() {
        deviceName1.setDevName(AllTests.DEVICE_NAME);
        assertEquals("Test new device name", deviceName2.getDevName(),
                     deviceName1.getDevName());
    }
}