/*
 * AdsState.java
 */
package de.beckhoff.jni.tcads;

/**
 * This object contains information about the ADS State.
 *
 * @author Beckhoff Automation
 */
public class AdsState {
    /**
     * ADS state: Invalidated state.
     */
    public static final short ADSSTATE_INVALID = 0;
    /**
     * ADS state: Idle state.
     */
    public static final short ADSSTATE_IDLE = 1;
    /**
     * ADS state: Reset state.
     */
    public static final short ADSSTATE_RESET = 2;
    /**
     * ADS state: Initialize state.
     */
    public static final short ADSSTATE_INIT = 3;
    /**
     * ADS state: Start state.
     */
    public static final short ADSSTATE_START = 4;
    /**
     * ADS state: Run state.
     */
    public static final short ADSSTATE_RUN = 5;
    /**
     * ADS state: Stop state.
     */
    public static final short ADSSTATE_STOP = 6;
    /**
     * ADS state: Save Configuration state.
     */
    public static final short ADSSTATE_SAVECFG = 7;
    /**
     * ADS state: Load Configuration state.
     */
    public static final short ADSSTATE_LOADCFG = 8;
    /**
     * ADS state: Power failure state.
     */
    public static final short ADSSTATE_POWERFAILURE = 9;
    /**
     * ADS state: Power good state.
     */
    public static final short ADSSTATE_POWERGOOD = 10;
    /**
     * ADS state: Error state state.
     */
    public static final short ADSSTATE_ERROR = 11;
    /**
     * ADS state: Shutting down state.
     */
    public static final short ADSSTATE_SHUTDOWN = 12;
    /**
     * ADS state: Suspend state.
     */
    public static final short ADSSTATE_SUSPEND = 13;
    /**
     * ADS state: Resume state.
     */
    public static final short ADSSTATE_RESUME = 14;

    /**
     * Short field containing the current ADS state.
     */
    private short mState;

    /**
     * Class constructor.
     */
    public AdsState() { this.mState = 0; }

    /**
     * Setter for field state.
     * @param lState
     *      the short value to be set.
     */
    public void setState(short lState) { mState = lState; }

    /**
     * Getter for field state.
     * @return
     *      the current short value of field state.
     */
    public short getState() { return mState; }
}