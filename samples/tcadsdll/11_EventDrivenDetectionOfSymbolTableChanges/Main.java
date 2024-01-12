import de.beckhoff.jni.AdsConstants;
import de.beckhoff.jni.JNILong;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AdsCallbackObject;
import de.beckhoff.jni.tcads.AdsNotificationAttrib;
import de.beckhoff.jni.tcads.AmsAddr;

public class Main {
    public static void main(String[] args) {
        long err = 0;
        AmsAddr addr = new AmsAddr();

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

            JNILong notification = new JNILong();

            AdsNotificationAttrib attr = new AdsNotificationAttrib();
            attr.setCbLength(1);
            attr.setNTransMode(AdsConstants.ADSTRANS_SERVERONCHA);
            attr.setNMaxDelay(5000000);
            attr.setNCycleTime(5000000);

            // Create and add listener
            AdsListener listener = new AdsListener();
            AdsCallbackObject callObject = new AdsCallbackObject();
            callObject.addListenerCallbackAdsState(listener);

            err = AdsCallDllFunction.adsSyncAddDeviceNotificationReq(
                addr,
                AdsCallDllFunction.ADSIGRP_SYM_VERSION, // IndexGroup
                0,                                      // IndexOffset
                attr, // The defined AdsNotificationAttrib object
                0,    // Choose arbitrary number
                notification);
            if (err != 0) {
                System.out.println("Error: Add notification: 0x" +
                                   Long.toHexString(err));
            }

            // Read as long as user does not press return
            System.out.println("Press enter to continue..\n");
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
