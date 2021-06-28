package helper;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @program: untitled
 * @description:
 * @author: Joe
 * @create: 2020-04-22 14:50
 **/

public class AESCipherUtils {

    public static final String DEFAULT_CHARSET = "utf-8";
    private static final char[] bcdLookup = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String encryptBcdString(String plain, String key) {
        if (StringUtils.isBlank(plain)) {
            return null;
        }

        try {
            byte[] plainData = plain.getBytes(DEFAULT_CHARSET);
            byte[] keyData = AESCipherUtils.hexStrToBytes(key);
            byte[] encryptData = encrypt(plainData, keyData);
            return AESCipherUtils.bytesToHexStr(encryptData);
        } catch (Exception e) {

        }
        return null;
    }


    /**
     * 将16进制字符串还原为字节数组.
     */
    public static final byte[] hexStrToBytes(String s) {
        byte[] bytes;

        bytes = new byte[s.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;
    }

    /**
     * 将字节数组转换为16进制字符串的形式.
     */
    public static final String bytesToHexStr(byte[] bcd) {
        StringBuffer s = new StringBuffer(bcd.length * 2);

        for (int i = 0; i < bcd.length; i++) {
            s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
            s.append(bcdLookup[bcd[i] & 0x0f]);
        }

        return s.toString();
    }

    public static byte[] encrypt(byte[] plainData, byte[] key) {
        if (plainData == null) {
            return null;
        }

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return cipher.doFinal(plainData);
        } catch (Exception e) {

        }
        return null;
    }

    public static String hexToBin(String hexStr) {
        String hexToBinStr = "";
        try {
            hexToBinStr = Long.toBinaryString(Long.valueOf(hexStr, 16));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return fixLengthBins(hexToBinStr);
    }

    public static String fixLengthBins(String bins) {
        StringBuilder sb = new StringBuilder(bins);
        int len = bins.length();
        for (int i = 0; i < 8 - len; i++) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

}
