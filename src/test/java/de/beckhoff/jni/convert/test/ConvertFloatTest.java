package de.beckhoff.jni.convert.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.AllTests;
import de.beckhoff.jni.Convert;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ConvertFloatTest extends TestCase {
    private byte[] minData;
    private float minRepresentation;
    private byte[] maxData;
    private float maxRepresentation;

    public ConvertFloatTest(String name) { super(name); }

    @Override
    protected void setUp() {
        minData = new byte[] {0x01, 0x00, 0x00, 0x00};
        minRepresentation = Float.MIN_VALUE;

        maxData = new byte[] {(byte)0xff, (byte)0xff, 0x7f, 0x7f};
        maxRepresentation = Float.MAX_VALUE;
    }

    @Override
    protected void tearDown() {}

    public void testConversionFromArray() {
        assertEquals("Conversion to float (minimum)", minRepresentation,
                     Convert.ByteArrToFloat(minData),
                     AllTests.FLOAT_COMPARE_DELTA);

        assertEquals("Conversion to float (maximum)", maxRepresentation,
                     Convert.ByteArrToFloat(maxData),
                     AllTests.FLOAT_COMPARE_DELTA);
    }

    public void testConversionToArray() {
        byte[] convertedData = Convert.FloatToByteArr(minRepresentation);
        assertArrayEquals("Conversion to byte array (minimum)", minData,
                          convertedData);

        convertedData = Convert.FloatToByteArr(maxRepresentation);
        assertArrayEquals("Conversion to byte array (maximum)", maxData,
                          convertedData);
    }
}
