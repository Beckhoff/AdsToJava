package de.beckhoff.jni.convert.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.Convert;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ConvertDoubleTest extends TestCase {
    private byte[] minData;
    private double minRepresentation;
    private byte[] maxData;
    private double maxRepresentation;

    public ConvertDoubleTest(String name) { super(name); }

    @Override
    protected void setUp() {
        minData = new byte[] {0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        minRepresentation = Double.MIN_VALUE;

        maxData = new byte[] {(byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
                              (byte)0xff, (byte)0xff, (byte)0xef, 0x7f};
        maxRepresentation = Double.MAX_VALUE;
    }

    @Override
    protected void tearDown() {}

    public void testConversionFromArray() {
        assertEquals("Conversion to double (minimum)", minRepresentation,
                     Convert.ByteArrToDouble(minData),
                     AllTests.FLOAT_COMPARE_DELTA);

        assertEquals("Conversion to double (maximum)", maxRepresentation,
                     Convert.ByteArrToDouble(maxData),
                     AllTests.FLOAT_COMPARE_DELTA);
    }

    public void testConversionToArray() {
        byte[] convertedData = Convert.DoubleToByteArr(minRepresentation);
        assertArrayEquals("Conversion to byte array (minimum)", minData,
                          convertedData);

        convertedData = Convert.DoubleToByteArr(maxRepresentation);
        assertArrayEquals("Conversion to byte array (maximum)", maxData,
                          convertedData);
    }
}