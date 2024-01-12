import de.beckhoff.jni.AdsConstants;
import de.beckhoff.jni.JNILong;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AdsCallbackObject;
import de.beckhoff.jni.tcads.AdsNotificationAttrib;
import de.beckhoff.jni.tcads.AmsAddr;

public class Main {
    public static void main(String[] args) {
        try {
            long err;
            AmsAddr addr = new AmsAddr();
            JNILong notification = new JNILong();

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

            // Specify attributes of the notificationRequest
            AdsNotificationAttrib attr = new AdsNotificationAttrib();
            attr.setCbLength(Integer.SIZE / Byte.SIZE);
            attr.setNTransMode(AdsConstants.ADSTRANS_SERVERONCHA);
            attr.setDwChangeFilter(10000000); // 1 sec
            attr.setNMaxDelay(20000000);      // 2 sec

            // Create and add listener
            AdsListener listener = new AdsListener();
            AdsCallbackObject callObject = new AdsCallbackObject();
            callObject.addListenerCallbackAdsState(listener);

            // Create notificationHandle
            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr,
                0x4020, // IndexGroup
                0x0,    // IndexOffset
                attr,   // The defined AdsNotificationAttrib object
                42,     // Choose arbitrary number
                notification);
            if (err != 0) {
                System.out.println("Error: Add notification: 0x" +
                                   Long.toHexString(err));
            }

            // Read as long as user does not press return
            System.out.println("Press enter to continue..");
            System.in.read();

            // Delete notificationHandle
            err = AdsCallDllFunction.adsSyncDelDeviceNotificationReq(
                addr, notification);
            if (err != 0) {
                System.out.println("Error: Remove notification with handle 0x" +
                                   Long.toHexString(notification.getLong()) +
                                   ": result was 0x" + Long.toHexString(err));
            }

            // Delete listener
            callObject.removeListenerCallbackAdsState(listener);

            // Close communication
            err = AdsCallDllFunction.adsPortClose();
            if (err != 0) {
                System.out.println("Error: Close Communication: 0x" +
                                   Long.toHexString(err));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}