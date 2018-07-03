package cn.rockingwang.notebook.util;

import cn.rockingwang.notebook.common.constant.CharsetConstant;
import org.apache.commons.lang3.Validate;

import java.util.Arrays;

/**
 * @Author: wangpeng
 * @Description:
 */
public class PasswordUtils {

    /**
     * encrypt password
     *
     * @param password User input raw password
     * @param salt     Hex salt
     * @return Hex encrypted password
     */
    public static String encryptPassword(String password, String salt) {
        byte[] saltBytes = EncryptionUtils.decodeHex(salt);
        byte[] passwordBytes = EncryptionUtils.sha1(password.getBytes(CharsetConstant.UTF8), saltBytes);
        return EncryptionUtils.encodeHex(passwordBytes);
    }

    /**
     * check user's password
     *
     * @param src    input password
     * @param target Hex user password
     * @param salt   Hex salt
     * @return input password equals user's password
     */
    public static boolean checkPassword(String src, String target, String salt) {
        if (target == null || salt == null) {
            return false;
        }
        byte[] saltBytes = EncryptionUtils.decodeHex(salt);
        byte[] passwordBytes = EncryptionUtils.sha1(src.getBytes(CharsetConstant.UTF8), saltBytes);
        String password = EncryptionUtils.encodeHex(passwordBytes);
        return password.equals(target);
    }

}
