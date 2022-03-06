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

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对数据进行 MD 算法的摘要取值 <br>
 * 因为输出数据较短 故不封装 byte[] 返回值方法 <br>
 * 带有 key 参数的方法为 HMAC 的 MD 实现
 */
@SuppressWarnings("unused SpellCheckingInspection")
public class MdUtils {
    /**
     * 简单计算字符串MD值
     *
     * @param type   MD 算法类型
     * @param source 原字符串
     * @return MD5结果
     */
    private static String md(MdType type, String source) {
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
    public static String md5(String source) {
        return md(MdType.MD5, source);
    }

    /**
     * 从一个文件计算MD值
     *
     * @param type MD 算法类型
     * @param file 原文件
     * @return 文件计算得出的MD5
     */
    private static String md(MdType type, File file) {
        if (file == null || file.length() == 0) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(type.toString());
            FileInputStream fileInputStream = new FileInputStream(file);
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
     * @param file 原文件
     * @return MD结果
     */
    public static String md2(File file) {
        return md(MdType.MD2, file);
    }

    /**
     * 连续几个类型的MD计算
     *
     * @param file 原文件
     * @return MD结果
     */
    public static String md5(File file) {
        return md(MdType.MD5, file);
    }

    /**
     * 从一个 byte 数组计算MD值
     *
     * @param type  MD 算法类型
     * @param bytes 数组
     * @return 文件计算得出的MD5
     */
    private static String md(MdType type, byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(type.toString());
            messageDigest.update(bytes);
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
     * @param bytes byte 数组
     * @return MD结果
     */
    public static String md2(byte[] bytes) {
        return md(MdType.MD2, bytes);
    }

    /**
     * 连续几个类型的MD计算
     *
     * @param bytes byte 数组
     * @return MD结果
     */
    public static String md5(byte[] bytes) {
        return md(MdType.MD5, bytes);
    }

    /**
     * HmacMD5 计算
     *
     * @param source 数据源
     * @param key    密钥
     * @return String 计算结果
     */
    public static String md5(String source, String key) {
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacMD5");
            mac.init(keySpec);
            byte[] byteArray = mac.doFinal(source.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : byteArray) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * HmacMD5 计算
     *
     * @param file 数据源
     * @param key  密钥
     * @return String 计算结果
     */
    public static String md5(File file, String key) {
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacMD5");
            mac.init(keySpec);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                mac.update(buffer, 0, len);
            }
            fileInputStream.close();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : mac.doFinal()) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * HmacMD5 计算
     *
     * @param bytes 数据源
     * @param key   密钥
     * @return String 计算结果
     */
    public static String md5(byte[] bytes, String key) {
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacMD5");
            mac.init(keySpec);
            byte[] byteArray = mac.doFinal(bytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : byteArray) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * HmacMD5 计算
     *
     * @param source 数据源
     * @param key    密钥
     * @return String 计算结果
     */
    public static String md5(String source, byte[] key) {
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            SecretKeySpec keySpec = new SecretKeySpec(key, "HmacMD5");
            mac.init(keySpec);
            byte[] byteArray = mac.doFinal(source.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : byteArray) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * HmacMD5 计算
     *
     * @param file 数据源
     * @param key  密钥
     * @return String 计算结果
     */
    public static String md5(File file, byte[] key) {
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            SecretKeySpec keySpec = new SecretKeySpec(key, "HmacMD5");
            mac.init(keySpec);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                mac.update(buffer, 0, len);
            }
            fileInputStream.close();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : mac.doFinal()) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * HmacMD5 计算
     *
     * @param bytes 数据源
     * @param key   密钥
     * @return String 计算结果
     */
    public static String md5(byte[] bytes, byte[] key) {
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            SecretKeySpec keySpec = new SecretKeySpec(key, "HmacMD5");
            mac.init(keySpec);
            byte[] byteArray = mac.doFinal(bytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : byteArray) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // MD2 MD4 MD5
    private enum MdType {
        MD2 {
            @Override
            public String toString() {
                return "md2";
            }
        },
        MD5 {
            @Override
            public String toString() {
                return "md5";
            }
        }
    }

}

