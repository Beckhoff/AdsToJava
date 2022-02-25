package de.beckhoff.jni.tcads.adsnotificationheader.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.tcads.AdsNotificationHeader;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class AdsNotificationHeaderTest extends TestCase {
    static final int INIT_ADS_NOTIFICATION_HEADER_DATA_LEN = 10;
    static final long INIT_ADS_NOTIFICATION_HEADER_HANDLE = 0L;
    static final long INIT_ADS_NOTIFICATION_HEADER_TIME_STAMP = 0L;
    static final byte[] INIT_ADS_NOTIFICATION_HEADER_DATA =
        new byte[INIT_ADS_NOTIFICATION_HEADER_DATA_LEN];

    private AdsNotificationHeader notificationHeader;

    public AdsNotificationHeaderTest(String name) { super(name); }

    @Override
    protected void setUp() {
        notificationHeader =
            new AdsNotificationHeader(INIT_ADS_NOTIFICATION_HEADER_DATA_LEN);
    }

    @Override
    protected void tearDown() {}

    public void testInitialization() {
        assertEquals("Test initial notification handle",
                     INIT_ADS_NOTIFICATION_HEADER_HANDLE,
                     notificationHeader.getHNotification());

        assertEquals("Test initial time stamp",
                     INIT_ADS_NOTIFICATION_HEADER_TIME_STAMP,
                     notificationHeader.getNTimeStamp());

        assertArrayEquals("Test initial data",
                          INIT_ADS_NOTIFICATION_HEADER_DATA,
                          notificationHeader.getData());
    }
}