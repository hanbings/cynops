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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * 用于编码解码 Base64 的类 <br>
 * 提供一个 BaseType 选择一些 Base64 变体 <br>
 * 此外 也可以使用参数中不含 BaseType 的封装方法 <br>
 * 此类中有部分方法会导致内存溢出 具体方法注释中会特殊标明
 */
@SuppressWarnings("unused")
public class Base64Utils {
    public enum BaseType {
        BASIC,
        MIME,
        URL
    }

    // 封装 去除 BaseType 参数

    // BASIC BASE64

    /**
     * 编码
     *
     * @param source 源
     * @return 编码结果字符串
     */
    public static String encode(String source) {
        return encode(BaseType.BASIC, source);
    }

    /**
     * 解码
     *
     * @param source 源
     * @return 解码结果字符串
     */
    public static String decode(String source) {
        return decode(BaseType.BASIC, source);
    }

    /**
     * 编码 从文件中读取源文件 此方法可能导致内存溢出
     *
     * @param source 源
     * @return 编码结果字符串
     */
    public static String encode(File source) {
        return encode(BaseType.BASIC, source);
    }

    /**
     * 编码 从文件读取源 结果写入文件
     *
     * @param source 源
     * @param target 目标文件
     */
    public static void encode(File source, File target) {
        encode(BaseType.BASIC, source, target);
    }

    /**
     * 解码 输入字符串 结果输出到文件
     *
     * @param source 源
     * @param target 目标文件
     */
    public static void decode(String source, File target) {
        decode(BaseType.BASIC, source, target);
    }

    /**
     * 解码 从文件中读取 结果输出到文件
     *
     * @param source 源
     * @param target 目标文件
     */
    public static void decode(File source, File target) {
        decode(BaseType.BASIC, source, target);
    }

    // MIME BASE64

    /**
     * 编码 使用MIME变体
     *
     * @param source 源
     * @return 编码结果字符串
     */
    public static String encodeWithMime(String source) {
        return encode(BaseType.MIME, source);
    }

    /**
     * 解码 使用MIME变体
     *
     * @param source 源
     * @return 解码结果字符串
     */
    public static String decodeWithMime(String source) {
        return decode(BaseType.MIME, source);
    }

    /**
     * 编码 从文件中读取源文件 此方法可能导致内存溢出 使用MIME变体
     *
     * @param source 源
     * @return 编码结果字符串
     */
    public static String encodeWithMime(File source) {
        return encode(BaseType.MIME, source);
    }

    /**
     * 编码 从文件读取源 结果写入文件 使用MIME变体
     *
     * @param source 源
     * @param target 目标文件
     */
    public static void encodeWithMime(File source, File target) {
        encode(BaseType.MIME, source, target);
    }

    /**
     * 解码 输入字符串 结果输出到文件 使用MIME变体
     *
     * @param source 源
     * @param target 目标文件
     */
    public static void decodeWithMime(String source, File target) {
        decode(BaseType.MIME, source, target);
    }

    /**
     * 解码 从文件中读取 结果输出到文件 使用MIME变体
     *
     * @param source 源
     * @param target 目标文件
     */
    public static void decodeWithMime(File source, File target) {
        decode(BaseType.MIME, source, target);
    }

    // URL BASE64

    /**
     * 编码 使用URL变体
     *
     * @param source 源
     * @return 编码结果字符串
     */
    public static String encodeWithUrl(String source) {
        return encode(BaseType.URL, source);
    }

    /**
     * 解码 使用URL变体
     *
     * @param source 源
     * @return 解码结果字符串
     */
    public static String decodeWithUrl(String source) {
        return decode(BaseType.URL, source);
    }

    /**
     * 编码 从文件中读取源文件 此方法可能导致内存溢出 使用URL变体
     *
     * @param source 源
     * @return 编码结果字符串
     */
    public static String encodeWithUrl(File source) {
        return encode(BaseType.URL, source);
    }

    /**
     * 编码 从文件读取源 结果写入文件 使用URL变体
     *
     * @param source 源
     * @param target 目标文件
     */
    public static void encodeWithUrl(File source, File target) {
        encode(BaseType.URL, source, target);
    }

    /**
     * 解码 输入字符串 结果输出到文件 使用URL变体
     *
     * @param source 源
     * @param target 目标文件
     */
    public static void decodeWithUrl(String source, File target) {
        decode(BaseType.URL, source, target);
    }

    /**
     * 解码 从文件中读取 结果输出到文件 使用URL变体
     *
     * @param source 源
     * @param target 目标文件
     */
    public static void decodeWithUrl(File source, File target) {
        decode(BaseType.URL, source, target);
    }

    /**
     * 编码 完整参数
     *
     * @param type   编码类型
     * @param source 源
     * @return 字符串返回 Base64
     */
    public static String encode(BaseType type, String source) {
        Base64.Encoder encoder = checkEncoder(type);
        return encoder.encodeToString(source.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解码 完整参数
     *
     * @param type   编码类型
     * @param source 源
     * @return 字符串返回 解码后字符串
     */
    public static String decode(BaseType type, String source) {
        Base64.Decoder decoder = checkDecoder(type);
        return new String(decoder.decode(source.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 编码 从一个文件中读取源 返回字符串 <br>
     * 特殊说明： 有区别于 encode(BaseType type, File source, File target) <br>
     * 因为此方法在编码特大文件时可能会导致内存溢出 此方法在发生错误的情况下会返回 null
     *
     * @param type   编码类型
     * @param source 源
     * @return 字符串返回 Base64
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static String encode(BaseType type, File source) {
        Base64.Encoder encoder = checkEncoder(type);
        InputStream input;
        try {
            input = new FileInputStream(source);
            byte[] bytes = new byte[(int) source.length()];
            input.read(bytes);
            input.close();
            return encoder.encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 编码 从一个文件中读取源 编码后写入到另一个文件中 <br>
     * 分段读取分段写入 无需担心内存溢出
     *
     * @param type   编码类型
     * @param source 源
     * @param target 目标文件
     */
    public static void encode(BaseType type, File source, File target) {
        Base64.Encoder encoder = checkEncoder(type);
        try {
            InputStream input = new FileInputStream(source);
            // 这个是用来追加写入的
            RandomAccessFile output = new RandomAccessFile(target, "rw");
            // 好像我看见很多地方都用的 3 * 1024 ＊ 1024
            // 那就这样吧 如果知道为什么和我说一说 qwq
            byte[] temp = new byte[3 * 1024 * 1024];
            byte[] base64;
            int count;
            // 读源文件 并加载到缓冲区
            while ((count = input.read(temp)) != -1) {
                if (count != temp.length) {
                    base64 = encoder.encode(Arrays.copyOf(temp, count));
                } else {
                    base64 = encoder.encode(temp);
                }
                // 写入文件
                output.seek(target.length());
                output.write(base64);
            }
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解码 输入一个字符串 Base64 编码后输出到一个文件中 <br>
     * 别给一个空文件进来 要不报错可要糊你一脸咯～
     *
     * @param type   编码类型
     * @param source 源
     * @param target 目标文件
     */
    public static void decode(BaseType type, String source, File target) {
        Base64.Decoder decoder = checkDecoder(type);
        byte[] bytes = decoder.decode(source);
        try {
            OutputStream output = new FileOutputStream(target);
            output.write(bytes);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解码 从一个文件中读取 Base64 输出到另一个文件中 <br>
     * 分段读取分段写入 无需担心内存溢出
     *
     * @param type   编码类型
     * @param source 源
     * @param target 目标文件
     */
    public static void decode(BaseType type, File source, File target) {
        Base64.Decoder decoder = checkDecoder(type);
        try {
            InputStream input = new FileInputStream(source);
            // 这个是用来追加写入的
            RandomAccessFile output = new RandomAccessFile(target, "rw");
            // 好像我看见很多地方都用的 3 * 1024 ＊ 1024
            // 那就这样吧 如果知道为什么和我说一说 qwq
            byte[] temp = new byte[3 * 1024 * 1024];
            byte[] bytes;
            int count;
            // 读源文件 并加载到缓冲区
            while ((count = input.read(temp)) != -1) {
                if (count != temp.length) {
                    bytes = decoder.decode(Arrays.copyOf(temp, count));
                } else {
                    bytes = decoder.decode(temp);
                }
                // 写入文件
                output.seek(target.length());
                output.write(bytes);
            }
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对应类型的编码器
     *
     * @param type 编码类型
     * @return Base64.Encoder
     */
    private static Base64.Encoder checkEncoder(BaseType type) {
        if (type == BaseType.BASIC) {
            return Base64.getEncoder();
        }
        if (type == BaseType.MIME) {
            return Base64.getMimeEncoder();
        }
        if (type == BaseType.URL) {
            return Base64.getUrlEncoder();
        }
        return Base64.getEncoder();
    }

    /**
     * 获取对应类型解码器
     *
     * @param type 编码类型
     * @return Base64.Decoder
     */
    private static Base64.Decoder checkDecoder(BaseType type) {
        if (type == BaseType.BASIC) {
            return Base64.getDecoder();
        }
        if (type == BaseType.MIME) {
            return Base64.getMimeDecoder();
        }
        if (type == BaseType.URL) {
            return Base64.getUrlDecoder();
        }
        return Base64.getDecoder();
    }

    // 新增几个方法

    /**
     * 编码 完整参数
     *
     * @param type   编码类型
     * @param source 源
     * @return 字符串返回 Base64
     */
    public static String encode(BaseType type, byte[] source) {
        Base64.Encoder encoder = checkEncoder(type);
        return encoder.encodeToString(source);
    }

    /**
     * 解码 完整参数
     *
     * @param type   编码类型
     * @param source 源
     * @return 字符串返回 解码后字符串
     */
    public static String decode(BaseType type, byte[] source) {
        Base64.Decoder decoder = checkDecoder(type);
        return new String(decoder.decode(source));
    }

    /**
     * 编码
     *
     * @param source 源
     * @return 字符串返回 Base64
     */
    public static String encode(byte[] source) {
        return encode(BaseType.BASIC, source);
    }

    /**
     * 编码
     *
     * @param source 源
     * @return 字符串返回 Base64
     */
    public static String encodeWithUrl(byte[] source) {
        return encode(BaseType.URL, source);
    }

    /**
     * 编码
     *
     * @param source 源
     * @return 字符串返回 Base64
     */
    public static String encodeWithMime(byte[] source) {
        return encode(BaseType.MIME, source);
    }

    /**
     * 解码
     *
     * @param source 源
     * @return 字符串返回 解码后字符串
     */
    public static String decode(byte[] source) {
        return decode(BaseType.BASIC, source);
    }

    /**
     * 解码
     *
     * @param source 源
     * @return 字符串返回 解码后字符串
     */
    public static String decodeWithUrl(byte[] source) {
        return decode(BaseType.URL, source);
    }

    /**
     * 解码
     *
     * @param source 源
     * @return 字符串返回 解码后字符串
     */
    public static String decodeWithMime(byte[] source) {
        return decode(BaseType.MIME, source);
    }
}
