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

import io.hanbings.cynops.security.implement.MurmurImplement;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * murmur hash 包含 1 2_32 2_64 3_32 3_128 <br>
 * 种子使用 0xa0e0ef06L 即 cynops 的 crc32 <br>
 * 0xa0e0ef06 即 2699095814 (u int)
 */
@SuppressWarnings("unused")
public class MurmurUtils {
    /**
     * Murmur1
     *
     * @param source String 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur1(String source) {
        return Long.toHexString(MurmurImplement.murmur1(source.getBytes(StandardCharsets.UTF_8)
                , source.getBytes(StandardCharsets.UTF_8).length, 0xa0e0ef06L));
    }

    /**
     * Murmur1
     *
     * @param file file 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur1(File file) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
            return Long.toHexString(MurmurImplement.murmur1(bytes, bytes.length, 0xa0e0ef06L));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Murmur1
     *
     * @param bytes byte[] 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur1(byte[] bytes) {
        return Long.toHexString(MurmurImplement.murmur1(bytes, bytes.length, 0xa0e0ef06L));
    }

    /**
     * Murmur2 32位
     *
     * @param source String 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur2With32(String source) {
        return Long.toHexString(MurmurImplement.murmur2With32(source.getBytes(StandardCharsets.UTF_8)
                , source.getBytes(StandardCharsets.UTF_8).length, 0xa0e0ef06L));
    }

    /**
     * Murmur2 32位
     *
     * @param file file 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur2With32(File file) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
            return Long.toHexString(MurmurImplement.murmur2With32(bytes, bytes.length, 0xa0e0ef06L));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Murmur2 32位
     *
     * @param bytes byte[] 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur2With32(byte[] bytes) {
        return Long.toHexString(MurmurImplement.murmur2With32(bytes, bytes.length, 0xa0e0ef06L));
    }

    /**
     * Murmur2 64位
     *
     * @param source String 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur2(String source) {
        return Long.toHexString(MurmurImplement.murmur2With64(source.getBytes(StandardCharsets.UTF_8)
                , source.getBytes(StandardCharsets.UTF_8).length, 0xa0e0ef06L));
    }

    /**
     * Murmur2 64位
     *
     * @param file file 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur2(File file) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
            return Long.toHexString(MurmurImplement.murmur2With64(bytes, bytes.length, 0xa0e0ef06L));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Murmur2 64位
     *
     * @param bytes byte[] 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur2(byte[] bytes) {
        return Long.toHexString(MurmurImplement.murmur2With64(bytes, bytes.length, 0xa0e0ef06L));
    }

    /**
     * Murmur3 32位
     *
     * @param source String 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur3With32(String source) {
        return Long.toHexString(MurmurImplement.murmur3With32(source.getBytes(StandardCharsets.UTF_8)
                , source.getBytes(StandardCharsets.UTF_8).length, 0xa0e0ef06L));
    }

    /**
     * Murmur3 32位
     *
     * @param file file 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur3With32(File file) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
            return Long.toHexString(MurmurImplement.murmur3With32(bytes, bytes.length, 0xa0e0ef06L));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Murmur3 32位
     *
     * @param bytes byte[] 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur3With32(byte[] bytes) {
        return Long.toHexString(MurmurImplement.murmur3With32(bytes, bytes.length, 0xa0e0ef06L));
    }

    /**
     * Murmur3 128位
     *
     * @param source String 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur3(String source) {
        long[] longs = MurmurImplement.murmur3With128(source.getBytes(StandardCharsets.UTF_8)
                , source.getBytes(StandardCharsets.UTF_8).length, 0xa0e0ef06L);
        return Long.toHexString(longs[0]) + Long.toHexString(longs[1]);
    }

    /**
     * Murmur3 128位
     *
     * @param file file 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur3(File file) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
            long[] longs = MurmurImplement.murmur3With128(bytes, bytes.length, 0xa0e0ef06L);
            return Long.toHexString(longs[0]) + Long.toHexString(longs[1]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Murmur3 128位
     *
     * @param bytes byte[] 数据源
     * @return 计算结果 String Hex
     */
    public static String murmur3(byte[] bytes) {
        long[] longs = MurmurImplement.murmur3With128(bytes, bytes.length, 0xa0e0ef06L);
        return Long.toHexString(longs[0]) + Long.toHexString(longs[1]);
    }
}
