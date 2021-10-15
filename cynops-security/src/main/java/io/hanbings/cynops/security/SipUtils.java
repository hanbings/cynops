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

import io.hanbings.cynops.security.implement.SipImplement;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Sip Hash <br>
 * 初始化密钥默认使用 0xa0e0ef06L 即 cynops 的 crc32 <br>
 * 0xa0e0ef06 即 2699095814 (u int) <br>
 * 种子与 Hmac 的 hash 算法的 key 类似 其中一个用途是抵御哈希洪水攻击 <br>
 * 具体信息可参考 https://www.zhihu.com/question/286529973
 */
@SuppressWarnings("unused SpellCheckingInspection")
public class SipUtils {
    /**
     * Siphash
     *
     * @param source String 数据源
     * @return 计算结果 String Hex
     */
    public static String siphash(String source) {
        return Long.toHexString(SipImplement.siphash24(source.getBytes(StandardCharsets.UTF_8)
                , 0xa0e0ef06L, 0xa0e0ef06L));
    }

    /**
     * Siphash
     *
     * @param file file 数据源
     * @return 计算结果 String Hex
     */
    public static String siphash(File file) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
            return Long.toHexString(SipImplement.siphash24(bytes, 0xa0e0ef06L, 0xa0e0ef06L));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Siphash
     *
     * @param bytes byte[] 数据源
     * @return 计算结果 String Hex
     */
    public static String siphash(byte[] bytes) {
        return Long.toHexString(SipImplement.siphash24(bytes, 0xa0e0ef06L, 0xa0e0ef06L));
    }

    /**
     * Siphash
     *
     * @param source    String 数据源
     * @param firstKey  初始化密钥 A
     * @param secondKey 初始化密钥 B
     * @return 计算结果 String Hex
     */
    public static String siphash(String source, long firstKey, long secondKey) {
        return Long.toHexString(SipImplement.siphash24(source.getBytes(StandardCharsets.UTF_8)
                , firstKey, secondKey));
    }

    /**
     * Siphash
     *
     * @param file      file 数据源
     * @param firstKey  初始化密钥 A
     * @param secondKey 初始化密钥 B
     * @return 计算结果 String Hex
     */
    public static String siphash(File file, long firstKey, long secondKey) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
            return Long.toHexString(SipImplement.siphash24(bytes, firstKey, secondKey));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Siphash
     *
     * @param bytes     byte[] 数据源
     * @param firstKey  初始化密钥 A
     * @param secondKey 初始化密钥 B
     * @return 计算结果 String Hex
     */
    public static String siphash(byte[] bytes, long firstKey, long secondKey) {
        return Long.toHexString(SipImplement.siphash24(bytes, firstKey, secondKey));
    }
}
