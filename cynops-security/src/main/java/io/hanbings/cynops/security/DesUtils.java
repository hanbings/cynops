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
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused SpellCheckingInspection")
public class DesUtils {
    /**
     * 加密 使用 DES/ECB/PKCS5Padding <br>
     * 由于密码定长 不做处理 无法加密或解密将返回 null <br>
     * 密码要求 64位 即 8 个字符 (String)
     *
     * @param source 加密字符串
     * @param key    密钥
     * @return 加密后的字符串
     */
    public static String encrypt(String source, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] byteArray = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (byte temp : byteArray) {
                stringBuilder.append(String.format("%02x", temp));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解密 使用 DES/ECB/PKCS5Padding <br>
     * 由于密码定长 不做处理 无法加密或解密将返回 null <br>
     * 密码要求 64位 即 8 个字符 (String)
     *
     * @param source 解密字符串
     * @param key    密钥
     * @return 解密后的字符串
     */
    public static String decrypt(String source, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            source = source.toLowerCase();
            final byte[] byteArray = new byte[source.length() / 2];
            int index = 0;
            for (int count = 0; count < byteArray.length; count++) {
                //因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
                byte high = (byte) (Character.digit(source.charAt(index), 16) & 0xff);
                byte low = (byte) (Character.digit(source.charAt(index + 1), 16) & 0xff);
                byteArray[count] = (byte) (high << 4 | low);
                index += 2;
            }
            byte[] original = cipher.doFinal(byteArray);

            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            return null;
        }
    }
}
