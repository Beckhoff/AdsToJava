
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Main {
    private final static RequestData[] REQ_DATA =
        new RequestData[] {new RequestData("uintValue", 0x4020, 0x0, 0x2),
                           new RequestData("boolValue", 0x4020, 0x8, 0x1),
                           new RequestData("dintValue", 0x4020, 0x10, 0x4)};
    // The data that is going to be written in the third step
    private final static char CHAR_VAL = Character.MAX_VALUE;
    private final static byte BOOL_VAL = 1;
    private final static int INT_VAL = Integer.MIN_VALUE;
    private final static int DATA_LEN = 7;
    private final static int RESP_ERR_LEN = 4;

    public static void main(String[] args) {
        long err;
        AmsAddr addr = new AmsAddr();

        try {
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

            // Create request buffer (see picture 1)
            JNIByteBuffer jniRequestBuffer = null;
            ByteBuffer requestBuffer =
                ByteBuffer.allocate(RequestData.SIZE * REQ_DATA.length);
            requestBuffer.order(ByteOrder.LITTLE_ENDIAN);

            for (int i = 0; i < REQ_DATA.length; i++) {
                requestBuffer.put(REQ_DATA[i].toByteArray());
            }
            // Need to let a JNIByteBuffer wrap the byte[] to be able to send it
            jniRequestBuffer = new JNIByteBuffer(requestBuffer.array());

            // Create response buffer (see picture 2)
            JNIByteBuffer jniResponseBuffer =
                new JNIByteBuffer(REQ_DATA.length * RESP_ERR_LEN + DATA_LEN);

            // Read the values via sum command
            err = AdsCallDllFunction.adsSyncReadWriteReq(
                addr, 0xF080, REQ_DATA.length,
                jniResponseBuffer.getUsedBytesCount(), jniResponseBuffer,
                jniRequestBuffer.getUsedBytesCount(), jniRequestBuffer);

            if (err != 0) {
                System.out.println("Error: Get values: 0x" +
                                   Long.toHexString(err));
            } else {
                System.out.println("Success: Get values!");

                // Evaluate the sub commands error codes and response values
                // (see picture)
                ByteBuffer responseBuffer =
                    ByteBuffer.wrap(jniResponseBuffer.getByteArray());
                responseBuffer.order(ByteOrder.LITTLE_ENDIAN);

                // In each loop the errPosition is increased by the Size of an
                // error (int) and the valPosition by the size of the
                // corresponding data type

                int[] respErrs = new int[REQ_DATA.length];
                for (int i = 0; i < respErrs.length; i++) {
                    respErrs[i] = responseBuffer.getInt();
                }

                for (int i = 0; i < REQ_DATA.length; i++) {
                    String varName = REQ_DATA[i].getVarName();
                    int val = 0;

                    if (respErrs[i] == 0) {
                        switch (REQ_DATA[i].getLength()) {
                        case 1:
                            val = (int)responseBuffer.get();
                            break;
                        case 2:
                            val = responseBuffer.getChar();
                            break;
                        case 4:
                            val = responseBuffer.getInt();
                            break;
                        default:
                            break;
                        }

                        System.out.println(varName + ": " + val + "\t");
                    } else {
                        System.out.println(varName + ": Error: 0x" +
                                           Long.toHexString(err) + "\t");
                    }
                }
            }

            //// Write values
            // Construct buffers. Fill request buffer with the data from above
            // and append the data that is going to be written (see picture 3)
            byte[] requestBufferCopy = requestBuffer.array();
            requestBuffer =
                ByteBuffer.allocate(requestBuffer.capacity() + DATA_LEN);
            requestBuffer.order(ByteOrder.LITTLE_ENDIAN);

            requestBuffer.put(requestBufferCopy);
            requestBuffer.putChar(CHAR_VAL);
            requestBuffer.put(BOOL_VAL);
            requestBuffer.putInt(INT_VAL);

            // Need to let a JNIByteBuffer wrap the byte[] to be able to send it
            jniRequestBuffer.setByteArray(requestBuffer.array(), true);
            // Adjust the length of the response buffer (compare pictures 2 & 4)
            jniResponseBuffer.setUsedBytesCount(8 * REQ_DATA.length, true);

            // Write the values via sum command
            err = AdsCallDllFunction.adsSyncReadWriteReq(
                addr, 0xF081, REQ_DATA.length,
                jniResponseBuffer.getUsedBytesCount(), jniResponseBuffer,
                jniRequestBuffer.getUsedBytesCount(), jniRequestBuffer);

            if (err != 0) {
                System.out.println("Error: Write values: 0x" +
                                   Long.toHexString(err));
            } else {
                System.out.println("Success: Write values!");

                // Evaluate the sub command error codes
                ByteBuffer responseBuffer =
                    ByteBuffer.wrap(jniResponseBuffer.getByteArray());
                responseBuffer.order(ByteOrder.LITTLE_ENDIAN);

                for (int i = 0; i < REQ_DATA.length; i++) {
                    String varName = REQ_DATA[i].getVarName();
                    err = responseBuffer.getInt();

                    if (err == 0) {
                        System.out.println(varName + ": Success\t");
                    } else {
                        System.out.println(varName + ": Error: 0x" +
                                           Long.toHexString(err) + "\t");
                    }
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