import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;

public class Main {
    public static void main(String[] args) {
        long err;
        AmsAddr addr = new AmsAddr();
        JNIByteBuffer handleBuff = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
        JNIByteBuffer symbolBuff =
            new JNIByteBuffer(Convert.StringToByteArr("MAIN.PLCVar", true));
        JNIByteBuffer dataBuff = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);

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

        // Get handle by symbol name
        err = AdsCallDllFunction.adsSyncReadWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, 0x0,
            handleBuff.getUsedBytesCount(), handleBuff,
            symbolBuff.getUsedBytesCount(), symbolBuff);
        if (err != 0) {
            System.out.println("Error: Get handle: 0x" + Long.toHexString(err));
        } else {
            System.out.println("Success: Get handle!");
        }

        // Handle: byte[] to int
        int hdlBuffToInt = Convert.ByteArrToInt(handleBuff.getByteArray());

        // Read value by handle
        err = AdsCallDllFunction.adsSyncReadReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_VALBYHND, hdlBuffToInt, 0x4,
            dataBuff);
        if (err != 0) {
            System.out.println("Error: Read by handle: 0x" +
                               Long.toHexString(err));
        } else {
            // Data: byte[] to int
            int intVal = Convert.ByteArrToInt(dataBuff.getByteArray());
            System.out.println("Success: PLCVar value: " + intVal);
        }

        // Release handle
        err = AdsCallDllFunction.adsSyncWriteReq(
            addr, AdsCallDllFunction.ADSIGRP_SYM_RELEASEHND, 0,
            handleBuff.getUsedBytesCount(), handleBuff);

        if (err != 0) {
            System.out.println("Error: Release Handle: 0x" +
                               Long.toHexString(err));
        } else {
            System.out.println("Success: Release Handle!");
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