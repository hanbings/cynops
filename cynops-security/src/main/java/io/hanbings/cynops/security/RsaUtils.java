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

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

@SuppressWarnings("unused")
public class RsaUtils {
    // 参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMS = "RSA";

    /**
     * 加密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 String Base64 的数据源数据 String Base64 的公钥
     *
     * @param source    加密的字符串
     * @param publicKey 公钥值
     * @return 加密后的内容 以 Base64 编码后的 String
     */
    public static String encryptToString(String source, String publicKey) {
        return Base64Utils.encode(encrypt((Base64Utils.decode(source)).getBytes(StandardCharsets.UTF_8), (Base64Utils.decode(publicKey)).getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 加密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 byte[] 的数据源数据 String Base64 的公钥
     *
     * @param source    加密的字符串
     * @param publicKey 公钥值
     * @return 加密后的内容 以 Base64 编码后的 String
     */
    public static String encryptToString(byte[] source, String publicKey) {
        return Base64Utils.encode(encrypt(source, (Base64Utils.decode(publicKey)).getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 加密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 String Base64 的数据源数据 byte[] 的公钥
     *
     * @param source    加密的字符串
     * @param publicKey 公钥值
     * @return 加密后的内容 以 Base64 编码后的 String
     */
    public static String encryptToString(String source, byte[] publicKey) {
        return Base64Utils.encode(encrypt((Base64Utils.decode(source)).getBytes(StandardCharsets.UTF_8), publicKey));
    }

    /**
     * 加密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 byte[] 的数据源数据 byte[] 的公钥
     *
     * @param source    加密的字符串
     * @param publicKey 公钥值
     * @return 加密后的内容 以 Base64 编码后的 String
     */
    public static String encryptToString(byte[] source, byte[] publicKey) {
        return Base64Utils.encode(encrypt(source, publicKey));
    }

    /**
     * 加密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 String Base64 的数据源数据 String Base64 的公钥
     *
     * @param source    加密的字符串
     * @param publicKey 公钥值
     * @return 加密后的内容 byte[]
     */
    public static byte[] encrypt(String source, String publicKey) {
        return encrypt((Base64Utils.decode(source)).getBytes(StandardCharsets.UTF_8), (Base64Utils.decode(publicKey)).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 加密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 byte[] 的数据源数据 String Base64 的公钥
     *
     * @param source    加密的字符串
     * @param publicKey 公钥值
     * @return 加密后的内容 byte[]
     */
    public static byte[] encrypt(byte[] source, String publicKey) {
        return encrypt(source, (Base64Utils.decode(publicKey)).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 加密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 byte[] 的数据源数据 byte[] 的公钥
     *
     * @param source    加密的字符串
     * @param publicKey 公钥值
     * @return 加密后的内容 byte[]
     */
    public static byte[] encrypt(String source, byte[] publicKey) {
        return encrypt((Base64Utils.decode(source)).getBytes(StandardCharsets.UTF_8), publicKey);
    }

    /**
     * 加密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 byte[] 的数据源数据 byte[] 的公钥
     *
     * @param source    加密的字符串
     * @param publicKey 公钥值
     * @return 加密后的内容 byte[]
     */
    public static byte[] encrypt(byte[] source, byte[] publicKey) {
        try {
            // 生成公钥
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKey));
            //RSA加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return cipher.doFinal(source);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 String Base64 的数据源数据 String Base64 的公钥
     *
     * @param source     解密的字符串
     * @param privateKey 私钥值
     * @return 解密后的内容 Base64 编码后的 String
     */
    public static String decryptToString(String source, String privateKey) {
        return Base64Utils.encode(decrypt((Base64Utils.decode(source)).getBytes(StandardCharsets.UTF_8), (Base64Utils.decode(privateKey)).getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 解密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 byte[] 的数据源数据 String Base64 的公钥
     *
     * @param source     解密的字符串
     * @param privateKey 私钥值
     * @return 解密后的内容 Base64 编码后的 String
     */
    public static String decryptToString(byte[] source, String privateKey) {
        return Base64Utils.encode(decrypt(source, (Base64Utils.decode(privateKey)).getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 解密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 byte[] 的数据源数据 byte[] 的公钥
     *
     * @param source     解密的字符串
     * @param privateKey 私钥值
     * @return 解密后的内容 Base64 编码后的 String
     */
    public static String decryptToString(String source, byte[] privateKey) {
        return Base64Utils.encode(decrypt((Base64Utils.decode(source)).getBytes(StandardCharsets.UTF_8), privateKey));
    }

    /**
     * 解密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 byte[] 的数据源数据 byte[] 的公钥
     *
     * @param source     解密的字符串
     * @param privateKey 私钥值
     * @return 解密后的内容 Base64 编码后的 String
     */
    public static String decryptToString(byte[] source, byte[] privateKey) {
        return Base64Utils.encode(decrypt(source, privateKey));
    }

    /**
     * 解密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 String Base64 的数据源数据 String Base64 的公钥
     *
     * @param source     解密的字符串
     * @param privateKey 私钥值
     * @return 解密后的内容 byte[]
     */
    public static byte[] decrypt(String source, String privateKey) {
        return decrypt((Base64Utils.decode(source)).getBytes(StandardCharsets.UTF_8), (Base64Utils.decode(privateKey)).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 byte[] 的数据源数据 String Base64 的公钥
     *
     * @param source     解密的字符串
     * @param privateKey 私钥值
     * @return 解密后的内容 byte[]
     */
    public static byte[] decrypt(byte[] source, String privateKey) {
        return decrypt(source, (Base64Utils.decode(privateKey)).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 byte[] 的数据源数据 byte[] 的公钥
     *
     * @param source     解密的字符串
     * @param privateKey 私钥值
     * @return 解密后的内容 byte[]
     */
    public static byte[] decrypt(String source, byte[] privateKey) {
        return decrypt((Base64Utils.decode(source)).getBytes(StandardCharsets.UTF_8), privateKey);
    }

    /**
     * 解密 <br>
     * 出错时将抛出异常并返回 null <br>
     * 输入 byte[] 的数据源数据 byte[] 的公钥
     *
     * @param source     解密的字符串
     * @param privateKey 私钥值
     * @return 解密后的内容 byte[]
     */
    public static byte[] decrypt(byte[] source, byte[] privateKey) {
        try {
            // 生成私钥
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKey));
            // RSA解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            return cipher.doFinal(source);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用于校验生成的密钥对
     *
     * @param value      任意字符串值
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @return 校验结果
     */
    public static boolean check(String value, String publicKey, String privateKey) {
        String publicValue = encryptToString(value, publicKey);
        String privateValue = decryptToString(publicValue, privateKey);
        return Objects.equals(privateValue, value);
    }

    /**
     * 用于校验生成的密钥对
     *
     * @param value      任意字符串值
     * @param publicKey  公钥
     * @param privateKey 私钥
     * @return 校验结果
     */
    public static boolean check(String value, byte[] publicKey, byte[] privateKey) {
        String publicValue = encryptToString(value, publicKey);
        String privateValue = decryptToString(publicValue, privateKey);
        return Objects.equals(privateValue, value);
    }

    /**
     * 处理 Base64 格式公钥为 pem 格式的公钥
     *
     * @param base64 纯 Base64 公钥
     * @return 带有 pem 头的公钥
     */
    public static String getPemPublicKey(String base64) {
        return "-----BEGIN PUBLIC KEY-----\n" + base64 + "\n-----END PUBLIC KEY-----";
    }

    /**
     * 处理 Base64 格式私钥为 pem 格式的私钥
     *
     * @param base64 纯 Base64 私钥
     * @return 带有 pem 头的私钥
     */
    public static String getPemPrivateKey(String base64) {
        return "-----BEGIN PRIVATE KEY-----\n" + base64 + "\n-----END PRIVATE KEY-----";
    }
}
