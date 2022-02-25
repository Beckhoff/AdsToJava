package de.beckhoff.jni.jnibytebuffer.test;

import static org.junit.Assert.*;

import de.beckhoff.jni.JNIByteBuffer;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class JNIByteBufferInitializationTest extends TestCase {
    final static byte[] ZERO_LEN_BYTE_ARR = new byte[0];
    final static int ZERO_LEN_BYTE_ARR_LEN = ZERO_LEN_BYTE_ARR.length;

    final static byte[] TEST_BYTE_ARR = new byte[] {
        0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, 0x20, 0x54, 0x65, 0x73, 0x74, 0x00};
    final static int TEST_BYTE_ARR_LEN = TEST_BYTE_ARR.length;

    final static int TEST_EMPTY_BYTE_ARR_LEN = 8;
    final static byte[] TEST_EMPTY_BYTE_ARR = new byte[TEST_EMPTY_BYTE_ARR_LEN];

    final static byte[] ADD_TERM_ZERO_BYTE_ARR =
        new byte[] {0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, 0x20, 0x54,
                    0x65, 0x73, 0x74, 0x00, 0x00, 0x00, 0x00, 0x00};
    final static int ADD_TERM_ZERO_BYTE_ARR_LEN = ADD_TERM_ZERO_BYTE_ARR.length;

    final static byte[] REMOVE_TERM_ZERO_BYTE_ARR =
        new byte[] {0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, 0x20, 0x54};
    final static int REMOVE_TERM_ZERO_BYTE_ARR_LEN =
        REMOVE_TERM_ZERO_BYTE_ARR.length;

    public JNIByteBufferInitializationTest(String name) { super(name); }

    public void testInitialization1() {
        JNIByteBuffer byteBuffer = new JNIByteBuffer();

        assertArrayEquals("Initialization1 test byte array", ZERO_LEN_BYTE_ARR,
                          byteBuffer.getByteArray());

        assertEquals("Initialization1 test length", ZERO_LEN_BYTE_ARR_LEN,
                     byteBuffer.getUsedBytesCount());
    }

    public void testInitialization2() {
        JNIByteBuffer byteBuffer = new JNIByteBuffer(TEST_BYTE_ARR);

        assertArrayEquals("Initialization2 test byte array", TEST_BYTE_ARR,
                          byteBuffer.getByteArray());
        assertEquals("Initialization2 test length", TEST_BYTE_ARR_LEN,
                     byteBuffer.getUsedBytesCount());
    }

    public void testInitialization2Null() {
        JNIByteBuffer byteBuffer = new JNIByteBuffer(null);

        assertArrayEquals("Initialization2 test null buffer byte array",
                          ZERO_LEN_BYTE_ARR, byteBuffer.getByteArray());

        assertEquals("Initialization2 test null buffer length",
                     ZERO_LEN_BYTE_ARR_LEN, byteBuffer.getUsedBytesCount());
    }

    public void testInitialization3() {
        JNIByteBuffer byteBuffer = new JNIByteBuffer(TEST_EMPTY_BYTE_ARR_LEN);

        assertArrayEquals("Initialization3 test byte array",
                          TEST_EMPTY_BYTE_ARR, byteBuffer.getByteArray());
        assertEquals("Initialization3 test length", TEST_EMPTY_BYTE_ARR_LEN,
                     byteBuffer.getUsedBytesCount());
    }

    public void testInitialization4() {
        JNIByteBuffer byteBuffer =
            new JNIByteBuffer(TEST_BYTE_ARR, TEST_BYTE_ARR_LEN, true);

        assertArrayEquals("Initialization4 test byte array", TEST_BYTE_ARR,
                          byteBuffer.getByteArray());
        assertEquals("Initialization4 test length", TEST_BYTE_ARR_LEN,
                     byteBuffer.getUsedBytesCount());
    }

    public void testInitialization5() {
        JNIByteBuffer byteBuffer =
            new JNIByteBuffer(TEST_BYTE_ARR, ADD_TERM_ZERO_BYTE_ARR_LEN, true);

        assertArrayEquals("Initialization5 test byte array",
                          ADD_TERM_ZERO_BYTE_ARR, byteBuffer.getByteArray());
        assertEquals("Initialization5 test length", ADD_TERM_ZERO_BYTE_ARR_LEN,
                     byteBuffer.getUsedBytesCount());
    }

    public void testInitialization6() {
        JNIByteBuffer byteBuffer = new JNIByteBuffer(
            TEST_BYTE_ARR, REMOVE_TERM_ZERO_BYTE_ARR_LEN, true);

        assertArrayEquals("Initialization6 test byte array",
                          REMOVE_TERM_ZERO_BYTE_ARR, byteBuffer.getByteArray());
        assertEquals("Initialization6 test length",
                     REMOVE_TERM_ZERO_BYTE_ARR_LEN,
                     byteBuffer.getUsedBytesCount());
    }

    public void testInitialization7() {
        JNIByteBuffer byteBuffer = new JNIByteBuffer(
            TEST_BYTE_ARR, REMOVE_TERM_ZERO_BYTE_ARR_LEN, false);

        assertArrayEquals("Initialization7 test byte array", TEST_BYTE_ARR,
                          byteBuffer.getByteArray());
        assertEquals("Initialization7 test length",
                     REMOVE_TERM_ZERO_BYTE_ARR_LEN,
                     byteBuffer.getUsedBytesCount());
    }

    public void testInitialization7Null() {
        JNIByteBuffer byteBuffer =
            new JNIByteBuffer(null, REMOVE_TERM_ZERO_BYTE_ARR_LEN, false);

        assertArrayEquals("Initialization7 test byte array", null,
                          byteBuffer.getByteArray());
        assertEquals("Initialization7 test length",
                     REMOVE_TERM_ZERO_BYTE_ARR_LEN,
                     byteBuffer.getUsedBytesCount());
    }
}