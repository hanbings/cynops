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

package io.hanbings.cynops.security.implement;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Radix 64 编码
 * 由 Base 64 变体 MIME + CRC24 校验值变体
 * 这里的 Radix 64 是按照 RFC 4880 中所描述用于 OpenPGP 的 Radix 64 实现
 * 详细内容 https://datatracker.ietf.org/doc/html/rfc4880
 */
@SuppressWarnings("unused")
public class Radix64Implement {
    /**
     * 计算 Radix 64
     * @param bytes 原始数据
     * @return 计算结果 byte[]
     */
   public static byte[] radix64(byte[] bytes) {
       // 先进行 base64 mime 计算
       byte[] base64 = Base64.getMimeEncoder().encode(bytes);
       // 计算 crc24
       byte[] crc24 = Base64.getMimeEncoder().encode(Crc24Implement.crc24(bytes));
       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
       try {
           outputStream.write(base64);
           outputStream.write("\n=".getBytes(StandardCharsets.UTF_8));
           outputStream.write(crc24);
           return outputStream.toByteArray();
       } catch (IOException e) {
           e.printStackTrace();
           return null;
       }
   }
}
