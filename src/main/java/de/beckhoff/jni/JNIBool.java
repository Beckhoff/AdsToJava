/*
 * JNIBool.java
 */
package de.beckhoff.jni;

/**
 * This is a Boolean related data type which can be used as a buffer for JNI
 * calls.
 *
 * @author Beckhoff Automation
 */
public class JNIBool {
    /**
     * field containing the long.
     */
    private boolean mBool;

    /**
     * Class constructor.
     */
    public JNIBool() { this.mBool = false; }

    /**
     * Class constructor specifying the initially contained boolean.
     * @param lbool
     *      the initial value of type boolean.
     */
    public JNIBool(boolean lbool) { this.mBool = lbool; }

    /**
     * Setter for field mBool.
     * @param lbool
     *      the boolean value to be set.
     */
    public void setBool(boolean lbool) { this.mBool = lbool; }

    /**
     * Getter for field mLong.
     * @return
     *      the current boolean value of field mBool.
     */
    public boolean getBool() { return this.mBool; }
}