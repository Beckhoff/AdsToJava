public class TransferObject {
    private short shortVal;
    private int intVal;
    private byte byteVal;
    private double doubleVal;
    private float floatVal;

    public TransferObject() {
        this.shortVal = Short.MAX_VALUE;
        this.intVal = Integer.MIN_VALUE;
        this.byteVal = 3;
        this.doubleVal = 4.1234;
        this.floatVal = 5.4321f;
    }

    public short getShortVal() { return shortVal; }
    public int getIntVal() { return intVal; }
    public byte getByteVal() { return byteVal; }
    public double getDoubleVal() { return doubleVal; }
    public float getFloatVal() { return floatVal; }
}
