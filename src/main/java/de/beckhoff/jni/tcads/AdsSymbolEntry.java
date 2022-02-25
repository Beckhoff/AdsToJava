package de.beckhoff.jni.tcads;

import de.beckhoff.jni.Convert;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * This object is capable of storing the result of an AdsSyncReadWriteReq()
 * where variable information is retrieved. By handing the byte[] into this
 * classes constructor the contained information is read and retrievable
 * through the according getters.
 *
 * @author Beckhoff Automation
 */
public class AdsSymbolEntry {
    private final static char MIN_BUFF_LEN = 30;

    /**
     * int field containing the symbols length.
     */
    private int entryLength;
    /**
     * int field containing the symbols index group.
     */
    private int iGroup;
    /**
     * int field containing the symbols index offset.
     */
    private int iOffs;
    /**
     * int field containing the symbols size (in bytes, 0 = length in bit).
     */
    private int size;
    /**
     * int field containing the symbols data type (lookup the "Reading the PLC
     * variable declaration of an individual variable" InfoSys article
     * for further information).
     */
    private int dataType;
    /**
     * int field containing further information (1 = ADSSYMBOLFLAG_PERSISTENT,
     * 2 = ADSSYMBOLFLAG_BITVALUE).
     */
    private int flags;
    /**
     * short field containing the symbols length of name (excl. \0).
     */
    private short nameLength;
    /**
     * short field containing the symbols length of type (excl. \0).
     */
    private short typeLength;
    /**
     * short field containing the symbols length of comment (excl. \0).
     */
    private short commentLength;
    /**
     * String field containing the symbols name (with terminating \0).
     */
    private String name;
    /**
     * String field containing the symbols type name (with terminating \0).
     */
    private String type;
    /**
     * String field containing the symbols comment (with terminating \0).
     */
    private String comment;

    /**
     * Class constructor. Tries to read from a given buffer with minimum length
     * of MIN_BUFF_LEN. It expects the buffer to be of a size equal to
     * MIN_BUFF_LEN + nameLength + typeLength + commentLength.
     *
     * Each member of this class wil be set while parsing through the given
     * buffer.
     *
     * @param buff
     *      the byte[] to be parsed.
     * @exception IllegalArgumentException
     *      is thrown when the parsing fails.
     */
    public AdsSymbolEntry(byte[] buff) throws IllegalArgumentException {
        try {
            retrieveInformationItems(buff);
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    /**
     * Overwrites the current byte[] and therefore each according field with the
     * contained information.
     *
     * @param buff
     *      the byte[] to be set.
     */
    public void setSymbolEntryBuff(byte[] buff) {
        retrieveInformationItems(buff);
    }

    /**
     * Getter for field entryLength.
     * @return
     *      the current int value of the field entryLength.
     */
    public int getEntryLen() { return this.entryLength; }

    /**
     * Getter for field indexGroup.
     * @return
     *      the current int value of the field indexGroup.
     */
    public int getiGroup() { return this.iGroup; }

    /**
     * Getter for field indexOffset.
     * @return
     *      the current int value of the field indexOffset.
     */
    public int getiOffs() { return this.iOffs; }

    /**
     * Getter for field size.
     * @return
     *      the current int value of the field size.
     */
    public int getSize() { return this.size; }

    /**
     * Getter for field dataType.
     * @return
     *      the current int value of the field dataType.
     */
    public int getDataType() { return this.dataType; }

    /**
     * Getter for field flags.
     * @return
     *      the current int value of the field flags.
     */
    public int getFlags() { return this.flags; }

    /**
     * Getter for fields nameLength.
     * @return
     *      the current in value of the field nameLength.
     */
    public int getNameLength() { return this.nameLength; }

    /**
     * Getter for fields typeLength.
     * @return
     *      the current in value of the field typeLength.
     */
    public int getTypeLength() { return this.typeLength; }

    /**
     * Getter for fields commentLength.
     * @return
     *      the current in value of the field commentLength.
     */
    public int getCommentLength() { return this.commentLength; }

    /**
     * Getter for field name.
     * @return
     *      the current int value of the field name.
     */
    public String getName() { return this.name; }

    /**
     * Getter for field type.
     * @return
     *      the current int value of the field type.
     */
    public String getType() { return this.type; }

    /**
     * Getter for field comment.
     * @return
     *      the current int value of the field comment.
     */
    public String getComment() { return this.comment; }

    /**
     * This method tries to set each member of this class while parsing through
     * the given buffer.
     *
     * @param buff
     *      the byte[] to be parsed.
     * @throws IllegalArgumentException
     *      is thrown when the parsing fails.
     */
    private void retrieveInformationItems(byte[] buff)
        throws IllegalArgumentException {
        // Tracks whether or not the buffer occurred to be invalid
        boolean isBufferValid = true;

        ByteBuffer bb = ByteBuffer.wrap(buff);
        bb.order(ByteOrder.LITTLE_ENDIAN);

        if (buff.length >= MIN_BUFF_LEN) {
            this.entryLength = bb.getInt();
            this.iGroup = bb.getInt();
            this.iOffs = bb.getInt();
            this.size = bb.getInt();
            this.dataType = bb.getInt();
            this.flags = bb.getInt();
            this.nameLength = bb.getShort();
            this.typeLength = bb.getShort();
            this.commentLength = bb.getShort();
        } else {
            isBufferValid = false;
        }

        // Take the expected length of each of the appended Strings and sum up
        // their values.
        short additionalLength =
            (short)(nameLength + 1 + typeLength + 1 + commentLength + 1);

        // Check whether the expected size matches with the actual size.
        if (isBufferValid &
            ((buff.length - MIN_BUFF_LEN) >= additionalLength)) {
            byte[] bt = new byte[nameLength + 1];
            bb.get(bt, 0, nameLength + 1);
            this.name = Convert.ByteArrToString(bt);

            bt = new byte[typeLength + 1];
            bb.get(bt, 0, typeLength + 1);
            this.type = Convert.ByteArrToString(bt);

            bt = new byte[commentLength + 1];
            bb.get(bt, 0, commentLength + 1);
            this.comment = Convert.ByteArrToString(bt);
        } else {
            isBufferValid = false;
        }

        if (!isBufferValid) {
            throw new IllegalArgumentException(
                "The buffer did not have the correct format.");
        }
    }
}