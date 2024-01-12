import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;

public class Main {
    public static void main(String[] args) {
        long err;
        AmsAddr addr = new AmsAddr();
        JNIByteBuffer symBuff;
        JNIByteBuffer handleBuff = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);

        try {
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

            // Convert Symbol name to byte buffer
            symBuff =
                new JNIByteBuffer(Convert.StringToByteArr("MAIN.PLCVar", true));

            // Get handle via symbol name
            err = AdsCallDllFunction.adsSyncReadWriteReq(
                addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, 0,
                handleBuff.getUsedBytesCount(),
                handleBuff, // buffer for getting handle
                symBuff.getUsedBytesCount(),
                symBuff); // buffer containing symbol name

            if (err != 0) {
                System.out.println("Error: Get Handle: 0x" +
                                   Long.toHexString(err));
            } else {
                System.out.println("Success: Get Handle!");
            }

            // Release handle
            err = AdsCallDllFunction.adsSyncWriteReq(
                addr, AdsCallDllFunction.ADSIGRP_SYM_RELEASEHND, 0,
                handleBuff.getUsedBytesCount(), handleBuff);

            if (err != 0) {
                System.out.println("Error: Release Handle: 0x" +
                                   Long.toHexString(err));
            } else {
                System.out.println("Success: Handle removed!");
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