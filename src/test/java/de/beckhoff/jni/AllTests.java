package de.beckhoff.jni;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author Beckhoff Automation
 */
public class AllTests extends TestCase {
    public static final String TWINCAT2X = "TwinCat 2.x";
    public static final String TWINCAT3X = "TwinCat 3.x";
    public static final String WHICH_TWINCAT = AllTests.TWINCAT3X;

    public static final JNIByteBuffer NAME_BUFFER_BYTE_VALUE =
        new JNIByteBuffer("MAIN.sintVal".getBytes());
    public static final JNIByteBuffer NAME_BUFFER_SHORT_VALUE =
        new JNIByteBuffer("MAIN.intVal".getBytes());
    public static final JNIByteBuffer NAME_BUFFER_INT_VALUE =
        new JNIByteBuffer("MAIN.dintVal".getBytes());
    public static final JNIByteBuffer NAME_BUFFER_BOOL_VALUE =
        new JNIByteBuffer("MAIN.boolVal".getBytes());
    public static final JNIByteBuffer NAME_BUFFER_FLOAT_VALUE =
        new JNIByteBuffer("MAIN.floatVal".getBytes());
    public static final JNIByteBuffer NAME_BUFFER_DOUBLE_VALUE =
        new JNIByteBuffer("MAIN.doubleVal".getBytes());
    public static final JNIByteBuffer NAME_BUFFER_STRING_VALUE =
        new JNIByteBuffer("MAIN.stringVal".getBytes());
    public static final JNIByteBuffer NAME_BUFFER_INVALID_SYMNAME =
        new JNIByteBuffer("MAIN.invalid".getBytes());
    public static final JNIByteBuffer NAME_BUFFER_NULL = null;

    public static final boolean BOOL_VAL = true;
    public static final byte BYTE_VAL = Byte.MAX_VALUE;
    public static final short SHORT_VAL = Short.MAX_VALUE;
    public static final int INT_VAL = Integer.MAX_VALUE;
    public static final float FLOAT_VAL = Float.MAX_VALUE;
    public static final double DOUBLE_VAL = Double.MAX_VALUE;
    public static final String STRING_VAL = WHICH_TWINCAT;

    public static final boolean BOOL_VALUE_DEFAULT = false;
    public static final byte BYTE_VALUE_DEFAULT = 0;
    public static final short SHORT_VALUE_DEFAULT = 0;
    public static final int INT_VALUE_DEFAULT = 0;
    public static final float FLOAT_VALUE_DEFAULT = 0.0f;
    public static final double DOUBLE_VALUE_DEFAULT = 0.0;
    public static final String STRING_VALUE_DEFAULT = "";

    public static final long INDEX_GROUP = 0x4020;
    public static final long INDEX_OFF1 = 0x0;
    public static final long INDEX_OFF2 = 0x8;
    public static final long INDEX_OFF3 = 0x10;
    public static final long INDEX_OFF4 = 0x18;
    public static final long INDEX_OFF5 = 0x20;
    public static final long INDEX_OFF6 = 0x28;
    public static final long INDEX_OFF7 = 0x30;

    public static final short DEVICE_PORT = 851;
    public static final String DEVICE_AMSADDR = "127.0.0.1.1.1";
    public static final String DEVICE_NAME = "Plc30 App"; // TCatPlcCtrl
    public static final String EMPTY_DEVICE_NAME = "";

    public static final int ADS_PORTNUMBER_MIN = 32768;
    public static final int ADS_PORTNUMBER_MAX = 49150;

    public static final double FLOAT_COMPARE_DELTA = 0.00001;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AllTests.class);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(de.beckhoff.jni.convert.test.AllTests.suite());
        suite.addTest(de.beckhoff.jni.jnibytebuffer.test.AllTests.suite());
        suite.addTest(de.beckhoff.jni.jnilong.test.AllTests.suite());
        suite.addTest(
            de.beckhoff.jni.tcads.adscalldllfunction.test.AllTests.suite());
        suite.addTest(
            de.beckhoff.jni.tcads.adscalldllfunctionex.test.AllTests.suite());
        suite.addTest(
            de.beckhoff.jni.tcads.adsdevicename.test.AllTests.suite());
        suite.addTest(
            de.beckhoff.jni.tcads.adsnotificationattrib.test.AllTests.suite());
        suite.addTest(
            de.beckhoff.jni.tcads.adsnotificationheader.test.AllTests.suite());
        suite.addTest(de.beckhoff.jni.tcads.adsstate.test.AllTests.suite());
        suite.addTest(
            de.beckhoff.jni.tcads.adssymbolentry.test.AllTests.suite());
        suite.addTest(de.beckhoff.jni.tcads.adsversion.test.AllTests.suite());
        suite.addTest(de.beckhoff.jni.tcads.amsaddr.test.AllTests.suite());
        suite.addTest(de.beckhoff.jni.tcads.amsnetid.test.AllTests.suite());

        return suite;
    }
}
