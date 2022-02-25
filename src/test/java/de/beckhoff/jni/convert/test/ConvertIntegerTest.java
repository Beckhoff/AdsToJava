package de.beckhoff.jni.convert.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.Convert;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ConvertIntegerTest extends TestCase {
    private byte[] minData;
    private int minRepresentation;
    private byte[] maxData;
    private int maxRepresentation;

    public ConvertIntegerTest(String name) { super(name); }

    @Override
    protected void setUp() {
        minData = new byte[] {0x00, 0x00, 0x00, (byte)0x80};
        minRepresentation = Integer.MIN_VALUE;

        maxData = new byte[] {(byte)0xff, (byte)0xff, (byte)0xff, 0x7f};
        maxRepresentation = Integer.MAX_VALUE;
    }

    @Override
    protected void tearDown() {}

    public void testConversionFromArray() {
        assertEquals("Conversion to int (minimum)", minRepresentation,
                     Convert.ByteArrToInt(minData));

        assertEquals("Conversion to int (maximum)", maxRepresentation,
                     Convert.ByteArrToInt(maxData));
    }

    public void testConversionToArray() {
        byte[] convertedData = Convert.IntToByteArr(minRepresentation);
        assertArrayEquals("Conversion to byte array (minimum)", minData,
                          convertedData);

        convertedData = Convert.IntToByteArr(maxRepresentation);
        assertArrayEquals("Conversion to byte array (maximum)", maxData,
                          convertedData);
    }
}