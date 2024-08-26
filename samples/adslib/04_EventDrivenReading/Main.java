import de.beckhoff.jni.AdsConstants;
import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.JNILong;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AdsCallbackObject;
import de.beckhoff.jni.tcads.AdsNotificationAttrib;
import de.beckhoff.jni.tcads.AmsAddr;

/**
 * Sample - add event listener for PLC variable
 * 
 * @author Fabio Richeri (fabio.richeri@sinolo.it)
 * @author Matteo Cartieri (matteo.cartieri02@gmail.com)
 *
 */
public class Main {
	public static void main(String[] args) {
		// Set up the ADS route between your Linux system and target PLC before running
		// the program. Usually <linux_netid> is <linux_ip> plus .1.1

		// ./adslib_for_linux/build/AdsTool/AdsTool <target_ip> addroute
		// --addr=<linux_ip> --netid=<linux_netid> --password=<target_password>

		try {
			long err;

			JNIByteBuffer handleBuff = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
			JNIByteBuffer symbolBuff = new JNIByteBuffer(Convert.StringToByteArr("MAIN.PLCVar", true));
			JNILong notification = new JNILong();

			// Open communication
			long port = AdsCallDllFunction.adsPortOpenEx();
			if (port == 0) {
				System.out.println("Error: Unable to open an ADS port.");
			}

			AmsAddr addr = new AmsAddr();
			// Set the netId of the PLC target 
			addr.setNetIdStringEx("X.X.X.X.1.1"); // TODO ADJUST THIS VALUE!
			addr.setPort(851);

			// Uncomment and adjust if automatic AMS NetId deduction is
			// not working as expected:
			// {
			// AmsAddr local = new AmsAddr();
			// local.setNetIdStringEx("Y.Y.Y.Y.1.1");
			// AdsCallDllFunction.adsSetLocalAddress(local.getNetId());
			// }

			// Since the AMS routing isn't handled by the TwinCAT AMS Router, we need
			// to tell the AdsLib which IP address is associated with the AMS NetId.
			// Set this with the IP of the PLC target
			AdsCallDllFunction.adsAddLocalRoute(addr.getNetId(), "X.X.X.X"); // TODO ADJUST THIS VALUE!

			// Get handle by symbol name
			JNILong lengthReturn = new JNILong();
			err = AdsCallDllFunction.adsSyncReadWriteReqEx2(port, addr, AdsCallDllFunction.ADSIGRP_SYM_HNDBYNAME, 0x0,
					handleBuff.getUsedBytesCount(), handleBuff, symbolBuff.getUsedBytesCount(), symbolBuff,
					lengthReturn);
			if (err != 0) {
				System.out.println("Error: Get handle: 0x" + Long.toHexString(err));
			} else {
				System.out.println("Success: Get handle!");
			}

			// Handle: byte[] to int
			int hdlBuffToInt = Convert.ByteArrToInt(handleBuff.getByteArray());

			// Write value by handle
			lengthReturn = new JNILong();
			// Specify attributes of the notificationRequest
			AdsNotificationAttrib attr = new AdsNotificationAttrib();
			attr.setCbLength(Integer.SIZE / Byte.SIZE);
			attr.setNTransMode(AdsConstants.ADSTRANS_SERVERONCHA);
			attr.setDwChangeFilter(10000000); // 1 sec
			attr.setNMaxDelay(20000000); // 2 sec

			// Create and add listener
			AdsListener listener = new AdsListener();
			AdsCallbackObject callObject = new AdsCallbackObject();
			callObject.addListenerCallbackAdsState(listener);

			// Create notificationHandle
			err = AdsCallDllFunction.adsSyncAddDeviceNotificationReqEx(port, addr,
					AdsCallDllFunction.ADSIGRP_SYM_VALBYHND, hdlBuffToInt, // IndexOffset
					attr, // The defined AdsNotificationAttrib object
					0, // Choose arbitrary number
					notification);

			if (err != 0) {
				System.out.println("Error: Add device notification: 0x" + Long.toHexString(err));
			} else {
				System.out.println("Success: Add device notification");
			}

			// Read as long as user does not press return
			System.out.println("Press enter to continue..\n");
			System.in.read();

			// Delete device notification
			err = AdsCallDllFunction.adsSyncDelDeviceNotificationReqEx(port, addr, notification);

			if (err != 0) {
				System.out.println("Error: Remove device notification: 0x" + Long.toHexString(err));
			}

			// Delete listener
			callObject.removeListenerCallbackAdsState(listener);

			// Release handle
			err = AdsCallDllFunction.adsSyncWriteReqEx(port, addr, AdsCallDllFunction.ADSIGRP_SYM_RELEASEHND, 0,
					handleBuff.getUsedBytesCount(), handleBuff);

			if (err != 0) {
				System.out.println("Error: Release Handle: 0x" + Long.toHexString(err));
			} else {
				System.out.println("Success: Release Handle!");
			}

			// Remove the associating between the AMS NetId and the IP address of
			// our target system
			AdsCallDllFunction.adsDelLocalRoute(addr.getNetId());

			// Close communication
			err = AdsCallDllFunction.adsPortCloseEx(port);
			if (err != 0) {
				System.out.println("Error: Close Communication: 0x" + Long.toHexString(err));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
