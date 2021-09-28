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

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("unused")
public class RandomUtils {

    /**
     * 反正就一个字 懒
     * 随机一对 RSA 密钥
     *
     * @param length 密钥长度
     * @return 生成的密钥对
     */
    public static KeyPair randomRSAKey(int length) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(length);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
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
    public static KeyPair randomRSA1024Key() {
        return randomRSAKey(1024);
    }

    /**
     * 生成2048位的 RSA 对
     *
     * @return 密钥对
     */
    public static KeyPair randomRSA2048Key() {
        return randomRSAKey(2048);
    }

    /**
     * 生成1024位的 RSA 对
     * 即使是加密也推荐这个位数 2021年6月1号以后的 Https (SSL) 都将是3072位的
     *
     * @return 密钥对
     */
    public static KeyPair randomRSA3072Key() {
        return randomRSAKey(3072);
    }
}
