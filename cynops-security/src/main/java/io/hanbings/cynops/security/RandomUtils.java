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

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@SuppressWarnings("unused")
public class RandomUtils {

    /**
     * 反正就一个字 懒
     * 随机一对 RSA 密钥
     *
     * @param length 密钥长度
     * @return 生成的密钥对
     */
    public static KeyPair randomRsaKeys(int length) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(length);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成1024位的 RSA 对
     * 用于加密不推荐这个长度 不安全
     *
     * @return 密钥对
     */
    public static KeyPair randomRsa1024Keys() {
        return randomRsaKeys(1024);
    }

    /**
     * 生成2048位的 RSA 对
     *
     * @return 密钥对
     */
    public static KeyPair randomRsa2048Keys() {
        return randomRsaKeys(2048);
    }

    /**
     * 生成3072位的 RSA 对
     * 即使是加密也推荐这个位数 2021年6月1号以后的 Https (SSL) 都将是3072位的
     *
     * @return 密钥对
     */
    public static KeyPair randomRsa3072Keys() {
        return randomRsaKeys(3072);
    }

    /**
     * 生成4096位的 RSA 对
     *
     * @return 密钥对
     */
    public static KeyPair randomRsa4096Keys() {
        return randomRsaKeys(4096);
    }

    /**
     * 随机生成一串 AES 密码
     * 此外 请在生成密钥的场景下尽可能使用返回 byte[] 的方法 因为 byte[] 从内存中擦除的时间比 String 更短 更安全
     *
     * @param length 期望的密钥长度
     * @return 字符串密钥
     */
    public static String randomAesKey(int length) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(length);
            SecretKey secretKey = keyGen.generateKey();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : secretKey.getEncoded()) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 随机生成一串 AES 密码
     *
     * @param length 期望的密钥长度
     * @return 字符串密钥
     */
    public static byte[] randomAesKeyBytes(int length) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(length);
            return keyGen.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成 128 位的 AES 密钥
     *
     * @return 生成的密钥
     */
    public static String randomAes128Key() {
        return randomAesKey(128);
    }

    /**
     * 生成 192 位的 AES 密钥
     *
     * @return 生成的密钥
     */
    public static String randomAes192Key() {
        return randomAesKey(192);
    }

    /**
     * 生成 256 位的 AES 密钥
     *
     * @return 生成的密钥
     */
    public static String randomAes256Key() {
        return randomAesKey(256);
    }

    /**
     * 生成 128 位的 AES 密钥
     *
     * @return 生成的密钥
     */
    public static byte[] randomAes128KeyBytes() {
        return randomAesKeyByte(128);
    }

    /**
     * 生成 192 位的 AES 密钥
     *
     * @return 生成的密钥
     */
    public static byte[] randomAes192KeyBytes() {
        return randomAesKeyByte(192);
    }

    /**
     * 生成 256 位的 AES 密钥
     *
     * @return 生成的密钥
     */
    public static byte[] randomAes256KeyBytes() {
        return randomAesKeyByte(256);
    }

    /**
     * 随机生成一串 DES 密钥 <br>
     * 定长 16 字符 <br>
     * 注意: 这里会生成 16 字符 但是 DesUtils 所接受的参数是 8 字符 仍需要进行裁剪 <br>
     * 此外 请在生成密钥的场景下尽可能使用返回 byte[] 的方法 因为 byte[] 从内存中擦除的时间比 String 更短 更安全
     *
     * @return 字符串密钥
     */
    public static String randomDesKey() {
        try {
            SecureRandom random = new SecureRandom();
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            keyGen.init(random);
            SecretKey secretKey = keyGen.generateKey();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : secretKey.getEncoded()) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 随机生成一串 DES 密钥 <br>
     * 定长
     *
     * @return 字符串密钥
     */
    public static byte[] randomDesKeyBytes() {
        try {
            SecureRandom random = new SecureRandom();
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            keyGen.init(random);
            return keyGen.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
