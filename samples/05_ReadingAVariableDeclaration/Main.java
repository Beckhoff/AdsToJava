import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AdsSymbolEntry;
import de.beckhoff.jni.tcads.AmsAddr;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Main {
    // TODO: Check real values of each constant.
    private static final int ADST_VOID = 0;
    private static final int ADST_INT8 = 16;
    private static final int ADST_UINT8 = 17;
    private static final int ADST_INT16 = 2;
    private static final int ADST_UINT16 = 18;
    private static final int ADST_INT32 = 3;
    private static final int ADST_UINT32 = 19;
    private static final int ADST_INT64 = 20;
    private static final int ADST_UINT64 = 21;
    private static final int ADST_REAL32 = 4;
    private static final int ADST_REAL64 = 5;
    private static final int ADST_STRING = 30;
    private static final int ADST_WSTRING = 31;
    private static final int ADST_REAL80 = 32;
    private static final int ADST_BIT = 33;
    private static final int ADST_BIGTYPE = 65;
    private static final int ADST_MAXTYPES = 67;

    private static final ValueString[] adsDatatypeString =
        new ValueString[] {new ValueString(ADST_VOID, "ADST_VOID"),
                           new ValueString(ADST_INT8, "ADST_INT8"),
                           new ValueString(ADST_UINT8, "ADST_UINT8"),
                           new ValueString(ADST_INT16, "ADST_INT16"),
                           new ValueString(ADST_UINT16, "ADST_UINT16"),
                           new ValueString(ADST_INT32, "ADST_INT32"),
                           new ValueString(ADST_UINT32, "ADST_UINT32"),
                           new ValueString(ADST_INT64, "ADST_INT64"),
                           new ValueString(ADST_UINT64, "ADST_UINT64"),
                           new ValueString(ADST_REAL32, "ADST_REAL32"),
                           new ValueString(ADST_REAL64, "ADST_REAL64"),
                           new ValueString(ADST_STRING, "ADST_STRING"),
                           new ValueString(ADST_WSTRING, "ADST_WSTRING"),
                           new ValueString(ADST_REAL80, "ADST_REAL80"),
                           new ValueString(ADST_BIT, "ADST_BIT"),
                           new ValueString(ADST_BIGTYPE, "ADST_BIGTYPE"),
                           new ValueString(ADST_MAXTYPES, "ADST_MAXTYPES")};

    public static void main(String[] args) {
        long err;
        AmsAddr addr = new AmsAddr();
        JNIByteBuffer readBuff = new JNIByteBuffer(0xFFFF);
        JNIByteBuffer writeBuff;
        String userInput = null;
        BufferedReader buffReader = null;

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

        while (true) {
            try {
                // User chooses which variable declaration to display
                System.out.print("Enter variable Name (e.g. MAIN.uintValue): ");

                buffReader =
                    new BufferedReader(new InputStreamReader(System.in));
                userInput = buffReader.readLine();

                System.out.println(userInput);

                // Initialize writeBuff with user data
                writeBuff = new JNIByteBuffer(
                    Convert.StringToByteArr(userInput, false));

                // Get variable declaration
                err = AdsCallDllFunction.adsSyncReadWriteReq(
                    addr, AdsCallDllFunction.ADSIGRP_SYM_INFOBYNAMEEX, 0,
                    readBuff.getUsedBytesCount(), readBuff,
                    writeBuff.getUsedBytesCount(), writeBuff);

                if (err != 0) {
                    System.out.println("Error: AdsSyncReadReq: 0x" +
                                       Long.toHexString(err));
                } else {
                    // Convert stream to AdsSymbolEntry
                    AdsSymbolEntry adsSymbolEntry =
                        new AdsSymbolEntry(readBuff.getByteArray());

                    // Write information to stdout
                    System.out.println("Name:\t\t" + adsSymbolEntry.getName());
                    System.out.println("Index Group:\t" +
                                       adsSymbolEntry.getiGroup());
                    System.out.println("Index Offset:\t" +
                                       adsSymbolEntry.getiOffs());
                    System.out.println("Size:\t\t" + adsSymbolEntry.getSize());
                    System.out.println("Type:\t\t" + adsSymbolEntry.getType());
                    System.out.println("Comment:\t" +
                                       adsSymbolEntry.getComment());

                    // Example: Separate treatment of data types
                    switch (adsSymbolEntry.getDataType()) {
                    case ADST_UINT32:
                        System.out.println("Datatype:\tADST_UINT32");

                        int elements = adsSymbolEntry.getSize() /
                                       (Integer.SIZE / Byte.SIZE);

                        JNIByteBuffer uint32Buff =
                            new JNIByteBuffer(adsSymbolEntry.getSize());

                        err = AdsCallDllFunction.adsSyncReadReq(
                            addr, adsSymbolEntry.getiGroup(),
                            adsSymbolEntry.getiOffs(), adsSymbolEntry.getSize(),
                            uint32Buff);

                        if (err != 0) {
                            System.out.println("Error: AdsSyncReadReq: 0x" +
                                               Long.toHexString(err));
                        } else {
                            ByteBuffer bb = ByteBuffer.allocate(0);
                            bb = ByteBuffer.wrap(uint32Buff.getByteArray());
                            bb.order(ByteOrder.LITTLE_ENDIAN);

                            System.out.print("Value:\t\t");

                            // In case of an array print each value.
                            for (int i = 0; i < elements; i++)
                                System.out.print(bb.getInt() + "\t");

                            System.out.println("\n");
                        }
                        break;

                    default:
                        // Iterate through ValueString[] and try to find a
                        // datatype-match
                        for (int i = 0; i < adsDatatypeString.length; i++) {
                            if (adsDatatypeString[i].getValue() ==
                                adsSymbolEntry.getDataType()) {
                                System.out.println(
                                    "Datatype: "
                                    + "\t" + adsDatatypeString[i].getLabel());
                            }
                        }
                        break;
                    }
                }

                // User chooses to repeat process or not
                System.out.print("Exit(y/n): ");
                buffReader =
                    new BufferedReader(new InputStreamReader(System.in));
                userInput = buffReader.readLine();

                if ("y".equals(userInput.toLowerCase()))
                    break;

            } catch (Exception ex) {
                System.out.print(ex.getMessage());
            }
        }

        try {
            buffReader.close();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

        // Close communication
        err = AdsCallDllFunction.adsPortClose();
        if (err != 0) {
            System.out.println("Error: Close Communication: 0x" +
                               Long.toHexString(err));
        }
    }
}