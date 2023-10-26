package cn.study.utils;

import java.security.MessageDigest;

/**
 * @author zhangcc
 * @date 2023-10-26
 *
 * SHA2--256加密
 */
public class SHA256 {
    private static final String SHA_256_ALGORITHM = "SHA-256";
    public static String encrypt(String data) throws Exception {
        //获取SHA-256算法实例
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_256_ALGORITHM);
        //计算散列值
        byte[] digest = messageDigest.digest(data.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转换为15进制字符串
        for (byte b : digest) {
            stringBuilder.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return stringBuilder.toString();
    }

//    public static void main(String[] args) throws Exception {
//        String data = "123456";
//        String encryptedData = encrypt(data);
//        System.out.println("加密后的数据：" + encryptedData);
//    }
}
