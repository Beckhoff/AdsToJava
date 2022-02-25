package de.beckhoff.jni.convert.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.Convert;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ConvertByteTest extends TestCase {
    private byte[] minData;
    private byte minRepresentation;
    private byte[] maxData;
    private byte maxRepresentation;

    public ConvertByteTest(String name) { super(name); }

    @Override
    protected void setUp() {
        minData = new byte[] {(byte)0x80};
        minRepresentation = Byte.MIN_VALUE;

        maxData = new byte[] {(byte)0x7f};
        maxRepresentation = Byte.MAX_VALUE;
    }

    @Override
    protected void tearDown() {}

    public void testConversionFromArray() {
        assertEquals("Conversion to byte (minimum)", minRepresentation,
                     Convert.ByteArrToByte(minData));

        assertEquals("Conversion to byte (maximum)", maxRepresentation,
                     Convert.ByteArrToByte(maxData));
    }

    public void testConversionToArray() {
        byte[] convertedData = Convert.ByteToByteArr(minRepresentation);
        assertArrayEquals("Conversion to byte array (minimal)", minData,
                          convertedData);

        convertedData = Convert.ByteToByteArr(maxRepresentation);
        assertArrayEquals("Conversion to byte array (maximal)", maxData,
                          convertedData);
    }
}