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
import java.security.PrivateKey;
import java.security.PublicKey;
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
     * @param key    key值
     * @return 加密后的内容
     * @throws Exception 异常
     */
    public static String encrypt(String source, String key) throws Exception {
        // 处理公钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key.getBytes(StandardCharsets.UTF_8));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // 进行加密
        Cipher cipher = Cipher.getInstance(ALGORITHMS);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = source.getBytes();
        bytes = cipher.doFinal(bytes);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    /**
     * 解密
     *
     * @param source 解密的字符串
     * @param key    解密的key值
     * @return 解密后的内容
     * @throws Exception 异常
     */
    public static String decrypt(String source, String key) throws Exception {
        // 处理私钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key.getBytes(StandardCharsets.UTF_8));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // 进行解密
        Cipher cipher = Cipher.getInstance(ALGORITHMS);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(source.getBytes(StandardCharsets.UTF_8));
        bytes = cipher.doFinal(bytes);
        return new String(bytes);
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
            return Objects.equals(RsaUtils.encrypt(value, publicKey), RsaUtils.decrypt(value, privateKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
