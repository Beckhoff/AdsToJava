package de.beckhoff.jni;

import java.io.UnsupportedEncodingException;

/**
 * This class holds byte[] conversions for the most important data types.
 *
 * @author Beckhoff Automation
 */
public class Convert {
    /**
     * Private constructor to suppress instantiation.
     */
    private Convert() {}

    /**
     * This method converts a single byte to a byte[].
     *
     * @param data
     *    the byte to be converted.
     * @return
     *    the result of the conversion of type byte[].
     */
    public static byte[] ByteToByteArr(byte data) {
        byte[] result = new byte[] {data};

        return result;
    }

    /**
     * This method converts a byte[] to a single byte.
     *
     * @param data
     *    the byte[] to be converted.
     * @return
     *    the result of the conversion of type byte.
     * @throws
     *  IllegalArgumentException - is thrown when the size of the array is not
     *  equal to the expected size.
     */
    public static byte ByteArrToByte(byte[] data) {
        int expectedSize = Byte.SIZE / 8;

        if (data.length != expectedSize) {
            throw new IllegalArgumentException(
                "The length of the array is not equal to the expected size " +
                expectedSize);
        }

        return data[0];
    }

    /**
     * This method converts a short to a byte[].
     *
     * @param data
     *    the short to be converted.
     * @return
     *    the result of the conversion of type byte[].
     */
    public static byte[] ShortToByteArr(short data) {
        byte base = (byte)0xff;

        // Shift needed bytes onto base and store into array
        byte[] result =
            new byte[] {(byte)(data & base), (byte)((data >> 8) & base)};

        return result;
    }

    /**
     * This method converts a byte[] to a short.
     *
     * @param data
     *    the byte[] to be converted.
     * @return
     *    the result of the conversion of type short.
     * @throws
     *  IllegalArgumentException - is thrown when the size of the array is not
     *  equal to the expected size.
     */
    public static short ByteArrToShort(byte[] data) {
        int expectedSize = Short.SIZE / 8;

        if (data.length != expectedSize) {
            throw new IllegalArgumentException(
                "The length of the array is not equal to the expected size " +
                expectedSize);
        }

        short base = 0xff;

        // 2K fills leading bits with 1's -> delete
        short result = (short)(data[1] & base);
        result = (short)((result << 8) | (data[0] & base));

        return result;
    }

    /**
     * This method converts an int to a byte[].
     *
     * @param data
     *    the int to be converted.
     * @return
     *    the result of the conversion of type byte[].
     */
    public static byte[] IntToByteArr(int data) {
        byte base = (byte)0xff;

        // Shift needed bytes onto base and store into array
        byte[] result = new byte[] {
            (byte)(data & base), (byte)((data >> 8) & base),
            (byte)((data >> 16) & base), (byte)((data >> 24) & base)};

        return result;
    }

    /**
     * This method converts a byte[] to an int.
     *
     * @param data
     *    the byte[] to be converted.
     * @return
     *    the result of the conversion of type int.
     * @throws
     *  IllegalArgumentException - is thrown when the size of the array is not
     *  equal to the expected size.
     */
    public static int ByteArrToInt(byte[] data) {
        int expectedSize = Integer.SIZE / 8;

        if (data.length != expectedSize) {
            throw new IllegalArgumentException(
                "The length of the array is not equal to the expected size " +
                expectedSize);
        }

        int base = 0xff;

        // 2K fills leading bits with 1's -> delete
        int result = data[3] & base;
        result = (result << 8) | (data[2] & base);
        result = (result << 8) | (data[1] & base);
        result = (result << 8) | (data[0] & base);

        return result;
    }

    /**
     * This method converts a long to a byte[].
     *
     * @param data
     *    the long to be converted.
     * @return
     *    the result of the conversion of type byte[].
     */
    public static byte[] LongToByteArr(long data) {
        byte base = (byte)0xff;

        // Shift needed bytes onto base and store into array
        byte[] result = new byte[] {
            (byte)(data & base),         (byte)((data >> 8) & base),
            (byte)((data >> 16) & base), (byte)((data >> 24) & base),
            (byte)((data >> 32) & base), (byte)((data >> 40) & base),
            (byte)((data >> 48) & base), (byte)((data >> 56) & base)};

        return result;
    }

    /**
     * This method converts a byte[] to a long.
     *
     * @param data
     *    the byte[] to be converted.
     * @return
     *    the result of the conversion of type long.
     * @throws
     *  IllegalArgumentException - is thrown when the size of the array is not
     *  equal to the expected size.
     */
    public static long ByteArrToLong(byte[] data) {
        int expectedSize = Long.SIZE / 8;

        if (data.length != expectedSize) {
            throw new IllegalArgumentException(
                "The length of the array is not equal to the expected size " +
                expectedSize);
        }

        long base = 0xff;

        // 2K fills leading bits with 1's -> delete
        long result = data[7] & base;
        result = (result << 8) | (data[6] & base);
        result = (result << 8) | (data[5] & base);
        result = (result << 8) | (data[4] & base);
        result = (result << 8) | (data[3] & base);
        result = (result << 8) | (data[2] & base);
        result = (result << 8) | (data[1] & base);
        result = (result << 8) | (data[0] & base);

        return result;
    }

    /**
     * This method converts a float to a byte[].
     *
     * @param data
     *    the float to be converted.
     * @return
     *    the result of the conversion of type byte[].
     */
    public static byte[] FloatToByteArr(float data) {
        int resultInt = Float.floatToRawIntBits(data);
        return IntToByteArr(resultInt);
    }

    /**
     * This method converts a byte[] to a float.
     *
     * @param data
     *    the byte[] to be converted.
     * @return
     *    the result of the conversion of type float.
     * @throws
     *  IllegalArgumentException - is thrown when the size of the array is not
     *  equal to the expected size.
     */
    public static float ByteArrToFloat(byte[] data) {
        int expectedSize = Float.SIZE / 8;

        if (data.length != expectedSize) {
            throw new IllegalArgumentException(
                "The length of the array is not equal to the expected size " +
                expectedSize);
        }

        int resultInt = ByteArrToInt(data);

        return Float.intBitsToFloat(resultInt);
    }

    /**
     * This method converts a double to a byte[].
     *
     * @param data
     *    the double to be converted.
     * @return
     *    the result of the conversion of type byte[].
     */
    public static byte[] DoubleToByteArr(double data) {
        long resultLong = Double.doubleToLongBits(data);
        return LongToByteArr(resultLong);
    }

    /**
     * This method converts a byte[] to a double.
     *
     * @param data
     *    the byte[] to be converted.
     * @return
     *    the result of the conversion of type double.
     * @throws
     *  IllegalArgumentException - is thrown when the size of the array is not
     *  equal to the expected size.
     */
    public static double ByteArrToDouble(byte[] data) {
        int expectedSize = Double.SIZE / 8;

        if (data.length != expectedSize) {
            throw new IllegalArgumentException(
                "The length of the array is not equal to the expected size " +
                expectedSize);
        }

        long resultLong = ByteArrToLong(data);

        return Double.longBitsToDouble(resultLong);
    }

    /**
     * This method converts a boolean to a byte[].
     *
     * @param data
     *  the boolean to be converted.
     * @return
     *  the result of the conversion of type byte[] (length of the array is one.
     * The arrays first elements value will either be 0x00 or 0x01).
     */
    public static byte[] BoolToByteArr(boolean data) {
        byte[] result;

        if (data) {
            result = new byte[] {0x01};
        } else {
            result = new byte[] {0x00};
        }

        return result;
    }

    /**
     * This method converts a byte[] to a boolean.
     *
     * @param data
     *  the byte[] to be converted.
     * @return
     *  the result of the conversion of type boolean. The return value will be
     * <code>true</code> if the arrays first elements value is 0x01. Otherwise
     * it will be <code>false</code>.
     * @throws
     *  IllegalArgumentException - is thrown when the size of the array is not
     *  equal to the expected size.
     */
    public static boolean ByteArrToBool(byte[] data) {
        int expectedSize = Byte.SIZE / 8;

        if (data.length != expectedSize) {
            throw new IllegalArgumentException(
                "The length of the array is not equal to the expected size " +
                expectedSize);
        }

        boolean result = false;

        if (data[0] == 0x01) {
            result = true;
        }

        return result;
    }

    /**
     * This method converts a char to a byte[].
     *
     * @param data
     *  the char to be converted.
     * @return
     *  the result of the conversion of type byte[].
     * @throws
     *  IllegalArgumentException - is thrown when the size of a character is not
     *  equal to the expected size.
     */
    public static byte[] CharToByteArr(char data) {
        int expectedSize = 2;
        byte[] b = new byte[expectedSize];
        b[0] = (byte)(data >>> 8);
        b[1] = (byte)data;
        return b;
    }

    /**
     * This method converts a byte[] to a char.
     *
     * @param data
     *  the byte[] to be converted
     * @return
     *  the result of the conversion of type byte[].
     * @throws
     *  IllegalArgumentException - is thrown when the size of the array is not
     *  equal to the expected size.
     */
    public static char ByteArrToChar(byte[] data) {
        int expectedSize = 2; // Character.SIZE / 8;

        if (data.length != expectedSize) {
            throw new IllegalArgumentException(
                "The length of the array is not equal to the expected size " +
                expectedSize);
        }

        return (char)((data[0] << 8) | (data[1] & 255));
    }

    /**
     * This method converts a String to a byte[] without a terminating null
     * byte.
     *
     * Since the caller cannot choose to alter the behavior described above
     * this method has been marked deprecated. For full control use
     * StringToByteArr(String, boolean) instead.
     * @param data
     *    the String to be converted.
     * @return
     *    the result of the conversion of type byte[].
     */
    @Deprecated
    public static byte[] StringToByteArr(String data) {
        return StringToByteArr(data, false);
    }

    /**
     * This method converts a String to a byte[]. Whether or not a terminating
     * zero byte is appended may be controlled by setting appendNullByte to true
     * or false.
     * This method assumes "UTF-8" encoding.
     *
     * @param data
     *    the String to be converted.
     * @param appendNullByte
     *    specifies whether a null character should be added to the end of the
     * string.
     * @return
     *    the result of the conversion of type byte[].
     */
    public static byte[] StringToByteArr(String data, boolean appendNullByte) {
        return StringToByteArr(data, appendNullByte, "UTF-8");
    }

    /**
     * This method converts a String to a byte[]. Whether or not a terminating
     * zero byte is appended may be controlled by setting appendNullByte to true
     * or false.
     *
     * @param data
     *    the String to be converted.
     * @param appendNullByte
     *    specifies whether a null character should be added to the end of the
     *    string.
     * @param expectedEncoding
     *    specified the encoding of the data, e.g. "UTF-8"
     * @return
     *    the result of the conversion of type byte[].
     */
    public static byte[] StringToByteArr(String data, boolean appendNullByte,
                                         String expectedEncoding) {
        try {
            if (appendNullByte) {
                byte[] bytes = data.getBytes(expectedEncoding);
                if (bytes.length > 0) {
                    if (bytes[bytes.length - 1] != 0) {
                        data = data + "\0";
                    }
                } else if (bytes.length == 0) {
                    data = data + "\0";
                }
            }
            return data.getBytes(expectedEncoding);
        } catch (UnsupportedEncodingException ex) {
            return new byte[] {};
        }
    }

    /**
     * This method converts the given byte[] to its String representation. Both,
     * arrays with and without a terminating null byte may be converted using
     * this method.
     *
     * @param data
     *    the byte[] to be converted.
     * @return
     *    the result of the conversion of type String.
     */
    public static String ByteArrToString(byte[] data) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; (i < data.length && (data[i] != 0)); i++) {
            builder.append((char)data[i]);
        }

        return builder.toString();
    }
}