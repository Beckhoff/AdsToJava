/*
 * AdsVersion.java
 */
package de.beckhoff.jni.tcads;

/**
 * This object contains information about the ADS Version.
 *
 * @author Beckhoff Automation
 */
public class AdsVersion {
    /**
     * char field containing the current version number.
     */
    private char mVersion;
    /**
     * char field containing the current revision.
     */
    private char mRevision;
    /**
     * short field containing the current build number.
     */
    private short mBuild;

    /**
     * Class constructor
     */
    public AdsVersion() {
        this.mVersion = 0;
        this.mRevision = 0;
        this.mBuild = 0;
    }

    /**
     * Getter for field version number (char containing the current version
     * number).
     * @return
     *      the current char value of field version number.
     */
    public char getVersion() { return mVersion; }

    /**
     * Getter for field revision (char containing the current revision).
     * @return
     *      the current char value of field revision.
     */
    public char getRevision() { return mRevision; }

    /**
     * Getter for field build number (short containing the current build
     * number).
     * @return
     *      the current short value of field build number.
     */
    public short getBuild() { return mBuild; }
}