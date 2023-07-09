package de.beckhoff.jni;

/**
 * This class contains constants determining at what time the
 * AdsSyncNotification-Events are fired.
 *
 * @author Beckhoff Automation
 */
public class AdsConstants {
    /**
     * The AdsSyncNotification-Event is not fired.
     */
    public final static int ADSTRANS_NOTRANS = 0;
    public final static int ADSTRANS_CLIENTCYCLE = 1;
    public final static int ADSTRANS_CLIENT1REQ = 2;
    public final static int ADSTRANS_CLIENTONCHA =
        2; // the alternative name for ADSTRANS_CLIENT1REQ
    /**
     * The AdsSyncNotification-Event is fired cyclically.
     */
    public final static int ADSTRANS_SERVERCYCLE = 3;
    /**
     * The AdsSyncNotification-Event is fired when the data changes.
     */
    public final static int ADSTRANS_SERVERONCHA = 4;

    /**
     * Private constructor to suppress instantiation.
     */
    private AdsConstants() {}
}