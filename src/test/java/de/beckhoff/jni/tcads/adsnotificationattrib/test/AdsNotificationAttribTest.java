package de.beckhoff.jni.tcads.adsnotificationattrib.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.tcads.AdsNotificationAttrib;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class AdsNotificationAttribTest extends TestCase {
    static final long INIT_ADS_NOTIFICATION_ATTRIBUTE_LEN = 0L;
    static final int INIT_ADS_NOTIFICATION_ATTRIBUTE_TRANS_MODE = 0;
    static final long INIT_ADS_NOTIFICATION_ATTRIBUTE_DELAY = 0L;
    static final long INIT_ADS_NOTIFICATION_ATTRIBUTE_CYCLE_TIME = 0L;
    static final long INIT_ADS_NOTIFICATION_ATTRIBUTE_CHANGE_FILTER = 0L;

    static final long NEW_ADS_NOTIFICATION_ATTRIBUTE_LEN = 3L;
    static final int NEW_ADS_NOTIFICATION_ATTRIBUTE_TRANS_MODE = 5;
    static final long NEW_ADS_NOTIFICATION_ATTRIBUTE_DELAY = 7L;
    static final long NEW_ADS_NOTIFICATION_ATTRIBUTE_CYCLE_TIME = 9L;
    static final long NEW_ADS_NOTIFICATION_ATTRIBUTE_CHANGE_FILTER = 11L;

    private AdsNotificationAttrib notificationAttrib;

    public AdsNotificationAttribTest(String name) { super(name); }

    @Override
    protected void setUp() {
        notificationAttrib = new AdsNotificationAttrib();
    }

    @Override
    protected void tearDown() {}

    public void testInitialization() {
        assertEquals("Test initially carried amount of bytes",
                     INIT_ADS_NOTIFICATION_ATTRIBUTE_LEN,
                     notificationAttrib.getCbLength());

        assertEquals("Test initial transfer mode",
                     INIT_ADS_NOTIFICATION_ATTRIBUTE_TRANS_MODE,
                     notificationAttrib.getNTransMode());

        assertEquals("Test initial maximal delay",
                     INIT_ADS_NOTIFICATION_ATTRIBUTE_DELAY,
                     notificationAttrib.getNMaxDelay());

        assertEquals("Test initial cycle time",
                     INIT_ADS_NOTIFICATION_ATTRIBUTE_CYCLE_TIME,
                     notificationAttrib.getNCycleTime());

        assertEquals("Test initial change filter",
                     INIT_ADS_NOTIFICATION_ATTRIBUTE_CHANGE_FILTER,
                     notificationAttrib.getDwChangeFilter());
    }

    public void testSetCBLength() {
        notificationAttrib.setCbLength(NEW_ADS_NOTIFICATION_ATTRIBUTE_LEN);
        assertEquals("Test newly carried amount of bytes",
                     NEW_ADS_NOTIFICATION_ATTRIBUTE_LEN,
                     notificationAttrib.getCbLength());
    }

    public void testSetCycleTime() {
        notificationAttrib.setNCycleTime(
            NEW_ADS_NOTIFICATION_ATTRIBUTE_CYCLE_TIME);
        assertEquals("Test new cycle time",
                     NEW_ADS_NOTIFICATION_ATTRIBUTE_CYCLE_TIME,
                     notificationAttrib.getNCycleTime());
    }

    public void testSetMaxDelay() {
        notificationAttrib.setNMaxDelay(NEW_ADS_NOTIFICATION_ATTRIBUTE_DELAY);
        assertEquals("Test new delay", NEW_ADS_NOTIFICATION_ATTRIBUTE_DELAY,
                     notificationAttrib.getNMaxDelay());
    }

    public void testSetTransMode() {
        notificationAttrib.setNTransMode(
            NEW_ADS_NOTIFICATION_ATTRIBUTE_TRANS_MODE);
        assertEquals("Test new transfer mode",
                     NEW_ADS_NOTIFICATION_ATTRIBUTE_TRANS_MODE,
                     notificationAttrib.getNTransMode());
    }

    public void testSetChangeFilter() {
        notificationAttrib.setDwChangeFilter(
            NEW_ADS_NOTIFICATION_ATTRIBUTE_CHANGE_FILTER);
        assertEquals("Test new change filter",
                     NEW_ADS_NOTIFICATION_ATTRIBUTE_CHANGE_FILTER,
                     notificationAttrib.getDwChangeFilter());
    }
}