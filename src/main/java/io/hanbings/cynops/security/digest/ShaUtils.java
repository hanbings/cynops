package io.hanbings.cynops.security.digest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaUtils {
    // SHA-1 SHA-256 SHA-384 SHA-512
    public enum ShaType {
        SHA1 {
            @Override
            public String toString() {
                return "SHA-1";
            }
        },
        SHA256 {
            @Override
            public String toString() {
                return "SHA-256";
            }
        },
        SHA384 {
            @Override
            public String toString() {
                return "SHA-384";
            }
        },
        SHA512 {
            @Override
            public String toString() {
                return "SHA-512";
            }
        }
    }

    /**
     * 计算字符串SHA
     *
     * @param source 字符串
     * @param type   SHA类型 SHA-1 SHA-256 SHA-384 SHA-512
     * @return 返回计算的SHA结果
     */
    public static String sha(ShaType type, String source) {
        if (source == null || source.length() == 0) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(type.toString());
            byte[] byteArray = messageDigest.digest(source.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : byteArray) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算文件SHA
     *
     * @param source 文件 计算速度可能会受IO性能影响 这里缓存是1M
     * @param type   SHA类型 SHA-1 SHA-256 SHA-384 SHA-512
     * @return 返回计算的SHA结果
     */
    public static String sha(ShaType type, File source) {
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
}
