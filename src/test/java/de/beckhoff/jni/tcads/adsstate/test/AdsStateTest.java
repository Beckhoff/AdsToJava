package de.beckhoff.jni.tcads.adsstate.test;

import de.beckhoff.jni.tcads.AdsState;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class AdsStateTest extends TestCase {
    static final short INIT_ADSSTATE_STATE = 0;
    static final short NEW_ADSSTATE_STATE = 1;

    private AdsState state;

    public AdsStateTest(String name) { super(name); }

    @Override
    protected void setUp() {
        state = new AdsState();
    }

    @Override
    protected void tearDown() {}

    public void testInitialization() {
        assertEquals("Test initial state", INIT_ADSSTATE_STATE,
                     state.getState());
    }

    public void testSetState() {
        state.setState(NEW_ADSSTATE_STATE);
        assertEquals("Test new state", NEW_ADSSTATE_STATE, state.getState());
    }
}