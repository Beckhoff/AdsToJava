package de.beckhoff.jni.convert.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.Convert;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ConvertLongTest extends TestCase {
    private byte[] minData;
    private long minRepresentation;
    private byte[] maxData;
    private long maxRepresentation;

    public ConvertLongTest(String name) { super(name); }

    @Override
    protected void setUp() {
        minData =
            new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte)0x80};
        minRepresentation = Long.MIN_VALUE;

        maxData = new byte[] {(byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
                              (byte)0xff, (byte)0xff, (byte)0xff, 0x7f};
        maxRepresentation = Long.MAX_VALUE;
    }

    @Override
    protected void tearDown() {}

    public void testConversionFromArray() {
        assertEquals("Conversion to long (minimum)", minRepresentation,
                     Convert.ByteArrToLong(minData));

        assertEquals("Conversion to long (maximum)", maxRepresentation,
                     Convert.ByteArrToLong(maxData));
    }

    public void testConversionToArray() {
        byte[] convertedData = Convert.LongToByteArr(minRepresentation);
        assertArrayEquals("Conversion to byte array (minimum)", minData,
                          convertedData);

        convertedData = Convert.LongToByteArr(maxRepresentation);
        assertArrayEquals("Conversion to byte array (maximum)", maxData,
                          convertedData);
    }
}