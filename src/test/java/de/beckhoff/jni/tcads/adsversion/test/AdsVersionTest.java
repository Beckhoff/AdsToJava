package de.beckhoff.jni.tcads.adsversion.test;

import de.beckhoff.jni.tcads.AdsVersion;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class AdsVersionTest extends TestCase {
    static final short INIT_ADSVERSION_VERSION = 0;
    static final char INIT_ADSVERSION_BUILD = 0;
    static final char INIT_ADSVERSION_REVISION = 0;

    public AdsVersionTest(String name) { super(name); }

    public void testInitialization() {
        AdsVersion version = new AdsVersion();

        assertEquals("Test ADS-version", INIT_ADSVERSION_VERSION,
                     version.getVersion());

        assertEquals("Test ADS-build", INIT_ADSVERSION_BUILD,
                     version.getBuild());

        assertEquals("Test ADS-revision", INIT_ADSVERSION_REVISION,
                     version.getRevision());
    }
}