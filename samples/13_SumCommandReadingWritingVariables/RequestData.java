
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class RequestData {
    /**
     * the classes size in bits.
     */
    public static final int SIZE = Integer.SIZE * 3 / 8;
    private String varName;
    private int indexGroup;
    private int indexOffset;
    private int length;

    public RequestData(String varName, int indexGroup, int indexOffset,
                       int length) {
        this.varName = varName;
        this.indexGroup = indexGroup;
        this.indexOffset = indexOffset;
        this.length = length;
    }

    public String getVarName() { return this.varName; }

    public int getLength() { return this.length; }

    public byte[] toByteArray() {
        ByteBuffer tempBuff = ByteBuffer.allocate(SIZE);
        tempBuff.order(ByteOrder.LITTLE_ENDIAN);

        tempBuff.putInt(this.indexGroup);
        tempBuff.putInt(this.indexOffset);
        tempBuff.putInt(this.length);

        return tempBuff.array();
    }
}