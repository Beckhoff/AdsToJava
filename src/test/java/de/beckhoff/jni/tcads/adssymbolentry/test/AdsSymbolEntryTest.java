package de.beckhoff.jni.tcads.adssymbolentry.test;

import de.beckhoff.jni.tcads.AdsSymbolEntry;
import junit.framework.TestCase;

/**
 *
 * @author Beckhoff Automation
 */
public class AdsSymbolEntryTest extends TestCase {
    static final byte[] INIT_ADSSYM_BUFF = new byte[] {
        0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00,
        0x00, 0x03, 0x00, 0x00, 0x00, 0x04, 0x00, 0x00, 0x00, 0x05, 0x00,
        0x00, 0x00, 0x03, 0x00, 0x03, 0x00, 0x03, 0x00, 0x42, 0x65, 0x63,
        0x00, 0x6B, 0x68, 0x6F, 0x00, 0x66, 0x66, 0x21, 0x00};

    static final int INIT_ADSSYM_ENTRY_LEN = 0;
    static final int INIT_ADSSYM_IGROUP = 1;
    static final int INIT_ADSSYM_IOFF = 2;
    static final int INIT_ADSSYM_SIZE = 3;
    static final int INIT_ADSSYM_DATATYPE = 4;
    static final int INIT_ADSSYM_FLAGS = 5;
    static final int INIT_ADSSYM_NAME_LEN = 3;
    static final int INIT_ADSSYM_TYPE_LEN = 3;
    static final int INIT_ADSSYM_COMMENT_LEN = 3;
    static final String INIT_ADSSYM_NAME = "Bec";
    static final String INIT_ADSSYM_TYPE = "kho";
    static final String INIT_ADSSYM_COMMENT = "ff!";

    private AdsSymbolEntry symbol;

    public AdsSymbolEntryTest(String name) { super(name); }

    @Override
    protected void setUp() {
        symbol = new AdsSymbolEntry(INIT_ADSSYM_BUFF);
    }

    @Override
    protected void tearDown() {}

    public void testInitialization() {
        assertEquals("Test initial entry length", INIT_ADSSYM_ENTRY_LEN,
                     symbol.getEntryLen());

        assertEquals("Test initial index group", INIT_ADSSYM_IGROUP,
                     symbol.getiGroup());

        assertEquals("Test initial index offset", INIT_ADSSYM_IOFF,
                     symbol.getiOffs());

        assertEquals("Test initial size", INIT_ADSSYM_SIZE, symbol.getSize());

        assertEquals("Test initial datatype", INIT_ADSSYM_DATATYPE,
                     symbol.getDataType());

        assertEquals("Test initial flags", INIT_ADSSYM_FLAGS,
                     symbol.getFlags());

        assertEquals("Test initial name length", INIT_ADSSYM_NAME_LEN,
                     symbol.getNameLength());

        assertEquals("Test initial type length", INIT_ADSSYM_TYPE_LEN,
                     symbol.getTypeLength());

        assertEquals("Test initial comment length", INIT_ADSSYM_COMMENT_LEN,
                     symbol.getCommentLength());

        assertEquals("Test initial name", INIT_ADSSYM_NAME, symbol.getName());

        assertEquals("Test initial type", INIT_ADSSYM_TYPE, symbol.getType());

        assertEquals("Test initial comment", INIT_ADSSYM_COMMENT,
                     symbol.getComment());
    }
}