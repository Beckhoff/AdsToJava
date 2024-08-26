import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import de.beckhoff.jni.Convert;
import de.beckhoff.jni.JNIByteBuffer;
import de.beckhoff.jni.JNILong;
import de.beckhoff.jni.tcads.AdsCallDllFunction;
import de.beckhoff.jni.tcads.AmsAddr;

/**
 * Sample - write PLC variable by its name
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

		long err;

		JNIByteBuffer handleBuff = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);
		JNIByteBuffer symbolBuff = new JNIByteBuffer(Convert.StringToByteArr("MAIN.PLCVar", true));
		// New buffer to hold integer value
		JNIByteBuffer dataBuff = new JNIByteBuffer(Integer.SIZE / Byte.SIZE);

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
				handleBuff.getUsedBytesCount(), handleBuff, symbolBuff.getUsedBytesCount(), symbolBuff, lengthReturn);
		if (err != 0) {
			System.out.println("Error: Get handle: 0x" + Long.toHexString(err));
		} else {
			System.out.println("Success: Get handle!");
		}

		// Handle: byte[] to int
		int hdlBuffToInt = Convert.ByteArrToInt(handleBuff.getByteArray());

		// Write byte array with new PLCVar value = 123
		int newValue = 123;
		ByteBuffer bb = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(newValue);
		byte[] byArr = bb.array();
		// Set data buffer
		dataBuff.setByteArray(byArr, false);

		// Write value by handle
		lengthReturn = new JNILong();
		err = AdsCallDllFunction.adsSyncWriteReqEx(port, addr, AdsCallDllFunction.ADSIGRP_SYM_VALBYHND, hdlBuffToInt,
				dataBuff.getUsedBytesCount(), dataBuff);

		if (err != 0) {
			System.out.println("Error: Write by handle: 0x" + Long.toHexString(err));
		} else {
			System.out.println("Success: PLCVar write value: " + newValue);
		}

		// Read value to check the write
		err = AdsCallDllFunction.adsSyncReadReqEx2(port, addr, AdsCallDllFunction.ADSIGRP_SYM_VALBYHND, hdlBuffToInt,
				dataBuff.getUsedBytesCount(), dataBuff, lengthReturn);
		if (err != 0) {
			System.out.println("Error: Read by handle: 0x" + Long.toHexString(err));
		} else {
			// Convert byte array to int
			int plcVar = Convert.ByteArrToInt(dataBuff.getByteArray());
			System.out.println("Success: PLCVar read value: " + plcVar);
		}

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

		try {
			System.in.read();
		} catch (Exception e) {
			System.out.println("Error: Close program");
		}
	}
}
