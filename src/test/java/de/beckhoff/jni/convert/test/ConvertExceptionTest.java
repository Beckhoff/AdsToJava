package de.beckhoff.jni.convert.test;

import de.beckhoff.jni.Convert;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class ConvertExceptionTest extends TestCase {
    byte[] data;

    public void testConversionByteArgumentException() {
        data = new byte[] {0x6f, 0x6f};

        try {
            Convert.ByteArrToByte(data);
            fail("IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testConversionShortArgumentException() {
        data = new byte[] {0x6f, 0x6f, 0x6f};

        try {
            Convert.ByteArrToShort(data);
            fail("IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testConversionIntegerArgumentException() {
        data = new byte[] {0x6f, 0x6f, 0x6f, 0x6f, 0x6f};

        try {
            Convert.ByteArrToInt(data);
            fail("IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testConversionLongArgumentException() {
        data =
            new byte[] {0x6f, 0x6f, 0x6f, 0x6f, 0x6f, 0x6f, 0x6f, 0x6f, 0x6f};

        try {
            Convert.ByteArrToLong(data);
            fail("IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testConversionFloatArgumentException() {
        data = new byte[] {0x6f, 0x6f, 0x6f, 0x6f, 0x6f};

        try {
            Convert.ByteArrToFloat(data);
            fail("IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testConversionDoubleArgumentException() {
        data =
            new byte[] {0x6f, 0x6f, 0x6f, 0x6f, 0x6f, 0x6f, 0x6f, 0x6f, 0x6f};

        try {
            Convert.ByteArrToDouble(data);
            fail("IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testConversionCharArgumentException() {
        data = new byte[] {0x6f, 0x6f, 0x6f};

        try {
            Convert.ByteArrToChar(data);
            fail("IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testConversionBoolArgumentException() {
        data = new byte[] {0x6f, 0x6f};

        try {
            Convert.ByteArrToBool(data);
            fail("IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }
}