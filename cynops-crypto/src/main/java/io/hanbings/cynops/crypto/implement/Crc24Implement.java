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

package io.hanbings.cynops.crypto.implement;

/**
 * CRC 24 算法实现
 * 用于为 Radix64 提供校验码运算
 */
@SuppressWarnings("unused")
public class Crc24Implement {
    static int crc = 0xb704ce;
    static int poly = 0x1864cfb;

    /**
     * CRC 24 算法
     *
     * @param bytes 原始数据
     * @return 计算后的 byte 数组结果
     */
    public static byte[] crc24(byte[] bytes) {
        for (byte temp : bytes) {
            crc ^= (temp & 0xFF) << 16;
            for (int i = 0; i < 8; ++i) {
                crc <<= 1;
                if ((crc & 0x1000000) != 0)
                    crc ^= poly;
            }
        }
        byte[] target = new byte[3];
        for (int transfer = 16, idx = 0; transfer >= 0; transfer -= 8) {
            target[idx++] = (byte) (crc >>> transfer);
        }
        return target;
    }
}
