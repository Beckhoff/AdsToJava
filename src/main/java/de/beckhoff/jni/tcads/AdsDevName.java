/*
 * AdsDevName.java
 */

package de.beckhoff.jni.tcads;

/**
 * This object contain information about the ADS Device Name.
 *
 * @author Beckhoff Automation
 */
public class AdsDevName {
    /**
     * String field containing the device name.
     */
    private String mDevName;

    /**
     * Class constructor.
     */
    public AdsDevName() { this.mDevName = ""; }

    /**
     * Class constructor specifying the initially contained device name.
     * @param lDevName
     *      the initial device name of type String.
     */
    public AdsDevName(String lDevName) { this.mDevName = lDevName; }

    /**
     * Setter for field device name.
     * @param lDevName
     *      the String value to be set.
     */
    public void setDevName(String lDevName) { this.mDevName = lDevName; }

    /**
     * Getter for field device name.
     * @return
     *      the current String value of field device name.
     */
    public String getDevName() { return this.mDevName; }
}