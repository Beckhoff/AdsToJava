package de.beckhoff.jni.tcads;

import java.util.Arrays;

/**
 * This object contains an AMS Net ID.
 *
 * @author Beckhoff Automation
 */
public class AmsNetId {
    /**
     * char[] field containing the AmsNetID in char representation.
     */
    private char[] mB;

    /**
     * Class constructor.
     */
    public AmsNetId() { this.mB = new char[6]; }

    /**
     * Class copy constructor.
     * @param id
     *      another AmsNetId object.
     */
    public AmsNetId(AmsNetId id) { this.mB = id.mB.clone(); }

    /**
     * Class constructor specifying the initially contained byte[]. Note the
     * length of the byte[] has exactly to be 6.
     * @param bytes
     *      the initial byte[].
     * @exception IllegalArgumentException
     *      this is thrown when bytes.length != 6
     */
    public AmsNetId(char[] bytes) { setCharArr(bytes); }

    /**
     * Getter for field amsNetID (char[] containing the AmsNetID in char
     * representation).
     * @return
     *      the current byte[] of field amsNetID.
     */
    public char[] getCharArr() { return this.mB.clone(); }

    /**
     * Setter for field amsNetID (char[] containing the AmsNetID in char
     * representation). Note the  length of the byte[] has exactly to be 6.
     * @param bytes
     *      the byte[] to be set.
     * @exception IllegalArgumentException
     *      this is thrown when bytes.length != 6
     */
    public void setCharArr(char[] bytes) {
        if (bytes.length == 6)
            this.mB = bytes.clone();
        else
            throw new IllegalArgumentException("Setting the AmsNetId failed.");
    }

    /**
     * Setter for individual array elements of the field amsNetID (char[]
     * containing the AmsNetID in char representation).
     * @param b
     *      the byte to be set.
     * @param index
     *      the index of the byte to be set.
     * @exception IllegalArgumentException
     *      this is thrown when the index is not valid
     */
    public void setChar(char b, int index) {
        if (index >= 0 && index < 6)
            this.mB[index] = b;
        else
            throw new IllegalArgumentException(
                "Setting part of the AmsNetId failed.");
    }

    @Override
    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AmsNetId)) {
            return false;
        }
        return Arrays.equals(this.mB, ((AmsNetId)o).mB);
    }

    @Override
    public final int hashCode() {
        return Arrays.hashCode(this.mB);
    }
}