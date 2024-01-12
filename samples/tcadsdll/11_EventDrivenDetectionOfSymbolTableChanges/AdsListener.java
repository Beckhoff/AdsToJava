import de.beckhoff.jni.tcads.AdsNotificationHeader;
import de.beckhoff.jni.tcads.AmsAddr;
import de.beckhoff.jni.tcads.CallbackListenerAdsState;

public class AdsListener implements CallbackListenerAdsState {
    // Callback function
    @Override
    public void onEvent(AmsAddr addr, AdsNotificationHeader notification,
                        long user) {
        System.out.println("The symbol table has changed!");
    }
}