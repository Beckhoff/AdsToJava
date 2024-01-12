import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Main {
    public static void main(String[] args) {
        try {
            long err;
            AmsAddr addr = new AmsAddr();
            JNIByteBuffer buff = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
            ByteBuffer bb = ByteBuffer.allocate(0);

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

            // Specify IndexGroup, IndexOffset and read PLCVar
            err = AdsCallDllFunction.adsSyncReadReq(addr,
                                                    0x4020, // Index Group
                                                    0x0,    // Index Offset
                                                    buff.getUsedBytesCount(),
                                                    buff);
            if (err != 0) {
                System.out.println("Error: Read by address: 0x" +
                                   Long.toHexString(err));
            } else {
                bb = ByteBuffer.wrap(buff.getByteArray());
                bb.order(ByteOrder.LITTLE_ENDIAN);

                System.out.println("" + bb.getInt());
            }

            // Close communication
            err = AdsCallDllFunction.adsPortClose();
            if (err != 0) {
                System.out.println("Error: Close Communication: 0x" +
                                   Long.toHexString(err));
            }

            System.out.println("Press enter to continue..");
            System.in.read();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}