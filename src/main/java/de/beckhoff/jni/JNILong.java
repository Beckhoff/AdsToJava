/*
 * JNILong.java
 */
package de.beckhoff.jni;

/**
 * This is a Long related data type which can be used as a buffer for JNI
 * calls.
 *
 * @author Beckhoff Automation
 */
public class JNILong {
    /**
     * Long field containing the long.
     */
    private long mLong;

    /**
     * Class constructor.
     */
    public JNILong() { this.mLong = 0L; }

    /**
     * Class constructor specifying the initially contained long.
     * @param lLong
     *      the initial value of type long.
     */
    public JNILong(long lLong) { this.mLong = lLong; }

    /**
     * Setter for field mLong.
     * @param lLong
     *      the long value to be set.
     */
    public void setLong(long lLong) { this.mLong = lLong; }

    /**
     * Getter for field mLong.
     * @return
     *      the current long value of field mLong.
     */
    public long getLong() { return this.mLong; }
}