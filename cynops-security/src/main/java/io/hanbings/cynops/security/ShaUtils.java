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

package io.hanbings.cynops.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对数据进行 MD 算法的摘要取值 <br>
 * 因为输出数据较短 故不封装 byte[] 返回值方法
 */
@SuppressWarnings("unused")
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
     * 连续几个类型的SHA计算
     *
     * @param source 原字符串
     * @return SHA结果
     */
    public static String sha1(String source) {
        return sha(ShaType.SHA1, source);
    }

    /**
     * 连续几个类型的SHA计算
     *
     * @param source 原字符串
     * @return SHA结果
     */
    public static String sha256(String source) {
        return sha(ShaType.SHA256, source);
    }

    /**
     * 连续几个类型的SHA计算
     *
     * @param source 原字符串
     * @return SHA结果
     */
    public static String sha384(String source) {
        return sha(ShaType.SHA384, source);
    }

    /**
     * 连续几个类型的SHA计算
     *
     * @param source 原字符串
     * @return SHA结果
     */
    public static String sha512(String source) {
        return sha(ShaType.SHA512, source);
    }

    /**
     * 计算文件SHA
     *
     * @param file 文件 计算速度可能会受IO性能影响 这里缓存是1M
     * @param type SHA类型 SHA-1 SHA-256 SHA-384 SHA-512
     * @return 返回计算的SHA结果
     */
    public static String sha(ShaType type, File file) {
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
     * 连续几个类型的SHA计算
     *
     * @param file 原文件
     * @return SHA结果
     */
    public static String sha1(File file) {
        return sha(ShaType.SHA1, file);
    }

    /**
     * 连续几个类型的SHA计算
     *
     * @param file 原文件
     * @return SHA结果
     */
    public static String sha256(File file) {
        return sha(ShaType.SHA256, file);
    }

    /**
     * 连续几个类型的SHA计算
     *
     * @param file 原文件
     * @return SHA结果
     */
    public static String sha384(File file) {
        return sha(ShaType.SHA384, file);
    }

    /**
     * 连续几个类型的SHA计算
     *
     * @param file 原文件
     * @return SHA结果
     */
    public static String sha512(File file) {
        return sha(ShaType.SHA512, file);
    }

    /**
     * byte 数组SHA
     *
     * @param bytes byte 数组
     * @param type  SHA类型 SHA-1 SHA-256 SHA-384 SHA-512
     * @return 返回计算的SHA结果
     */
    public static String sha(ShaType type, byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(type.toString());
            byte[] byteArray = messageDigest.digest(bytes);
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
     * 连续几个类型的SHA计算
     *
     * @param bytes byte 数组
     * @return SHA结果
     */
    public static String sha1(byte[] bytes) {
        return sha(ShaType.SHA1, bytes);
    }

    /**
     * 连续几个类型的SHA计算
     *
     * @param bytes byte 数组
     * @return SHA结果
     */
    public static String sha256(byte[] bytes) {
        return sha(ShaType.SHA256, bytes);
    }

    /**
     * 连续几个类型的SHA计算
     *
     * @param bytes byte 数组
     * @return SHA结果
     */
    public static String sha384(byte[] bytes) {
        return sha(ShaType.SHA384, bytes);
    }

    /**
     * 连续几个类型的SHA计算
     *
     * @param bytes byte 数组
     * @return SHA结果
     */
    public static String sha512(byte[] bytes) {
        return sha(ShaType.SHA512, bytes);
    }
}

