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

package io.hanbings.cynops.lang;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * byte 工具类
 * 用于处理 byte 数据的转换 编辑
 */
@SuppressWarnings("unused")
public class ByteUtils {
    /**
     * 单个 byte 判断是否为 0
     *
     * @param octet 一个 byte
     * @return 判断结果
     */
    public static boolean isZero(byte octet) {
        return octet == 0;
    }

    /**
     * byte 数组判断是否为 0
     *
     * @param bytes byte 数组
     * @return 判断结果
     */
    public static boolean isAllZero(byte[] bytes) {
        for (byte octet : bytes) {
            if (octet != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断 byte 数组是否为 null 或是长度为 0
     *
     * @param bytes byte 数组
     * @return 判断结果
     */
    public static boolean isAllEmpty(byte[] bytes) {
        return bytes == null || bytes.length == 0;
    }

    /**
     * 将 byte 数组转换为 String
     *
     * @param bytes byte 数组
     * @return 转换结果
     */
    public static String getString(byte[] bytes) {
        if (isAllEmpty(bytes)) {
            return null;
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 将 byte 数组转换为 Hex String
     *
     * @param bytes byte 数组
     * @return 转换结果
     */
    public static String getHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte octet : bytes) {
            String hex = Integer.toHexString(octet & 0xFF);
            if (hex.length() < 2) {
                builder.append(0);
            }
            builder.append(hex);
        }
        return builder.toString();
    }

    /**
     * 裁剪 byte 数组中的一段
     *
     * @param bytes      byte 数组
     * @param beginIndex 开始裁剪处
     * @return 裁剪后的 byte 数组
     */
    public static byte[] subBytes(byte[] bytes, int beginIndex) {
        byte[] octets = new byte[bytes.length - beginIndex];
        if (bytes.length - beginIndex >= 0) {
            System.arraycopy(bytes, beginIndex, octets, 0, bytes.length - beginIndex);
        }
        return octets;
    }

    /**
     * 裁剪 byte 数组中的一段
     *
     * @param bytes      byte 数组
     * @param beginIndex 开始裁剪处
     * @param endIndex   结束裁剪处
     * @return 裁剪后的 byte 数组
     */
    public static byte[] subBytes(byte[] bytes, int beginIndex, int endIndex) {
        byte[] octets = new byte[bytes.length - (bytes.length - endIndex) - beginIndex];
        if (endIndex - beginIndex >= 0)
            System.arraycopy(bytes, beginIndex, octets, 0, endIndex - beginIndex);
        return octets;
    }
}
