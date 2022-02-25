package de.beckhoff.jni.tcads;

/**
 * This object contains information about the event.
 *
 * @author Beckhoff Automation
 */
public class AdsNotificationHeader {
    /**
     * Long field containing the handle for the notification. Is specified when
     * the notification is defined.
     */
    private long mHNotification;
    /**
     * Long field containing the time stamp in Windows FILETIME format.
     */
    private long mNTimeStamp;
    /**
     * Byte[] field containing the transferred data.
     */
    private byte[] data;

    /**
     * Class constructor specifying the initial byte[] length
     * @param length
     *      the int value to be set.
     */
    public AdsNotificationHeader(int length) {
        this.mHNotification = 0;
        this.mNTimeStamp = 0;
        data = new byte[length];
    }

    /**
     * Getter for field handle (long containing the handle for the
     * notification.)
     * @return
     *      the current long value of the field handle.
     */
    public long getHNotification() { return mHNotification; }

    /**
     * Getter for field time stamp (long containing the time stamp in Windows
     * FILETIME format).
     * @return
     *      the current long value of the field time stamp.
     */
    public long getNTimeStamp() { return mNTimeStamp; }

    /**
     * Getter for field data (byte[] field containing the transferred data).
     * @return
     *      the current byte[] of the field data.
     */
    public byte[] getData() { return this.data.clone(); }
}