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

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 这个工具类的 Base58 是标准实现而非比特币实现 好吧 加上了 BTC 地址标准的实现 但不提供异形参数返回值方法了<br>
 * 文件作为输入源时可能会造成溢出 如果有需要 将对此进行修复 <br>
 * //TODO: 文件过大导致内存溢出问题
 * 有关比特币地址生成详细: https://www.jianshu.com/p/a2ea3b44f6eb
 */
@SuppressWarnings("unused SpellCheckingInspection")
public class Base58Utils {
    static final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
    static final char ENCODED_ZERO = ALPHABET[0];
    static final int[] INDEXES = new int[128];
    static final BigInteger BASE = BigInteger.valueOf(58);

    static {
        Arrays.fill(INDEXES, -1);
        for (int count = 0; count < ALPHABET.length; count++) {
            INDEXES[ALPHABET[count]] = count;
        }
    }

    /**
     * 编码
     *
     * @param bytes byte[] 输入
     * @return 编码后的字符串
     */
    public String encode(byte[] bytes) {
        char[] buffer = new char[bytes.length * 2];
        int count = buffer.length - 1;
        BigInteger temp = new BigInteger(bytes);
        while (!temp.equals(BigInteger.ZERO)) {
            BigInteger[] divAndRem = temp.divideAndRemainder(BASE);
            buffer[count] = ALPHABET[divAndRem[1].intValue()];
            temp = divAndRem[0];
            count--;
        }
        for (int zeroI = 0; bytes[zeroI] == 0; zeroI++) {
            buffer[count] = ALPHABET[0];
            count--;
        }
        return new String(buffer, count + 1, buffer.length - count - 1);
    }

    /**
     * 解码
     *
     * @param source 输入
     * @return 解码后的 byte[]
     */
    public byte[] decode(String source) {
        BigInteger temp = BigInteger.ONE;
        BigInteger result = BigInteger.ZERO;

        for (int count = source.length() - 1; count >= 0; count--) {
            char include = source.charAt(count);
            if (include >= 128 || INDEXES[include] == -1) {
                throw new IllegalArgumentException("wrong character " + include + " at position " + count);
            }
            int index = INDEXES[include];
            result = result.add(temp.multiply(BigInteger.valueOf(index)));
            temp = temp.multiply(BASE);
        }
        return result.toByteArray();
    }

    /**
     * 编码 符合 BTC 地址标准的实现
     *
     * @param bytes byte[] 输入
     * @return 编码后的字符串
     */
    public static String encodeBtcAddress(byte[] bytes) {
        if (bytes.length == 0) {
            return "";
        }
        int zeros = 0;
        while (zeros < bytes.length && bytes[zeros] == 0) {
            ++zeros;
        }
        bytes = Arrays.copyOf(bytes, bytes.length);
        char[] encoded = new char[bytes.length * 2];
        int end = encoded.length;
        for (int start = zeros; start < bytes.length; ) {
            int remainder = 0;
            for (int count = start; count < bytes.length; count++) {
                int digit = (int) bytes[count] & 0xFF;
                int temp = remainder * 256 + digit;
                bytes[count] = (byte) (temp / 58);
                remainder = temp % 58;
            }

            encoded[--end] = ALPHABET[remainder];
            if (bytes[start] == 0) {
                ++start;
            }
        }
        while (end < encoded.length && encoded[end] == ENCODED_ZERO) {
            ++end;
        }
        while (--zeros >= 0) {
            encoded[--end] = ENCODED_ZERO;
        }
        return new String(encoded, end, encoded.length - end);
    }

    /**
     * 解码 符合 BTC 地址标准的实现
     *
     * @param source 输入
     * @return 解码后的 byte[]
     */
    public static byte[] decodeBtcAddress(String source) {
        if (source.length() == 0) {
            return new byte[0];
        }
        byte[] input = new byte[source.length()];
        for (int count = 0; count < source.length(); ++count) {
            char include = source.charAt(count);
            int digit = include < 128 ? INDEXES[include] : -1;
            if (digit < 0) {
                throw new IllegalArgumentException("wrong character " + include);
            }
            input[count] = (byte) digit;
        }
        int zeros = 0;
        while (zeros < input.length && input[zeros] == 0) {
            ++zeros;
        }
        byte[] decoded = new byte[source.length()];
        int end = decoded.length;
        for (int start = zeros; start < input.length; ) {
            int remainder = 0;
            for (int count = start; count < input.length; count++) {
                int digit = (int) input[count] & 0xFF;
                int temp = remainder * 58 + digit;
                input[count] = (byte) (temp / 256);
                remainder = temp % 256;
            }
            decoded[--end] = (byte) remainder;

            if (input[start] == 0) {
                ++start;
            }
        }
        while (end < decoded.length && decoded[end] == 0) {
            ++end;
        }
        return Arrays.copyOfRange(decoded, end - zeros, decoded.length);
    }
}
