import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.JNILong;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;

public class Main {
    public static void main(String[] args) {
        long err;
        JNIByteBuffer handleBuff = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
        JNIByteBuffer symbolBuff =
            new JNIByteBuffer(Convert.StringToByteArr("MAIN.PLCVar", true));
        JNIByteBuffer dataBuff = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);

        // Open communication
        long port = AdsCallDllFunction.adsPortOpenEx();
        if (port == 0) {
            System.out.println("Error: Unable to open an ADS port.");
        }

        AmsAddr addr = new AmsAddr();
        addr.setNetIdStringEx("X.X.X.X.1.1"); // ADJUST THIS VALUE!
        addr.setPort(851);

        // Uncomment and adjust if automatic AMS NetId deduction is
        // not working as expected:
        // {
        //     AmsAddr local = new AmsAddr();
        //     local.setNetIdStringEx("Y.Y.Y.Y.1.1");
        //     AdsCallDllFunction.adsSetLocalAddress(local.getNetId());
        // }

        // Since the AMS routing isn't handled by the TwinCAT AMS Router,
        // we need to tell the AdsLib which IP address is associated with the
        // AMS NetId.
        AdsCallDllFunction.adsAddLocalRoute(addr.getNetId(),
                                            "X.X.X.X"); // ADJUST THIS VALUE!

        // Get handle by symbol name
        JNILong lengthReturn = new JNILong();
        err = AdsCallDllFunction.adsSyncReadWriteReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, 0x0,
            handleBuff.getUsedBytesCount(), handleBuff,
            symbolBuff.getUsedBytesCount(), symbolBuff, lengthReturn);
        if (err != 0) {
            System.out.println("Error: Get handle: 0x" + Long.toHexString(err));
        } else {
            System.out.println("Success: Get handle!");
        }

        // Handle: byte[] to int
        int hdlBuffToInt = Convert.ByteArrToInt(handleBuff.getByteArray());

        // Read value by handle
        lengthReturn = new JNILong();
        err = AdsCallDllFunction.adsSyncReadReqEx2(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_VALBYHND, hdlBuffToInt,
            0x4, dataBuff, lengthReturn);
        if (err != 0) {
            System.out.println("Error: Read by handle: 0x" +
                               Long.toHexString(err));
        } else {
            // Data: byte[] to int
            int intVal = Convert.ByteArrToInt(dataBuff.getByteArray());
            System.out.println("Success: PLCVar value: " + intVal);
        }

        // Release handle
        err = AdsCallDllFunction.adsSyncWriteReqEx(
            port, addr, AdsCallDllFunction.ADSIGRP_SYM_RELEASEHND, 0,
            handleBuff.getUsedBytesCount(), handleBuff);

        if (err != 0) {
            System.out.println("Error: Release Handle: 0x" +
                               Long.toHexString(err));
        } else {
            System.out.println("Success: Release Handle!");
        }

        // Remove the associating between the AMS NetId and the IP address of
        // our target system
        AdsCallDllFunction.adsDelLocalRoute(addr.getNetId());

        // Close communication
        err = AdsCallDllFunction.adsPortCloseEx(port);
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