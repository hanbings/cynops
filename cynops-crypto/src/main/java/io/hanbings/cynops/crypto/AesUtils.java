/*
 * Copyright (c) 2021 Hanbings / hanbings Cynops Toolbox.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.hanbings.cynops.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * AES 加密封装 默认采用 Java 默认的 AES/ECB/PKCS5Padding
 */
@SuppressWarnings("unused SpellCheckingInspection")
public class AesUtils {

    /**
     * 加密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 String 原始数据 String 密钥
     *
     * @param source 原始数据
     * @param key    密钥
     * @return 加密后的 String
     */
    public static String encryptToString(String source, String key) {
        return encryptToString(source.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 加密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 String 原始数据 String 密钥
     *
     * @param source 原始数据
     * @param key    密钥
     * @return 加密后的 String
     */
    public static String encryptToString(String source, byte[] key) {
        return encryptToString(source.getBytes(StandardCharsets.UTF_8), key);
    }

    /**
     * 加密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 byte 数组原始数据 String 密钥
     *
     * @param source 原始数据
     * @param key    密钥
     * @return 加密后的 String
     */
    public static String encryptToString(byte[] source, String key) {
        return encryptToString(source, key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 加密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 byte 数组原始数据 byte 数组密钥
     *
     * @param source 原始数据
     * @param key    密钥
     * @return 加密后的 String
     */
    public static String encryptToString(byte[] source, byte[] key) {
        byte[] bytes = encrypt(source, key);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte temp : Objects.requireNonNull(bytes)) {
            stringBuilder.append(String.format("%02x", temp));
        }
        return stringBuilder.toString();
    }

    /**
     * 加密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 String 原始数据 String 密钥
     *
     * @param source 原始数据
     * @param key    密钥
     * @return 加密后的 byte 数组
     */
    public static byte[] encrypt(String source, String key) {
        return encrypt(source.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 加密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 String 原始数据 byte 数组密钥
     *
     * @param source 原始数据
     * @param key    密钥
     * @return 加密后的 byte 数组
     */
    public static byte[] encrypt(String source, byte[] key) {
        return encrypt(source.getBytes(StandardCharsets.UTF_8), key);
    }

    /**
     * 加密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 byte 数组原始数据 String 密钥
     *
     * @param source 原始数据
     * @param key    密钥
     * @return 加密后的 byte 数组
     */
    public static byte[] encrypt(byte[] source, String key) {
        return encrypt(source, key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 加密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 byte 数组原始数据 byte 数组密钥
     *
     * @param source 原始数据
     * @param key    密钥
     * @return 加密后的 byte 数组
     */
    public static byte[] encrypt(byte[] source, byte[] key) {
        try {
            // 补足密码长度
            byte[] raw = new byte[16];
            byte[] bytes = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            for (int i = 0; i < 16; i++) {
                if (key.length > i) {
                    raw[i] = key[i];
                } else {
                    raw[i] = bytes[0];
                }
            }

            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return cipher.doFinal(source);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 String 加密数据 String 密钥
     *
     * @param source 解密字符串
     * @param key    密钥
     * @return 解密后的 String
     */
    public static String decryptToString(String source, String key) {
        final byte[] bytes = new byte[source.length() / 2];
        int index = 0;
        for (int count = 0; count < bytes.length; count++) {
            //因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(source.charAt(index), 16) & 0xff);
            byte low = (byte) (Character.digit(source.charAt(index + 1), 16) & 0xff);
            bytes[count] = (byte) (high << 4 | low);
            index += 2;
        }
        return new String(Objects.requireNonNull(decrypt(bytes, key.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
    }

    /**
     * 解密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 String 加密数据 byte 数组密钥
     *
     * @param source 解密字符串
     * @param key    密钥
     * @return 解密后的 String
     */
    public static String decryptToString(String source, byte[] key) {
        final byte[] bytes = new byte[source.length() / 2];
        int index = 0;
        for (int count = 0; count < bytes.length; count++) {
            //因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(source.charAt(index), 16) & 0xff);
            byte low = (byte) (Character.digit(source.charAt(index + 1), 16) & 0xff);
            bytes[count] = (byte) (high << 4 | low);
            index += 2;
        }
        return new String(Objects.requireNonNull(decrypt(bytes, key)), StandardCharsets.UTF_8);
    }

    /**
     * 解密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 byte 数组加密数据 String 密钥
     *
     * @param source 解密字符串
     * @param key    密钥
     * @return 解密后的 String
     */
    public static String decryptToString(byte[] source, String key) {
        return new String(Objects.requireNonNull(decrypt(source, key.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
    }

    /**
     * 解密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 byte 数组加密数据 byte 数组密钥
     *
     * @param source 解密字符串
     * @param key    密钥
     * @return 解密后的 String
     */
    public static String decryptToString(byte[] source, byte[] key) {
        return new String(Objects.requireNonNull(decrypt(source, key)), StandardCharsets.UTF_8);
    }

    /**
     * 解密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 String 加密数据 String 密钥
     *
     * @param source 解密字符串
     * @param key    密钥
     * @return 解密后的 byte 数组
     */
    public static byte[] decrypt(String source, String key) {
        final byte[] bytes = new byte[source.length() / 2];
        int index = 0;
        for (int count = 0; count < bytes.length; count++) {
            //因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(source.charAt(index), 16) & 0xff);
            byte low = (byte) (Character.digit(source.charAt(index + 1), 16) & 0xff);
            bytes[count] = (byte) (high << 4 | low);
            index += 2;
        }
        return decrypt(bytes, key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 String 加密数据 byte 数组密钥
     *
     * @param source 解密字符串
     * @param key    密钥
     * @return 解密后的 byte 数组
     */
    public static byte[] decrypt(String source, byte[] key) {
        final byte[] bytes = new byte[source.length() / 2];
        int index = 0;
        for (int count = 0; count < bytes.length; count++) {
            //因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(source.charAt(index), 16) & 0xff);
            byte low = (byte) (Character.digit(source.charAt(index + 1), 16) & 0xff);
            bytes[count] = (byte) (high << 4 | low);
            index += 2;
        }
        return decrypt(bytes, key);
    }

    /**
     * 解密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 byte 数组加密数据 String 密钥
     *
     * @param source 解密字符串
     * @param key    密钥
     * @return 解密后的 byte 数组
     */
    public static byte[] decrypt(byte[] source, String key) {
        return decrypt(source, key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     * 输入 byte 数组加密数据 byte 数组密钥
     *
     * @param source 解密字符串
     * @param key    密钥
     * @return 解密后的 byte 数组
     */
    public static byte[] decrypt(byte[] source, byte[] key) {
        try {
            byte[] raw = new byte[16];
            byte[] bytes = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            for (int count = 0; count < 16; count++) {
                if (key.length > count) {
                    raw[count] = key[count];
                } else {
                    raw[count] = bytes[0];
                }
            }

            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return cipher.doFinal(source);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

