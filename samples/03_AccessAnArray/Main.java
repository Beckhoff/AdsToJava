import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;

public class Main {
    public static void main(String[] args) {
        long err;
        AmsAddr addr = new AmsAddr();
        JNIByteBuffer dataBuff = new JNIByteBuffer(200);

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

        // Read value by IndexGroup and IndexOffset
        err = AdsCallDllFunction.adsSyncReadReq(addr,
                                                0x4020, // Index Group
                                                0x0,    // Index Offset
                                                200, dataBuff);
        if (err != 0) {
            System.out.println("Error: Read by handle: 0x" +
                               Long.toHexString(err));
        } else {
            for (int i = 0; i < dataBuff.getUsedBytesCount(); i = i + 2) {
                // PLC datatype int consists of two bytes. Get them.
                byte lowByte = dataBuff.getByteArray()[i];
                byte highByte = dataBuff.getByteArray()[i + 1];
                // Create new byte[]. Little endian!
                byte[] valBytes = {lowByte, highByte};
                // Integer value: byte[] to int
                int valInt = Convert.ByteArrToShort(valBytes);
                System.out.println("Value of PLCVar[" + i / 2 + "]: " + valInt);
            }
        }

        // Close communication
        err = AdsCallDllFunction.adsPortClose();
        if (err != 0) {
            System.out.println("Error: Close Communication: 0x" +
                               Long.toHexString(err));
        }

        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("Error: Close program");
        }
    }
}