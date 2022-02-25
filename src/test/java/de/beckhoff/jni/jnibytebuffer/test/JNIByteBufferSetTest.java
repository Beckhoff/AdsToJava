package de.beckhoff.jni.jnibytebuffer.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.JNIByteBuffer;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class JNIByteBufferSetTest extends TestCase {
    final static byte[] ORIG_TEST_ARR = new byte[] {
        0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, 0x20, 0x54, 0x65, 0x73, 0x74, 0x00};
    final static int ORIG_TEST_ARR_LEN = ORIG_TEST_ARR.length;

    final static byte[] SHORT_TEST_ARR =
        new byte[] {0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, 0x20, 0x54};
    final static int SHORT_TEST_ARR_LEN = SHORT_TEST_ARR.length;

    final static byte[] LONG_TEST_ARR =
        new byte[] {0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, 0x20, 0x54,
                    0x65, 0x73, 0x74, 0x00, 0x00, 0x00, 0x00, 0x00};
    final static int LONG_TEST_ARR_LEN = LONG_TEST_ARR.length;

    JNIByteBuffer byteBuffer;

    public JNIByteBufferSetTest(String name) { super(name); }

    @Override
    protected void setUp() {
        byteBuffer = new JNIByteBuffer(ORIG_TEST_ARR, ORIG_TEST_ARR_LEN, true);
    }

    @Override
    protected void tearDown() {}

    public void testSetShortByteArray() {
        this.byteBuffer.setByteArray(SHORT_TEST_ARR, true);

        assertArrayEquals("Check byte array (short)", SHORT_TEST_ARR,
                          this.byteBuffer.getByteArray());
        assertEquals("Check length (short)", SHORT_TEST_ARR_LEN,
                     this.byteBuffer.getUsedBytesCount());
    }

    public void testSetLongByteArray() {
        this.byteBuffer.setByteArray(LONG_TEST_ARR, true);

        assertArrayEquals("Check byte array (long)", LONG_TEST_ARR,
                          this.byteBuffer.getByteArray());
        assertEquals("Check length (long)", LONG_TEST_ARR_LEN,
                     this.byteBuffer.getUsedBytesCount());
    }

    public void testSetShortByteArrayAffectByteCount() {
        this.byteBuffer.setByteArray(SHORT_TEST_ARR, true);

        assertArrayEquals("Check byte array affect byte count(short)",
                          SHORT_TEST_ARR, this.byteBuffer.getByteArray());
        assertEquals("Check length affect byte count(short)",
                     SHORT_TEST_ARR_LEN, this.byteBuffer.getUsedBytesCount());
    }

    public void testSetLongByteArrayAffectByteCount() {
        this.byteBuffer.setByteArray(LONG_TEST_ARR, true);

        assertArrayEquals("Check byte array affect byte count(long)",
                          LONG_TEST_ARR, this.byteBuffer.getByteArray());
        assertEquals("Check length affect byte count(long)", LONG_TEST_ARR_LEN,
                     this.byteBuffer.getUsedBytesCount());
    }

    public void testSetShortLength() {
        this.byteBuffer.setUsedBytesCount(SHORT_TEST_ARR_LEN, true);

        assertArrayEquals("Check byte array (short)", SHORT_TEST_ARR,
                          this.byteBuffer.getByteArray());
        assertEquals("Check length (short)", SHORT_TEST_ARR_LEN,
                     this.byteBuffer.getUsedBytesCount());
    }

    public void testSetLongLength() {
        this.byteBuffer.setUsedBytesCount(LONG_TEST_ARR_LEN, true);

        assertArrayEquals("Check byte array (short)", LONG_TEST_ARR,
                          this.byteBuffer.getByteArray());
        assertEquals("Check length (short)", LONG_TEST_ARR_LEN,
                     this.byteBuffer.getUsedBytesCount());
    }

    public void testSetShortLengthAffectByteCount() {
        this.byteBuffer.setUsedBytesCount(SHORT_TEST_ARR_LEN, true);

        assertArrayEquals("Check byte array affect byte count(short)",
                          SHORT_TEST_ARR, this.byteBuffer.getByteArray());
        assertEquals("Check length affect byte count(short)",
                     SHORT_TEST_ARR_LEN, this.byteBuffer.getUsedBytesCount());
    }

    public void testSetLongLengthAffectByteCount() {
        this.byteBuffer.setUsedBytesCount(LONG_TEST_ARR_LEN, true);

        assertArrayEquals("Check byte array affect byte count(long)",
                          LONG_TEST_ARR, this.byteBuffer.getByteArray());
        assertEquals("Check length affect byte count(long)", LONG_TEST_ARR_LEN,
                     this.byteBuffer.getUsedBytesCount());
    }
}