
import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;

public class Main {
    public static void main(String[] args) {
        try {
            long err;
            AmsAddr addr = new AmsAddr();

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

            // Specify IndexGroup, IndexOffset and write PLCVar
            err = AdsCallDllFunction.adsSyncWriteReq(
                addr,
                0x4020, // Index Group
                0x0,    // Index Offset
                Integer.SIZE / Byte.SIZE,
                new JNIByteBuffer(Convert.IntToByteArr(1024)));
            if (err != 0) {
                System.out.println("Error: Write by address: 0x" +
                                   Long.toHexString(err));
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