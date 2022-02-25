
package de.beckhoff.jni.jnilong.test;

import de.beckhoff.jni.JNILong;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class JNILongTest extends TestCase {
    static final long INIT_LONG = 12345678;
    static final long NEW_LONG = 87654321;
    private JNILong jniLong;

    public JNILongTest(String name) { super(name); }

    @Override
    protected void setUp() {
        jniLong = new JNILong(12345678);
    }

    @Override
    protected void tearDown() {}

    public void testInitialization() {
        assertEquals("Test initial long", INIT_LONG, jniLong.getLong());
    }

    public void testSetLong() {
        jniLong.setLong(NEW_LONG);
        assertEquals("Test new long", NEW_LONG, jniLong.getLong());
    }
}
