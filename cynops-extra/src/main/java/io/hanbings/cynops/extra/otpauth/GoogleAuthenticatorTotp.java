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
package io.hanbings.cynops.extra.otpauth;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Locale;

/**
 * Google Authenticator 谷歌验证器基于时间的二步认证的验证和密钥生成 <br>
 * 谷歌验证器要求密钥需要为 Base32 且 url 中包含密钥
 */
@SuppressWarnings("unused SpellCheckingInspection")
public class GoogleAuthenticatorTotp {
    private static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7'};
    private static final byte[] DECODE_TABLE;

    static {
        DECODE_TABLE = new byte[128];
        Arrays.fill(DECODE_TABLE, (byte) 0xFF);
        for (int i = 0; i < ALPHABET.length; i++) {
            DECODE_TABLE[(int) ALPHABET[i]] = (byte) i;
            if (i < 24) {
                DECODE_TABLE[(int) Character.toLowerCase(ALPHABET[i])] = (byte) i;
            }
        }
    }

    /**
     * 编码 Base32
     *
     * @param source 原始字符
     * @return Base 编码字符
     */
    private static String encode(byte[] source) {
        char[] chars = new char[((source.length * 8) / 5) + ((source.length % 5) != 0 ? 1 : 0)];
        for (int count = 0, handle = 0, index = 0; count < chars.length; count++) {
            if (index > 3) {
                int token = source[handle] & (0xFF >> index);
                index = (index + 5) % 8;
                token <<= index;
                if (handle < source.length - 1) {
                    token |= (source[handle + 1] & 0xFF) >> (8 - index);
                }
                chars[count] = ALPHABET[token];
                handle++;
            } else {
                chars[count] = ALPHABET[((source[handle] >> (8 - (index + 5))) & 0x1F)];
                index = (index + 5) % 8;
                if (index == 0) {
                    handle++;
                }
            }
        }
        return new String(chars);
    }

    /**
     * Base32 解码
     *
     * @param source Base32 编码字符串
     * @return 原始字符
     */
    private static byte[] decode(String source) {
        char[] stringData = source.toCharArray();
        byte[] data = new byte[(stringData.length * 5) / 8];
        for (int count = 0, handle = 0, index = 0; count < stringData.length; count++) {
            int token = DECODE_TABLE[stringData[count]];
            if (index <= 3) {
                index = (index + 5) % 8;
                if (index == 0) {
                    data[handle++] |= token;
                } else {
                    data[handle] |= token << (8 - index);
                }
            } else {
                index = (index + 5) % 8;
                data[handle++] |= (token >> index);
                if (handle < data.length) {
                    data[handle] |= token << (8 - index);
                }
            }
        }
        return data;
    }

    /**
     * 生成符合要求的密钥
     *
     * @return 密钥
     */
    public static String secret() {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            return encode(random.generateSeed(10)).toUpperCase(Locale.ROOT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前验证码
     *
     * @param secret 密钥
     * @return 验证码
     */
    public static String code(String secret) {
        return Totp.totp(
                decode(secret),
                "HmacSHA1"
                , 30
                , 0);
    }

    /**
     * 验证验证码
     *
     * @param secret 密钥
     * @param code   验证码
     * @return boolean 结果
     */
    public static boolean verify(String secret, String code) {
        return Totp.totp(
                decode(secret),
                "HmacSHA1"
                , 30
                , 0).equals(code);
    }

    /**
     * 生成 otpauth 的 url
     *
     * @param username 用户名
     * @param secret   密钥
     * @return 符合要求的 url
     */
    public static String url(String username, String secret) {
        return String.format("otpauth://totp/%s?secret=%s", username, secret);
    }
}
