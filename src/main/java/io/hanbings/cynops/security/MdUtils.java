package io.hanbings.cynops.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("unused")
public class MdUtils {
    // MD2 MD4 MD5
    public enum MdType {
        MD2 {
            @Override
            public String toString() {
                return "md2";
            }
        },
        MD4 {
            @Override
            public String toString() {
                return "md4";
            }
        },
        MD5 {
            @Override
            public String toString() {
                return "md5";
            }
        }
    }

    /**
     * 简单计算字符串MD值
     *
     * @param source 原字符串
     * @return MD5结果
     */
    public static String md(MdType type, String source) {
        if (source == null || source.length() == 0) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(type.toString());
            messageDigest.update(source.getBytes());
            byte[] byteArray = messageDigest.digest();
            char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            char[] charArray = new char[byteArray.length * 2];
            int index = 0;
            for (byte temp : byteArray) {
                charArray[index++] = hexDigits[temp >>> 4 & 0xf];
                charArray[index++] = hexDigits[temp & 0xf];
            }
            return new String(charArray);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 连续几个类型的MD计算
     *
     * @param source 原字符串
     * @return MD结果
     */
    public static String md2(String source) {
        return md(MdType.MD2, source);
    }

    /**
     * 连续几个类型的MD计算
     *
     * @param source 原字符串
     * @return MD结果
     */
    public static String md4(String source) {
        return md(MdType.MD4, source);
    }

    /**
     * 连续几个类型的MD计算
     *
     * @param source 原字符串
     * @return MD结果
     */
    public static String md5(String source) {
        return md(MdType.MD5, source);
    }

    /**
     * 从一个文件计算MD值
     *
     * @param source 原文件
     * @return 文件计算得出的MD5
     */
    public static String md(MdType type, File source) {
        if (source == null || source.length() == 0) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(type.toString());
            FileInputStream fileInputStream = new FileInputStream(source);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, len);
            }
            fileInputStream.close();
            byte[] byteArray = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : byteArray) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 连续几个类型的MD计算
     *
     * @param source 原文件
     * @return MD结果
     */
    public static String md2(File source) {
        return md(MdType.MD2, source);
    }

    /**
     * 连续几个类型的MD计算
     *
     * @param source 原文件
     * @return MD结果
     */
    public static String md4(File source) {
        return md(MdType.MD4, source);
    }

    /**
     * 连续几个类型的MD计算
     *
     * @param source 原文件
     * @return MD结果
     */
    public static String md5(File source) {
        return md(MdType.MD5, source);
    }
}
