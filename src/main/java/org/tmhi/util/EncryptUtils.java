package org.tmhi.util;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Author:       Hiei
 * Date:         2018/04/04
 * Description:  字符串加密通用方法封装类
 * Modified By:
 */
public class EncryptUtils {
    
    /** 加密算法名 */
    private static final String ALGORITHM = "SHA-256";
    /** 文字编码 */
    private static final Charset charset = StandardCharsets.UTF_8;
    
    /**
     * MessageDigest加盐哈希
     * 
     * @param string 未加密字符串
     * @param salt 盐值
     * @return 加密字符串 
     */
    private static String getEncryptedString(String string, String salt) throws Exception {
        
        // 使用java.security.MessageDigest加密
        MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
        // 加盐哈希
        byte[] bytes = digest.digest((string + salt).getBytes(charset));
        // 返回密码
        return DatatypeConverter.printHexBinary(bytes);
    }

    /**
     * 用户密码对比
     *
     * @param inputPWD 用户输入的密码
     * @param basePWD 数据库中的密码
     * @param salt 用户盐值
     * @return true: 密码一致 false: 密码不一致
     */
    public static boolean equalPassword(String inputPWD, String basePWD, String salt) throws Exception {

        // 加密用户密码
        String encryptedInput = getEncryptedString(inputPWD, salt);
        // 返回比较结果
        return encryptedInput.equals(basePWD);
    }
}
