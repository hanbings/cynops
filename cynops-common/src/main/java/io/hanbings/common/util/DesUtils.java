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

package io.hanbings.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SuppressWarnings("unused")
public class DesUtils {
    // 参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMS = "DES";
    // 初始化向量(根据需求调整向量的值, 也可以将向量添加到入参变量中)
    private static final byte[] SIV = new byte[16];
    // BASE编码解码
    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    /**
     * 加密
     *
     * @param content 加密的字符串
     * @param key     key值
     * @return 加密后的内容
     * @throws Exception 异常
     */
    public static String encrypt(String content, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHMS);
        // 加密向量
        IvParameterSpec iv = new IvParameterSpec(SIV);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), ALGORITHMS), iv);
        byte[] bytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        // 采用base64算法进行转码,避免出现中文乱码
        return encoder.encodeToString(bytes);
    }

    /**
     * 解密
     *
     * @param encrypt 解密的字符串
     * @param key     解密的key值
     * @return 解密后的内容
     * @throws Exception 异常
     */
    public static String decrypt(String encrypt, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHMS);
        // 加密向量
        IvParameterSpec iv = new IvParameterSpec(SIV);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), ALGORITHMS), iv);
        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = decoder.decode(encrypt);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }
}
