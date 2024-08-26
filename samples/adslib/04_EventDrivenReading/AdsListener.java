import de.beckhoff.jni.Convert;
import de.beckhoff.jni.tcads.AdsNotificationHeader;
import de.beckhoff.jni.tcads.AmsAddr;
import de.beckhoff.jni.tcads.CallbackListenerAdsState;
import java.util.Date;

public class AdsListener implements CallbackListenerAdsState {
	private final static long SPAN = 11644473600000L;

	// Callback function
	public void onEvent(AmsAddr addr, AdsNotificationHeader notification, long user) {

		// The PLC timestamp is coded in Windows FILETIME.
		// Nano secs since 01.01.1601.
		long dateInMillis = notification.getNTimeStamp();

		// Date accepts millisecs since 01.01.1970.
		// Convert to millisecs and substract span.
		Date notificationDate = new Date(dateInMillis / 10000 - SPAN);

		System.out.println("Value:\t\t" + Convert.ByteArrToInt(notification.getData()));
		System.out.println("Notification:\t" + notification.getHNotification());
		System.out.println("Time:\t\t" + notificationDate.toString());
		System.out.println("User:\t\t" + user);
		System.out.println("ServerNetID:\t" + addr.getNetIdString() + "\n");
	}
}
