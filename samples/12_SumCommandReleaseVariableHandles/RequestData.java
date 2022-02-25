
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class RequestData {
    /**
     * the classes size in bits.
     */
    public static final int SIZE = Integer.SIZE * 4;
    private int indexGroup;
    private int indexOffset;
    private int readLength;
    private int writeLength;

    public RequestData() {
        this.indexGroup = 0;
        this.indexOffset = 0;
        this.readLength = 0;
        this.writeLength = 0;
    }

    public void setIndexGroup(int indexGroup) { this.indexGroup = indexGroup; }

    public int getIndexGroup() { return indexGroup; }

    public void setIndexOffset(int indexOffset) {
        this.indexOffset = indexOffset;
    }

    public int getIndexOffset() { return indexOffset; }

    public void setReadLength(int readLength) { this.readLength = readLength; }

    public int getReadLength() { return readLength; }

    public void setWriteLength(int writeLength) {
        this.writeLength = writeLength;
    }

    public int getWriteLength() { return writeLength; }

    public byte[] toByteArray() {
        ByteBuffer tempBuff = ByteBuffer.allocate(SIZE / 8);
        tempBuff.order(ByteOrder.LITTLE_ENDIAN);

        tempBuff.putInt(this.indexGroup);
        tempBuff.putInt(this.indexOffset);
        tempBuff.putInt(this.readLength);
        tempBuff.putInt(this.writeLength);

        return tempBuff.array();
    }
}