package de.beckhoff.jni.convert.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.Convert;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ConvertShortTest extends TestCase {
    private byte[] minData;
    private short minRepresentation;
    private byte[] maxData;
    private short maxRepresentation;

    public ConvertShortTest(String name) { super(name); }

    @Override
    protected void setUp() {
        minData = new byte[] {0x00, (byte)0x80};
        minRepresentation = Short.MIN_VALUE;

        maxData = new byte[] {(byte)0xff, 0x7f};
        maxRepresentation = Short.MAX_VALUE;
    }

    @Override
    protected void tearDown() {}

    public void testConversionFromArray() {
        assertEquals("Conversion to short (minimum)", minRepresentation,
                     Convert.ByteArrToShort(minData));

        assertEquals("Conversion to short (maximum)", maxRepresentation,
                     Convert.ByteArrToShort(maxData));
    }

    public void testConversionToArray() {
        byte[] convertedData = Convert.ShortToByteArr(minRepresentation);
        assertArrayEquals("Conversion to byte array (minimum)", minData,
                          convertedData);

        convertedData = Convert.ShortToByteArr(maxRepresentation);
        assertArrayEquals("Conversion to byte array (maximum)", maxData,
                          convertedData);
    }
}