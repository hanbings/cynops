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
import java.util.Base64;
import java.util.Objects;

@SuppressWarnings("unused")
public class RsaUtils {
    // 参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMS = "RSA";

    /**
     * 加密
     *
     * @param source 加密的字符串
     * @param publicKey  公钥值
     * @return 加密后的内容
     * @throws Exception 异常
     */
    public static String encrypt(String source, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(source.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 解密
     *
     * @param source 解密的字符串
     * @param privateKey 私钥值
     * @return 解密后的内容
     * @throws Exception 异常
     */
    public static String decrypt(String source, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] input = Base64.getDecoder().decode(source);
        //base64编码的私钥
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(input));
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
        try {
            String publicValue = encrypt(value,publicKey);
            String privateValue = decrypt(publicValue,privateKey);
            return Objects.equals(privateValue, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
