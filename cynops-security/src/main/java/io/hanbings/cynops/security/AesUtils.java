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
// TODO: 有时间来重命名下变量
public class AesUtils {
    /**
     * 加密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     *
     * @param source 加密字符串
     * @param key    密钥
     * @return 加密后的字符串
     */
    public static String encrypt(String source, String key) {
        try {
            // 补足密码长度
            int plus = 16 - key.length();
            byte[] data = key.getBytes(StandardCharsets.UTF_8);
            byte[] raw = new byte[16];
            byte[] bytes = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            for (int i = 0; i < 16; i++) {
                if (data.length > i) {
                    raw[i] = data[i];
                } else {
                    raw[i] = bytes[0];
                }
            }

            // 加密
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            // 转换为 String
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
     * 解密 使用 AES/ECB/PKCS5Padding <br>
     * 如果加密解密过程中出错将返回 null
     *
     * @param source 解密字符串
     * @param key    密钥
     * @return 解密后的字符串
     */
    public static String decrypt(String source, String key) {
        try {
            // 补足密码长度
            int plus = 16 - key.length();
            byte[] data = key.getBytes(StandardCharsets.UTF_8);
            byte[] raw = new byte[16];
            byte[] bytes = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            for (int count = 0; count < 16; count++) {
                if (data.length > count) {
                    raw[count] = data[count];
                } else {
                    raw[count] = bytes[0];
                }
            }

            //解密
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
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
