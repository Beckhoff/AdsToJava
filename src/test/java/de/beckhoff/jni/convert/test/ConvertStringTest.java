package de.beckhoff.jni.convert.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.Convert;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ConvertStringTest extends TestCase {
    private final static byte[] DATA = new byte[] {
        0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, 0x20, 0x54, 0x65, 0x73, 0x74};
    private final static byte[] DATA_ZERO_BYTE = new byte[] {
        0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, 0x20, 0x54, 0x65, 0x73, 0x74, 0x00};
    private final static String REPRESENTATION = "String Test";

    public ConvertStringTest(String name) { super(name); }

    @Override
    protected void setUp() {}

    @Override
    protected void tearDown() {}

    public void testConversionFromArray() {
        assertEquals("Conversion to String", REPRESENTATION,
                     Convert.ByteArrToString(DATA));
        assertEquals("Conversion to String with terminating zero byte",
                     REPRESENTATION, Convert.ByteArrToString(DATA_ZERO_BYTE));
    }

    public void testConversionToArray0() {
        byte[] convertedData = Convert.StringToByteArr("", true);
        assertArrayEquals("Conversion0 to byte array", new byte[] {0x00},
                          convertedData);
    }

    public void testConversionToArray1() {
        byte[] convertedData = Convert.StringToByteArr(REPRESENTATION);
        assertArrayEquals("Conversion1 to byte array", DATA, convertedData);
    }

    public void testConversionToArray2() {
        byte[] convertedData = Convert.StringToByteArr(REPRESENTATION, true);
        assertArrayEquals("Conversion2 to byte array", DATA_ZERO_BYTE,
                          convertedData);
    }

    public void testConversionToArray3() {
        byte[] convertedData = Convert.StringToByteArr(REPRESENTATION, false);
        assertArrayEquals("Conversion3 to byte array", DATA, convertedData);
    }

    public void testConversionToArray4() {
        byte[] convertedData =
            Convert.StringToByteArr(REPRESENTATION, false, "xxx");
        assertEquals("Conversion4 to byte array", 0, convertedData.length);
    }
}