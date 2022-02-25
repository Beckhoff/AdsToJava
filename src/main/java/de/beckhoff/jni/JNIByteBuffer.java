/*
 * JNIOutObjectByteArray.java
 */
package de.beckhoff.jni;

/**
 * This is a ByteBuffer related data type which can be used as a buffer for JNI
 * calls.
 *
 * @author Beckhoff Automation
 */
public class JNIByteBuffer {
    /**
     * Byte[] field containing the buffer.
     */
    private byte[] mByteArray;
    /**
     * Int field containing the number of bytes mByteArray consists of.
     */
    private int mUsedBytesCount;

    /**
     * Class constructor
     */
    public JNIByteBuffer() {
        this.mUsedBytesCount = 0;
        this.mByteArray = new byte[0];
    }

    /**
     * Class constructor specifying the initially contained byte[]. Note that
     * by calling this constructor the field UsedBytesCount will be initialized
     * with the length of the given array. If lByteArray is null this classes
     * byte[] will be initialized with a new array of the length 0.
     * UsedBytesCount will be set to 0 as well.
     * @param lByteArray
     *      the initial byteArray of type byte[].
     */
    public JNIByteBuffer(byte[] lByteArray) {
        if (lByteArray != null) {
            this.mByteArray = lByteArray.clone();
            this.mUsedBytesCount = this.mByteArray.length;
        } else {
            this.mByteArray = new byte[0];
            this.mUsedBytesCount = 0;
        }
    }

    /**
     * Class constructor specifying the initially contained UsedBytesCount. Note
     * that by calling this constructor the field ByteArray will be initialized
     * with an new byte[] of the given length.
     * @param lUsedBytesCount
     *      the initial UsedBytesCount of type int.
     */
    public JNIByteBuffer(int lUsedBytesCount) {
        this.mUsedBytesCount = lUsedBytesCount;
        this.mByteArray = new byte[lUsedBytesCount];
    }

    /**
     * Class constructor specifying the initially contained byte[] and
     * UsedBytesCount. When calling this constructor supplying a length value
     * lUsedBytesCount different from the actual length of the byte array
     * lByteArray there is no further effect on the array.
     *
     * Since the caller cannot choose to alter the behavior described above
     * this constructor has been marked deprecated. For full control use
     * JNIByteBuffer(byte[], int, boolean) instead.
     * @param lByteArray
     *      the initial ByteArray of type byte[].
     * @param lUsedBytesCount
     *      the initial UsedBytesCount of type int.
     */
    @Deprecated
    public JNIByteBuffer(byte[] lByteArray, int lUsedBytesCount) {
        this(lByteArray, lUsedBytesCount, false);
    }

    /**
     * Class constructor specifying the initially contained byte[] and
     * UsedBytesCount. Setting the usedBytesCount affects lByteArray if
     * affectByteCount equals true.
     *
     * In case that byte[].length &lt; lUsedBytesCount null bytes are added to
     * the end of the array.
     * In case that byteArray.length &gt; lUsedBytesCount byteArray does contain
     * the first lUsedBytesCount bytes. Further bytes are dropped.
     *
     * @param lByteArray
     *      the initial ByteArray of type byte[].
     * @param lUsedBytesCount
     *      the initial UsedBytesCount of type int.
     * @param affectByteCount
     *      boolean which controls the way the provided lUsedBytesCount
     *      parameter will be treated
     * @throws IllegalArgumentException
     *      is thrown when either lByteArray is null or lUsedBytesCount
     *      is negative
     */
    public JNIByteBuffer(byte[] lByteArray, int lUsedBytesCount,
                         boolean affectByteCount)
        throws IllegalArgumentException {
        if (affectByteCount) {
            // Check values
            if (lByteArray == null) {
                throw new IllegalArgumentException(
                    "This constructor does not allow for lByteArray to be null");
            }

            if (lUsedBytesCount < 0) {
                throw new IllegalArgumentException(
                    "This constructor does not allow for lUsedBytesCount to be negative.");
            }

            // Init member-variables
            this.mByteArray = new byte[lUsedBytesCount];
            this.mUsedBytesCount = lUsedBytesCount;

            // Assign values
            for (int i = 0; i < lUsedBytesCount; i++) {
                if (i >= lByteArray.length)
                    break;

                this.mByteArray[i] = lByteArray[i];
            }
        } else {
            this.mByteArray = (lByteArray != null) ? lByteArray.clone() : null;
            this.mUsedBytesCount = lUsedBytesCount;
        }
    }

    /**
     * Setter for field ByteArray. Setting the byteArray does not affect
     * usedBytesCount.
     *
     * Since the caller cannot choose to alter the behavior described above
     * this method has been marked deprecated. For full control use
     * setByteArray(byte[], boolean) instead.
     * @param lByteArray
     *      the byte[] value to be set.
     */
    @Deprecated
    public void setByteArray(byte[] lByteArray) {
        this.setByteArray(lByteArray, false);
    }

    /**
     * Setter for field ByteArray. Setting the byteArray also affects
     * usedBytesCount.
     * @param lByteArray
     *      the byte[] value to be set.
     * @param affectByteCount
     *      specifies whether the usedBytesCount should be adjusted after
     * setting the byteArray.
     */
    public void setByteArray(byte[] lByteArray, boolean affectByteCount) {
        this.mByteArray = (lByteArray != null) ? lByteArray.clone() : null;

        if (affectByteCount) {
            if (this.mByteArray != null)
                this.mUsedBytesCount = this.mByteArray.length;
            else
                this.mUsedBytesCount = 0;
        }
    }

    /**
     * Getter for field ByteArray.
     * @return
     *      the current byte[] value of field ByteArray.
     */
    public byte[] getByteArray() {
        return (mByteArray != null) ? mByteArray.clone() : null;
    }

    /**
     * Setter for field UsedBytesCount. Setting the amount of bytes used does
     * not affect the byte[].
     *
     * Since the caller cannot choose to alter the behavior described above
     * this method has been marked deprecated. For full control use
     * setUsedBytesCount(int, boolean) instead.
     * @param lUsedBytesCount
     *      the byte[] length to be set.
     */
    @Deprecated
    public void setUsedBytesCount(int lUsedBytesCount) {
        setUsedBytesCount(lUsedBytesCount, false);
    }

    /**
     * Setter for field UsedBytesCount. Setting the usedBytesCount also affects
     * byteArray.
     *
     * In case that byte[].length &lt; lUsedBytesCount null bytes are added to
     * the end of the array.
     * In case that byteArray.length &gt; lUsedBytesCount byteArray does contain
     * the first lUsedBytesCount bytes. Further bytes are dropped.
     *
     * @param lUsedBytesCount
     *      the int value to be set.
     * @param affectByteArray
     *      specifies whether the byte array should be adjusted after setting
     *      the usedBytesCount.
     */
    public void setUsedBytesCount(int lUsedBytesCount,
                                  boolean affectByteArray) {
        this.mUsedBytesCount = lUsedBytesCount;

        if (affectByteArray) {
            byte[] newByte = new byte[lUsedBytesCount];

            for (int i = 0; i < lUsedBytesCount; i++) {
                if (i >= this.mByteArray.length) {
                    break;
                }

                newByte[i] = this.mByteArray[i];
            }

            this.mByteArray = newByte;
        }
    }

    /**
     * Getter for field UsedBytesCount.
     * @return
     *      the current byte[] value of field UsedBytesCount.
     */
    public int getUsedBytesCount() { return this.mUsedBytesCount; }
}