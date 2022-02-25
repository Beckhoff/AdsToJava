package de.beckhoff.jni.convert.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.Convert;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ConvertBoolTest extends TestCase {
    private byte[] minData;
    private boolean minRepresentation;
    private byte[] maxData;
    private boolean maxRepresentation;

    public ConvertBoolTest(String name) { super(name); }

    @Override
    protected void setUp() {
        minData = new byte[] {(byte)0x00};
        minRepresentation = Boolean.FALSE;

        maxData = new byte[] {(byte)0x01};
        maxRepresentation = Boolean.TRUE;
    }

    @Override
    protected void tearDown() {}

    public void testConversionFromArray() {
        assertEquals("Conversion to boolean (minimum)", minRepresentation,
                     Convert.ByteArrToBool(minData));

        assertEquals("Conversion to boolean (maximum)", maxRepresentation,
                     Convert.ByteArrToBool(maxData));
    }

    public void testConversionToArray() {
        byte[] convertedData = Convert.BoolToByteArr(minRepresentation);
        assertArrayEquals("Conversion to byte array (minimum)", minData,
                          convertedData);

        convertedData = Convert.BoolToByteArr(maxRepresentation);
        assertArrayEquals("Conversion to byte array (maximum)", maxData,
                          convertedData);
    }
}