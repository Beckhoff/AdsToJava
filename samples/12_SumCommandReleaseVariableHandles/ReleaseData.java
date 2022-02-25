
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ReleaseData {
    /**
     * the classes size in bits.
     */
    public static final int SIZE = Integer.SIZE * 3;
    private int indexGroup;
    private int indexOffset;
    private int length;

    public ReleaseData() {
        this.indexGroup = 0;
        this.indexOffset = 0;
        this.length = 0;
    }

    public void setIndexGroup(int indexGroup) { this.indexGroup = indexGroup; }

    public int getIndexGroup() { return indexGroup; }

    public void setIndexOffset(int indexOffset) {
        this.indexOffset = indexOffset;
    }

    public int getIndexOffset() { return indexOffset; }

    public void setLength(int length) { this.length = length; }

    public int getLength() { return length; }

    public byte[] toByteArray() {
        ByteBuffer tempBuff = ByteBuffer.allocate(SIZE / 8);
        tempBuff.order(ByteOrder.LITTLE_ENDIAN);

        tempBuff.putInt(this.indexGroup);
        tempBuff.putInt(this.indexOffset);
        tempBuff.putInt(this.length);

        return tempBuff.array();
    }
}