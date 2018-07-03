package cn.rockingwang.notebook.util;

import cn.rockingwang.notebook.common.constant.Constants;
import cn.rockingwang.notebook.exception.BizException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.Validate;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Author: wangpeng
 * @Description:
 */
public class EncryptionUtils {

    private static SecureRandom random = new SecureRandom();

    /**
     * 根据个数生成盐值
     */
    private static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 生成盐值
     */
    public static String generateSalt() {
        byte[] salt = generateSalt(Constants.SALT8);
        return encodeHex(salt);
    }

    /**
     * Hex 编码
     */
    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }

    /**
     * Hex 解码
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new BizException(e);
        }
    }

    /**
     * Base64 编码
     */
    public static String encodeBase64(byte[] input) {
        return Base64.encodeBase64String(input);
    }

    /**
     * Base64 解码
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input);
    }

    /**
     * SHA1
     */
    public static byte[] sha1(byte[] input, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(salt);
            return digest.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new BizException(e);
        }
    }

    public static String md5(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }


}
