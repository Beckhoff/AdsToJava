import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Main {
    public static void main(String[] args) {
        long err;
        AmsAddr addr = new AmsAddr();

        TransferObject transfer = new TransferObject(); // See additional class
        JNIByteBuffer dataBuff = new JNIByteBuffer(19);

        // Open communication
        AdsCallDllFunction.adsPortOpen();
        err = AdsCallDllFunction.getLocalAddress(addr);
        addr.setPort(851);

        if (err != 0) {
            System.out.println("Error: Open communication: 0x" +
                               Long.toHexString(err));
        } else {
            System.out.println("Success: Open communication!");
        }

        // Use JNIByteBuffer as a backing array for ByteBuffer
        ByteBuffer bb = ByteBuffer.wrap(dataBuff.getByteArray());

        // Write elements to buffer. Little Endian!
        bb.order(ByteOrder.LITTLE_ENDIAN);

        bb.putShort(transfer.getShortVal());
        bb.putInt(transfer.getIntVal());
        bb.put(transfer.getByteVal());
        bb.putDouble(transfer.getDoubleVal());
        bb.putFloat(transfer.getFloatVal());

        // Write struct to PLC
        err = AdsCallDllFunction.adsSyncWriteReq(addr,
                                                 0x4020, // Index Group
                                                 0x0,    // Index Offset
                                                 19, dataBuff);
        if (err != 0) {
            System.out.println("Error: Write request: 0x" +
                               Long.toHexString(err));
        } else {
            System.out.println("Success: Write struct!");
        }

        // Close communication
        err = AdsCallDllFunction.adsPortClose();
        if (err != 0) {
            System.out.println("Error: Close Communication: 0x" +
                               Long.toHexString(err));
        }
    }
}