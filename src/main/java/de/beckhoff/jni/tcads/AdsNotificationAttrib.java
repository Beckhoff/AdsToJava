/*
 * AdsNotificationAttrib.java
 */

package de.beckhoff.jni.tcads;

/**
 * This object contains all the attributes for the definition of a notification.
 *
 * @author Beckhoff Automation
 */
public class AdsNotificationAttrib {
    /**
     * long field containing the amount of bytes of the PLC variable.
     */
    private long mCbLength;
    /**
     * int field containing the transfer mode of events from the ADS server.
     */
    private int mNTransMode;
    /**
     * long field containing the latest time (in 100 nano seconds) this
     * notifications callback function is invoked.
     */
    private long mNMaxDelay;
    /**
     * long field containing the span between two automatic ADS server variable
     * lookups (in 100 nano seconds).
     */
    private long mNCycleTime;
    /**
     * long field containing the span between two automatic ADS server variable
     * lookups (in 100 nano seconds).
     */
    private long mDwChangeFilter;

    /**
     * Class constructor.
     */
    public AdsNotificationAttrib() {
        this.mCbLength = 0;
        this.mNTransMode = 0;
        this.mNMaxDelay = 0;
        this.mNCycleTime = 0;
        this.mDwChangeFilter = 0;
    }

    /**
     * Setter for field byte length (long containing the amount of  bytes of the
     * PLC variable).
     * @param lCbLength
     *      the long value to be set.
     */
    public void setCbLength(long lCbLength) { this.mCbLength = lCbLength; }

    /**
     * Getter for field byte length (long containing the amount of  bytes of the
     * PLC variable).
     * @return
     *      the current long value of field byte length.
     */
    public long getCbLength() { return this.mCbLength; }

    /**
     * Setter for field transfer mode (int containing the transfer mode of
     * events from the ADS server).
     * @param lNTransMode
     *      the int value to be set.
     */
    public void setNTransMode(int lNTransMode) { mNTransMode = lNTransMode; }

    /**
     * Getter for field transfer mode (int containing the transfer mode of
     * events from the ADS server).
     * @return
     *      the current int value of field transfer mode.
     */
    public int getNTransMode() { return mNTransMode; }

    /**
     * Setter for field delay (long containing the latest time [in 100 nano]
     * seconds) the notifications callback function is invoked).
     * @param lNMaxDelay
     *      the long value to be set.
     */
    public void setNMaxDelay(long lNMaxDelay) { mNMaxDelay = lNMaxDelay; }

    /**
     * Getter for field delay (long containing the latest time [in 100 nano]
     * seconds) the notifications callback function is invoked).
     * @return
     *      the current long value of field delay.
     */
    public long getNMaxDelay() { return mNMaxDelay; }

    /**
     * Setter for field cycle time (long containing the span between two
     * automatic ADS server variable lookups [in 100 nano seconds]).
     *
     * When NCycleTime will be set to 0 it will be set to the value of
     * DwChangeFilter instead.
     *
     * @param lNCycleTime
     *      the long value to be set.
     */
    public void setNCycleTime(long lNCycleTime) {
        if (lNCycleTime != 0)
            mNCycleTime = lNCycleTime;
        else
            mNCycleTime = mDwChangeFilter;
    }

    /**
     * Getter for field cycle time (long containing the span between two
     * automatic ADS server variable lookups [in 100 nano seconds]).
     * @return
     *      the current long value of field cycle time.
     */
    public long getNCycleTime() { return mNCycleTime; }

    /**
     * Setter for field change filter (long containing the span between two
     * automatic ADS server variable lookups [in 100 nano seconds]).
     *
     * Setting DwChangeFilter also affects NCycleTime. NCycleTime will be set to
     * the same value.
     *
     * @param lDwChangeFilter
     *      the long value to be set.
     */
    public void setDwChangeFilter(long lDwChangeFilter) {
        mDwChangeFilter = lDwChangeFilter;
        mNCycleTime = mDwChangeFilter;
    }

    /**
     * Getter for field change filter (long containing the span between two
     * automatic ADS server variable lookups [in 100 nano seconds]).
     * @return
     *      the current long value of field change filter.
     */
    public long getDwChangeFilter() { return mDwChangeFilter; }
}