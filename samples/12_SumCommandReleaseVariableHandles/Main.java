import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Main {
    private static final String VAR_NAME1 = "GVL.bVar01"; // Different to TC2.x
    private static final String VAR_NAME2 = "GVL.bVar02"; // Different to TC2.x

    public static void main(String[] args) {
        long err = 0;

        try {
            AmsAddr addr = new AmsAddr();

            // Create request and response buffer
            JNIByteBuffer jniReqBuff;
            JNIByteBuffer jniResBuff = new JNIByteBuffer(new byte[24]);

            // Construct data objects
            RequestData req1 = new RequestData();
            req1.setIndexGroup(AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME);
            req1.setIndexOffset(0x0);
            req1.setReadLength(Integer.SIZE / Byte.SIZE);
            req1.setWriteLength(VAR_NAME1.length());

            RequestData req2 = new RequestData();
            req2.setIndexGroup(AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME);
            req2.setIndexOffset(0x0);
            req2.setReadLength(Integer.SIZE / Byte.SIZE);
            req2.setWriteLength(VAR_NAME2.length());

            // Concatenate byte[] representations of RequestData objects and
            // variable names (see picture 1)
            int reqBuffSize = RequestData.SIZE * 2 / Byte.SIZE +
                              req1.getWriteLength() + req2.getWriteLength();

            ByteBuffer reqBuff = ByteBuffer.allocate(reqBuffSize);
            reqBuff.order(ByteOrder.LITTLE_ENDIAN);

            reqBuff.put(req1.toByteArray());
            reqBuff.put(req2.toByteArray());
            reqBuff.put(Convert.StringToByteArr(VAR_NAME1, false));
            reqBuff.put(Convert.StringToByteArr(VAR_NAME2, false));

            // Need to let a JNIByteBuffer wrap the byte[] to be able to send it
            jniReqBuff = new JNIByteBuffer(reqBuff.array());

            // Open communication & get handles
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

            err = AdsCallDllFunction.adsSyncReadWriteReq(
                addr,
                0xf082,                         // ADS list-read-write command
                0x2,                            // number of ADS-sub commands
                jniResBuff.getUsedBytesCount(), // we expect an ADS error
                                                //  return-code for each ADS-sub
                                                //  command
                jniResBuff, // provide space for the response
                            // containing the return codes
                jniReqBuff.getUsedBytesCount(), // send 48 bytes(IG1,
                                                // IO1, RLen1, WLen1, IG2, IO2,
                                                // RLen2, WLen2, Data1, Data2)
                jniReqBuff);

            // Check return codes
            ByteBuffer resBuff = ByteBuffer.wrap(jniResBuff.getByteArray());
            resBuff.order(ByteOrder.LITTLE_ENDIAN);
            if (err != 0) {
                System.out.println("Error: Get handles: 0x" +
                                   Long.toHexString(err));
            } else {
                // Extract error codes from response (see picture 2)
                // Pattern is: err1, len1, .., errN, lenN, data1, .., dataN
                int req1Err = resBuff.getInt((Integer.SIZE / Byte.SIZE) * 0);
                int req2Err = resBuff.getInt((Integer.SIZE / Byte.SIZE) * 2);

                if (req1Err != 0) {
                    System.out.println("Error: Get handle1: 0x" +
                                       Integer.toHexString(req1Err));
                } else if (req2Err != 0) {
                    System.out.println("Error: Get handle2: 0x" +
                                       Integer.toHexString(req2Err));
                } else {
                    System.out.println("Success: Get handles!");
                }
            }

            // Extract handles from response(see picture 2)
            int hnd1 = resBuff.getInt((Integer.SIZE / Byte.SIZE) * 4);
            int hnd2 = resBuff.getInt((Integer.SIZE / Byte.SIZE) * 5);

            //// Release handles
            // Construct buffers
            int relBuffSize = (ReleaseData.SIZE * 2 / Byte.SIZE) +
                              (Integer.SIZE * 2 / Byte.SIZE);
            int relResBuffSize = Integer.SIZE * 2 / Byte.SIZE;

            ByteBuffer relBuff = ByteBuffer.allocate(relBuffSize);
            relBuff.order(ByteOrder.LITTLE_ENDIAN);
            JNIByteBuffer jniRelBuff = new JNIByteBuffer(relBuffSize);
            JNIByteBuffer jniRelResBuff = new JNIByteBuffer(relResBuffSize);

            // Construct data objects
            ReleaseData rel1 = new ReleaseData();
            rel1.setIndexGroup(AdsCallDllFunction.ADSIGRP_SYM_RELEASEHND);
            rel1.setIndexOffset(0);
            rel1.setLength(Integer.SIZE / Byte.SIZE);

            ReleaseData rel2 = new ReleaseData();
            rel2.setIndexGroup(AdsCallDllFunction.ADSIGRP_SYM_RELEASEHND);
            rel2.setIndexOffset(0);
            rel2.setLength(Integer.SIZE / Byte.SIZE);

            // Concatenate byte[] representations of ReleaseData objects and
            // handles (see picture 3)
            relBuff.put(rel1.toByteArray());
            relBuff.put(rel2.toByteArray());
            relBuff.putInt(hnd1);
            relBuff.putInt(hnd2);

            // Need to let a JNIByteBuffer wrap the byte[] to be able to send it
            jniRelBuff.setByteArray(relBuff.array(), true);

            // Release handles - Second task cleared
            err = AdsCallDllFunction.adsSyncReadWriteReq(
                addr,
                0xf081,         // ADS list-write command
                0x2,            // number of ADS-sub commands
                relResBuffSize, // we expect an ADS-error-return-code for
                                // each ADS-sub command
                jniRelResBuff,  // provide space for the response containing
                                // the return codes
                relBuffSize,    // send 32 bytes (IG1, IO1, Len1, IG2, IO2,
                                // Len2, Data1, Data2)
                jniRelBuff);    // buffer with data

            // Check return codes
            if (err != 0) {
                System.out.println("Error: Release handles: 0x" +
                                   Long.toHexString(err));
            } else {
                ByteBuffer relResBuff =
                    ByteBuffer.wrap(jniRelResBuff.getByteArray());
                relResBuff.order(ByteOrder.LITTLE_ENDIAN);

                // Extract error codes from response (see picture 4)
                // Pattern is: err1, .., errN
                int rel1Err = relResBuff.getInt((Integer.SIZE / Byte.SIZE) * 0);
                int rel2Err = relResBuff.getInt((Integer.SIZE / Byte.SIZE) * 1);

                if (rel1Err != 0) {
                    System.out.println("Error: Release handle1: 0x" +
                                       Integer.toHexString(rel1Err));
                } else if (rel2Err != 0) {
                    System.out.println("Error: Release handle2: 0x" +
                                       Integer.toHexString(rel2Err));
                } else {
                    System.out.println("Success: Release handles!");
                }
            }

            System.out.println("\nPress enter to continue..");
            System.in.read();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // Close communication
            err = AdsCallDllFunction.adsPortClose();

            if (err != 0) {
                System.out.println("Error: Close communication: 0x" +
                                   Long.toHexString(err));
            } else {
                System.out.println("Success: Close communication!");
            }
        }
    }
}