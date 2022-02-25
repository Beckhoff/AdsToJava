package de.beckhoff.jni.convert.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.Convert;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ConvertCharTest extends TestCase {
    private byte[] minData;
    private char minRepresentation;
    private byte[] maxData;
    private char maxRepresentation;
    private byte[] minLowSurrogateData;
    private char minLowSurrogate;
    private byte[] minHighSurrogateData;
    private char minHighSurrogate;
    private byte[] maxLowSurrogateData;
    private char maxLowSurrogate;
    private byte[] maxHighSurrogateData;
    private char maxHighSurrogate;

    public ConvertCharTest(String name) { super(name); }

    @Override
    protected void setUp() {
        minData = new byte[] {0x00, 0x00};
        minRepresentation = Character.MIN_VALUE;

        maxData = new byte[] {(byte)0xff, (byte)0xff};
        maxRepresentation = Character.MAX_VALUE;

        minLowSurrogateData = new byte[] {(byte)0xdc, (byte)0x00};
        minLowSurrogate = Character.MIN_LOW_SURROGATE;

        minHighSurrogateData = new byte[] {(byte)0xd8, (byte)0x00};
        minHighSurrogate = Character.MIN_HIGH_SURROGATE;

        maxLowSurrogateData = new byte[] {(byte)0xdf, (byte)0xff};
        maxLowSurrogate = Character.MAX_LOW_SURROGATE;

        maxHighSurrogateData = new byte[] {(byte)0xdb, (byte)0xff};
        maxHighSurrogate = Character.MAX_HIGH_SURROGATE;
    }

    @Override
    protected void tearDown() {}

    public void testConversionFromArray() {
        assertEquals("Conversion to char (MIN_VALUE)", minRepresentation,
                     Convert.ByteArrToChar(minData));

        assertEquals("Conversion to char (MAX_VALUE)", maxRepresentation,
                     Convert.ByteArrToChar(maxData));

        assertEquals("Conversion to char (MIN_LOW_SURROGATE)", minLowSurrogate,
                     Convert.ByteArrToChar(minLowSurrogateData));

        assertEquals("Conversion to char (MIN_HIGH_SURROGATE)",
                     minHighSurrogate,
                     Convert.ByteArrToChar(minHighSurrogateData));

        assertEquals("Conversion to char (MAX_LOW_SURROGATE)", maxLowSurrogate,
                     Convert.ByteArrToChar(maxLowSurrogateData));

        assertEquals("Conversion to char (MAX_HIGH_SURROGATE)",
                     maxHighSurrogate,
                     Convert.ByteArrToChar(maxHighSurrogateData));
    }

    public void testConversionToArray() {
        assertEquals("This function is not implemented for this character size",
                     Character.SIZE / 8, 2);

        byte[] convertedData = Convert.CharToByteArr(minRepresentation);
        assertArrayEquals("Conversion to byte (MIN_VALUE)", minData,
                          convertedData);

        convertedData = Convert.CharToByteArr(maxRepresentation);
        assertArrayEquals("Conversion to byte (MAX_VALUE)", maxData,
                          convertedData);

        convertedData = Convert.CharToByteArr(minLowSurrogate);
        assertArrayEquals("Conversion to byte (MIN_LOW_SURROGATE)",
                          minLowSurrogateData, convertedData);

        convertedData = Convert.CharToByteArr(minHighSurrogate);
        assertArrayEquals("Conversion to byte (MIN_HIGH_SURROGATE)",
                          minHighSurrogateData, convertedData);

        convertedData = Convert.CharToByteArr(maxLowSurrogate);
        assertArrayEquals("Conversion to byte (MAX_LOW_SURROGATE)",
                          maxLowSurrogateData, convertedData);

        convertedData = Convert.CharToByteArr(maxHighSurrogate);
        assertArrayEquals("Conversion to byte (MAX_HIGH_SURROGATE)",
                          maxHighSurrogateData, convertedData);
    }
}