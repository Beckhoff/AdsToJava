/*
 * AmsAddr.java
 */
package de.beckhoff.jni.tcads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An AmsAddress consists of an AmsNetId and a port number.
 *
 * @author Beckhoff Automation
 */
public class AmsAddr {
    private AmsNetId mNetId;
    private int mPort;

    // SuppressFBWarnings("UUF_UNUSED_PUBLIC_OR_PROTECTED_FIELD")
    private char mNetIdPart0;
    private char mNetIdPart1;
    private char mNetIdPart2;
    private char mNetIdPart3;
    private char mNetIdPart4;
    private char mNetIdPart5;

    /**
     * Class constructor.
     */
    public AmsAddr() {
        this.mNetId = new AmsNetId();
        this.mPort = 0;
    }

    /**
     * Setter for field port.
     *
     * @param lPort
     *      the int value to be set.
     */
    public void setPort(int lPort) { this.mPort = lPort; }

    /**
     * Getter for field port.
     *
     * @return
     *      the current int value of field port.
     */
    public int getPort() { return this.mPort; }

    /**
     * Setter for field amsNetId
     *
     * @param netId
     *      the AmsNetId to be set
     */
    public void setNetId(AmsNetId netId) { this.mNetId = new AmsNetId(netId); }

    /**
     * Getter for field NetID.
     *
     * @return
     *      the current AmsNetID of field NetID.
     */
    public AmsNetId getNetId() { return new AmsNetId(this.mNetId); }

    /**
     * Set the char value of a particular part of the amsNetID.
     *
     * @param netIdPart
     *      the value to be set to the particular part of the amsNetID.
     * @param partIndex
     *      the part to write to (from 0 to 5).
     */
    public void setNetIDPart(char netIdPart, int partIndex) {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            // Make sure we are inside the bounds of the array
            if ((partIndex >= 0) &&
                (partIndex < this.mNetId.getCharArr().length)) {
                this.mNetId.setChar(netIdPart, partIndex);
            }
        } else {
            switch (partIndex) {
            case 0:
                this.mNetIdPart0 = netIdPart;
                break;
            case 1:
                this.mNetIdPart1 = netIdPart;
                break;
            case 2:
                this.mNetIdPart2 = netIdPart;
                break;
            case 3:
                this.mNetIdPart3 = netIdPart;
                break;
            case 4:
                this.mNetIdPart4 = netIdPart;
                break;
            case 5:
                this.mNetIdPart5 = netIdPart;
                break;
            default:
                break;
            }
        }
    }

    /**
     * Get the char value of a particular part of the amsNetID.
     *
     * @param partIndex
     *      the part to be returned (from 0 to 5).
     * @return
     *      the char value of the particular part. When part is not between
     *      0 and 5 the value returned will be 0. No exception will be thrown.
     */
    public char getNetIDPart(int partIndex) {
        char result = 0;

        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            // Make sure we are inside the bounds of the array
            if ((partIndex >= 0) &&
                (partIndex < this.mNetId.getCharArr().length)) {
                result = this.mNetId.getCharArr()[partIndex];
            }
        } else {
            switch (partIndex) {
            case 0:
                result = this.mNetIdPart0;
                break;
            case 1:
                result = this.mNetIdPart1;
                break;
            case 2:
                result = this.mNetIdPart2;
                break;
            case 3:
                result = this.mNetIdPart3;
                break;
            case 4:
                result = this.mNetIdPart4;
                break;
            case 5:
                result = this.mNetIdPart5;
                break;
            default:
                break;
            }
        }

        return result;
    }

    /**
     * Converts a String into an AmsNetId and sets it to the according
     * field value. <br>
     * The string is rejected and an IllegalArgumentException is
     * thrown if it occurs that the string dies not match the following pattern:
     * ^(?:" + (?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?) + "\\.){5}" +
     * (?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?) + "$"
     *
     * @param lNetId
     *      the String to be converted.
     * @throws IllegalArgumentException
     *      is thrown when the string fails to match the pattern mentioned
     *      above.
     */
    public void setNetIdStringEx(String lNetId)
        throws IllegalArgumentException {
        // Regular expression that matches any number between 0 and 255.
        String block = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        // The complete regular expression that matches AmsNetIds only.
        Pattern pattern =
            Pattern.compile("^(?:" + block + "\\.){5}" + block + "$");
        Matcher matcher = pattern.matcher(lNetId);

        if (matcher.matches()) {
            char i = 0;
            for (String s : lNetId.split("\\.")) {
                char part = (char)Short.parseShort(s);

                this.setNetIDPart(part, i);

                i++;
            }
        } else {
            throw new IllegalArgumentException("String could not be matched.");
        }
    }

    /**
     * Convert an AmsNetId into string representation.
     * @return
     *      the converted String.
     */
    public String getNetIdString() {
        StringBuilder ret = new StringBuilder();
        char[] netId;

        //        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
        //            netId = this.mNetId.getCharArr();
        //        } else {
        //            netId = new char[6];
        //            netId[0] = this.getNetIdPart0();
        //            netId[1] = this.getNetIdPart1();
        //            netId[2] = this.getNetIdPart2();
        //            netId[3] = this.getNetIdPart3();
        //            netId[4] = this.getNetIdPart4();
        //            netId[5] = this.getNetIdPart5();
        //        }

        // Make sure we are inside the bounds of the array
        for (int i = 0; (i >= 0) & (i < 6); i++) {
            ret.append((short)this.getNetIDPart(i));

            if (i != 5)
                ret.append('.');
        }

        return ret.toString();
    }

    /**
     * Convert a String into an AmsNetId and set it to the according field
     * value.
     *
     * @param lNetId
     *      the String to be converted.
     * @deprecated
     *      use setNetIdStringEx(String lNetId) instead.
     */
    @Deprecated
    public void setNetIdString(String lNetId) {
        int i = 0;

        for (String s : lNetId.split("\\.")) {
            char part = (char)Short.parseShort(s);
            this.setNetIDPart(part, i);

            i++;
        }
    }

    /**
     * @return
     *      the char value of the first part.
     * @deprecated
     *      use getNetIdPart(int part) instead.
     */
    @Deprecated
    public char getNetIdPart0() {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return this.mNetId.getCharArr()[0];
        } else {
            return this.mNetIdPart0;
        }
    }

    /**
     * @param lNetIdPart0
     *      the value to be set to the first part of the amsNetID.
     * @deprecated
     *      use setNetIdPart(char netIdPart, int partIndex) instead.
     */
    @Deprecated
    public void setNetIdPart0(char lNetIdPart0) {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            this.mNetId.setChar(lNetIdPart0, 0);
        } else {
            this.mNetIdPart0 = lNetIdPart0;
        }
    }

    /**
     * @return
     *      the char value of the second part.
     * @deprecated
     *      use getNetIdPart(int part) instead.
     */
    @Deprecated
    public char getNetIdPart1() {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return this.mNetId.getCharArr()[1];
        } else {
            return this.mNetIdPart1;
        }
    }

    /**
     * @param lNetIdPart1
     *      the value to be set to the second part of the amsNetID.
     * @deprecated
     *      use setNetIdPart(char netIdPart, int partIndex) instead.
     */
    @Deprecated
    public void setNetIdPart1(char lNetIdPart1) {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            this.mNetId.setChar(lNetIdPart1, 1);
        } else {
            this.mNetIdPart1 = lNetIdPart1;
        }
    }

    /**
     * @return
     *      the char value of the third part.
     * @deprecated
     *      use getNetIdPart(int part) instead.
     */
    @Deprecated
    public char getNetIdPart2() {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return this.mNetId.getCharArr()[2];
        } else {
            return this.mNetIdPart2;
        }
    }

    /**
     * @param lNetIdPart2
     *      the value to be set to the third part of the amsNetID.
     * @deprecated
     *      use setNetIdPart(char netIdPart, int partIndex) instead.
     */
    @Deprecated
    public void setNetIdPart2(char lNetIdPart2) {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            this.mNetId.setChar(lNetIdPart2, 2);
        } else {
            this.mNetIdPart2 = lNetIdPart2;
        }
    }

    /**
     * @return
     *      the char value of the fourth part.
     * @deprecated
     *      use getNetIdPart(int part) instead.
     */
    @Deprecated
    public char getNetIdPart3() {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return this.mNetId.getCharArr()[3];
        } else {
            return this.mNetIdPart3;
        }
    }

    /**
     * @param lNetIdPart3
     *      the value to be set to the fourth part of the amsNetID.
     * @deprecated
     *      use setNetIdPart(char netIdPart, int partIndex) instead.
     */
    @Deprecated
    public void setNetIdPart3(char lNetIdPart3) {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            this.mNetId.setChar(lNetIdPart3, 3);
        } else {
            this.mNetIdPart3 = lNetIdPart3;
        }
    }

    /**
     * @return
     *      the char value of the fifth part.
     * @deprecated
     *      use getNetIdPart(int part) instead.
     */
    @Deprecated
    public char getNetIdPart4() {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return this.mNetId.getCharArr()[4];
        } else {
            return this.mNetIdPart4;
        }
    }

    /**
     * @param lNetIdPart4
     *      the value to be set to the fifth part of the amsNetID.
     * @deprecated
     *      use setNetIdPart(char netIdPart, int partIndex) instead.
     */
    @Deprecated
    public void setNetIdPart4(char lNetIdPart4) {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            this.mNetId.setChar(lNetIdPart4, 4);
        } else {
            this.mNetIdPart4 = lNetIdPart4;
        }
    }

    /**
     * @return
     *      the char value of the sixth part.
     * @deprecated
     *      use getNetIdPart(int part) instead.
     */
    @Deprecated
    public char getNetIdPart5() {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            return this.mNetId.getCharArr()[5];
        } else {
            return this.mNetIdPart5;
        }
    }

    /**
     * @param lNetIdPart5
     *      the value to be set to the sixth part of the amsNetID.
     * @deprecated
     *      use setNetIdPart(char netIdPart, int partIndex) instead.
     */
    @Deprecated
    public void setNetIdPart5(char lNetIdPart5) {
        if (AdsCallDllFunction.jniWrapperDllVersionNot1()) {
            this.mNetId.setChar(lNetIdPart5, 5);
        } else {
            this.mNetIdPart5 = lNetIdPart5;
        }
    }
}