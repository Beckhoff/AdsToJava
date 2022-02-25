package de.beckhoff.jni.jnibytebuffer.test;

import de.beckhoff.jni.JNIByteBuffer;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class JNIByteBufferInitializationExceptionTest extends TestCase {
    final static byte[] TEST_BYTE_ARR = new byte[] {
        0x53, 0x74, 0x72, 0x69, 0x6E, 0x67, 0x20, 0x54, 0x65, 0x73, 0x74, 0x00};
    final static int TEST_BYTE_ARR_LEN = TEST_BYTE_ARR.length;
    final static int NEGATIVE_ARR_LEN = -4;

    public void testInitializationNegativeLength() {
        try {
            JNIByteBuffer byteBuffer =
                new JNIByteBuffer(TEST_BYTE_ARR, NEGATIVE_ARR_LEN, true);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException ex) {
        }
    }

    public void testInitializationNullBuff() {
        try {
            JNIByteBuffer byteBuffer =
                new JNIByteBuffer(null, TEST_BYTE_ARR_LEN, true);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
        }
    }
}