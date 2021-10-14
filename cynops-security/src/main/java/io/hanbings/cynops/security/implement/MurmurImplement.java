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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 参考 https://github.com/sangupta/murmur <br>
 * 该仓库采用 apache 2.0 协议 根据协议 更改的地方有 <br>
 * 1. 将单独的类文件合并为一个类 <br>
 * 2. 更改了某些地方不一致的 16 进制表达法
 * 例如 标记 long 类型的 l 或 L 统一为大写 L
 * 16 进制使用 0x 为标识符 且 16 进制字母部分为全小写
 * <br>
 * 3. 英文注释翻译为中文
 */
@SuppressWarnings("unused SpellCheckingInspection")
public class MurmurImplement {

    // 用于将各种掩码转换为无符号值的简单常量
    private static final int UNSIGNED_MASK = 0xff;
    private static final long UINT_MASK = 0xffffffffL;
    private static final long LONG_MASK = 0xffffffffffffffffL;

    /**
     * 按照原始源代码中的描述计算 Murmur1 哈希
     * 这里所指的原始源代码是来着于 C++ 的原始 murmur 实现
     * <a href="https://sites.google.com/site/murmurhash/MurmurHash.cpp?attredirects=0">MurmurHash.cpp</a>
     * 另外付有一个 Google 的文件链接
     * <a href="https://sites.google.com/site/murmurhash/">Murmur Project</a>
     *
     * @param data   需要散列的数据
     * @param length 需要散列的数据长度
     * @param seed   用于计算哈希的种子
     * @return 计算出的哈希值
     */
    public static long murmur1(final byte[] data, int length, long seed) {
        final long m = 0xc6a4a793L;
        final int r = 16;
        long h = seed ^ (length * m);
        int length4 = length >> 2;
        for (int i = 0; i < length4; i++) {
            final int i4 = i << 2;

            long k = (data[i4] & UNSIGNED_MASK);
            k |= (data[i4 + 1] & UNSIGNED_MASK) << 8;
            k |= (data[i4 + 2] & UNSIGNED_MASK) << 16;
            k |= (long) (data[i4 + 3] & UNSIGNED_MASK) << 24;

            h = ((h + k) & UINT_MASK);
            h = ((h * m) & UINT_MASK);
            h ^= ((h >> 16) & UINT_MASK);
        }
        int offset = length4 << 2;

        switch (length & 3) {
            case 3:
                h += ((data[offset + 2] << 16) & UINT_MASK);

            case 2:
                h += ((data[offset + 1] << 8) & UINT_MASK);

            case 1:
                h += ((data[offset]) & UINT_MASK);
                h = ((h * m) & UINT_MASK);
                h ^= ((h >> r) & UINT_MASK);
        }

        h = ((h * m) & UINT_MASK);
        h ^= ((h >> 10) & UINT_MASK);
        h = ((h * m) & UINT_MASK);
        h ^= ((h >> 17) & UINT_MASK);
        return h;
    }

    /**
     * 按照原始源代码中的描述计算 Murmur2 32 位 哈希
     * 原始源代码文件 <a href="https://sites.google.com/site/murmurhash/MurmurHash2.cpp?attredirects=0">
     * MurmurHash2.cpp</a>
     *
     * @param data   需要散列的数据
     * @param length 需要散列的数据长度
     * @param seed   用于计算哈希的种子
     * @return 计算出的哈希值
     */
    public static long murmur2With32(final byte[] data, int length, long seed) {
        final long m = 0x5bd1e995L;
        final int r = 24;
        long hash = ((seed ^ length) & UINT_MASK);
        int length4 = length >>> 2;

        for (int i = 0; i < length4; i++) {
            final int i4 = i << 2;

            long k = (data[i4] & UNSIGNED_MASK);
            k |= (data[i4 + 1] & UNSIGNED_MASK) << 8;
            k |= (data[i4 + 2] & UNSIGNED_MASK) << 16;
            k |= (long) (data[i4 + 3] & UNSIGNED_MASK) << 24;

            k = ((k * m) & UINT_MASK);
            k ^= ((k >>> r) & UINT_MASK);
            k = ((k * m) & UINT_MASK);

            hash = ((hash * m) & UINT_MASK);
            hash = ((hash ^ k) & UINT_MASK);
        }

        int offset = length4 << 2;
        switch (length & 3) {
            case 3:
                hash ^= ((data[offset + 2] & UNSIGNED_MASK) << 16);
            case 2:
                hash ^= ((data[offset + 1] & UNSIGNED_MASK) << 8);
            case 1:
                hash ^= (data[offset] & UNSIGNED_MASK);
                hash = ((hash * m) & UINT_MASK);
        }

        hash ^= ((hash >>> 13) & UINT_MASK);
        hash = ((hash * m) & UINT_MASK);
        hash ^= hash >>> 15;
        return hash;
    }

    /**
     * 按照原始源代码中的描述计算 Murmur2 64 位 哈希
     * 原始源代码文件 <a href="https://sites.google.com/site/murmurhash/MurmurHash2_64.cpp?attredirects=0">
     * MurmurHash2_64.cpp</a>
     *
     * @param data   需要散列的数据
     * @param length 需要散列的数据长度
     * @param seed   用于计算哈希的种子
     * @return 计算出的哈希值
     */
    public static long murmur2With64(final byte[] data, int length, long seed) {
        final long m = 0xc6a4a7935bd1e995L;
        final int r = 47;
        long h = (seed & UINT_MASK) ^ (length * m);
        int length8 = length >> 3;

        for (int i = 0; i < length8; i++) {
            final int i8 = i << 3;
            long k = ((long) data[i8] & 0xff) + (((long) data[i8 + 1] & 0xff) << 8)
                    + (((long) data[i8 + 2] & 0xff) << 16) + (((long) data[i8 + 3] & 0xff) << 24)
                    + (((long) data[i8 + 4] & 0xff) << 32) + (((long) data[i8 + 5] & 0xff) << 40)
                    + (((long) data[i8 + 6] & 0xff) << 48) + (((long) data[i8 + 7] & 0xff) << 56);
            k *= m;
            k ^= k >>> r;
            k *= m;
            h ^= k;
            h *= m;
        }

        switch (length & 7) {
            case 7:
                h ^= (long) (data[(length & ~7) + 6] & 0xff) << 48;
            case 6:
                h ^= (long) (data[(length & ~7) + 5] & 0xff) << 40;
            case 5:
                h ^= (long) (data[(length & ~7) + 4] & 0xff) << 32;
            case 4:
                h ^= (long) (data[(length & ~7) + 3] & 0xff) << 24;
            case 3:
                h ^= (long) (data[(length & ~7) + 2] & 0xff) << 16;
            case 2:
                h ^= (long) (data[(length & ~7) + 1] & 0xff) << 8;
            case 1:
                h ^= (data[length & ~7] & 0xff);
                h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;
        return h;
    }

    private static final int X86_32_C1 = 0xcc9e2d51;
    private static final int X86_32_C2 = 0x1b873593;
    private static final long X64_128_C1 = 0x87c37b91114253d5L;
    private static final long X64_128_C2 = 0x4cf5ad432745937fL;

    /**
     * 按照原始源代码中的描述计算 Murmur3 32 位 哈希
     * <a href="https://code.google.com/p/smhasher/source/browse/trunk/MurmurHash3.cpp">
     * MurmurHash3.cpp</a>
     *
     * @param data   需要散列的数据
     * @param length 需要散列的数据长度
     * @param seed   用于计算哈希的种子
     * @return 计算出的哈希值
     */
    public static long murmur3With32(final byte[] data, int length, long seed) {
        final int nblocks = length >> 2;
        long hash = seed;

        for (int i = 0; i < nblocks; i++) {
            final int i4 = i << 2;

            long k1 = (data[i4] & UNSIGNED_MASK);
            k1 |= (data[i4 + 1] & UNSIGNED_MASK) << 8;
            k1 |= (data[i4 + 2] & UNSIGNED_MASK) << 16;
            k1 |= (long) (data[i4 + 3] & UNSIGNED_MASK) << 24;

            k1 = (k1 * X86_32_C1) & UINT_MASK;
            k1 = rotl32(k1, 15);
            k1 = (k1 * X86_32_C2) & UINT_MASK;

            hash ^= k1;
            hash = rotl32(hash, 13);
            hash = (((hash * 5) & UINT_MASK) + 0xe6546b64L) & UINT_MASK;
        }

        int offset = (nblocks << 2);
        long k1 = 0;

        switch (length & 3) {
            case 3:
                k1 ^= (data[offset + 2] << 16) & UINT_MASK;
            case 2:
                k1 ^= (data[offset + 1] << 8) & UINT_MASK;
            case 1:
                k1 ^= data[offset];
                k1 = (k1 * X86_32_C1) & UINT_MASK;
                k1 = rotl32(k1, 15);
                k1 = (k1 * X86_32_C2) & UINT_MASK;
                hash ^= k1;
        }

        hash ^= length;
        hash = fmix32(hash);
        return hash;
    }

    /**
     * 按照原始源代码中的描述计算 Murmur3 128 位 哈希
     * <a href="https://code.google.com/p/smhasher/source/browse/trunk/MurmurHash3.cpp">
     * MurmurHash3.cpp</a>
     *
     * @param data   需要散列的数据
     * @param length 需要散列的数据长度
     * @param seed   用于计算哈希的种子
     * @return 计算出的哈希值
     */
    public static long[] murmur3With128(final byte[] data, final int length, final long seed) {
        long h1 = seed;
        long h2 = seed;

        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        while (buffer.remaining() >= 16) {
            long k1 = buffer.getLong();
            long k2 = buffer.getLong();

            h1 ^= mixK1(k1);

            h1 = Long.rotateLeft(h1, 27);
            h1 += h2;
            h1 = h1 * 5 + 0x52dce729;

            h2 ^= mixK2(k2);

            h2 = Long.rotateLeft(h2, 31);
            h2 += h1;
            h2 = h2 * 5 + 0x38495ab5;
        }

        buffer.compact();
        buffer.flip();

        final int remaining = buffer.remaining();
        if (remaining > 0) {
            long k1 = 0;
            long k2 = 0;
            switch (buffer.remaining()) {
                case 15:
                    k2 ^= (long) (buffer.get(14) & UNSIGNED_MASK) << 48;
                case 14:
                    k2 ^= (long) (buffer.get(13) & UNSIGNED_MASK) << 40;
                case 13:
                    k2 ^= (long) (buffer.get(12) & UNSIGNED_MASK) << 32;
                case 12:
                    k2 ^= (long) (buffer.get(11) & UNSIGNED_MASK) << 24;
                case 11:
                    k2 ^= (long) (buffer.get(10) & UNSIGNED_MASK) << 16;
                case 10:
                    k2 ^= (long) (buffer.get(9) & UNSIGNED_MASK) << 8;
                case 9:
                    k2 ^= buffer.get(8) & UNSIGNED_MASK;
                case 8:
                    k1 ^= buffer.getLong();
                    break;
                case 7:
                    k1 ^= (long) (buffer.get(6) & UNSIGNED_MASK) << 48;
                case 6:
                    k1 ^= (long) (buffer.get(5) & UNSIGNED_MASK) << 40;
                case 5:
                    k1 ^= (long) (buffer.get(4) & UNSIGNED_MASK) << 32;
                case 4:
                    k1 ^= (long) (buffer.get(3) & UNSIGNED_MASK) << 24;
                case 3:
                    k1 ^= (long) (buffer.get(2) & UNSIGNED_MASK) << 16;
                case 2:
                    k1 ^= (long) (buffer.get(1) & UNSIGNED_MASK) << 8;
                case 1:
                    k1 ^= (buffer.get(0) & UNSIGNED_MASK);
                    break;
                default:
                    throw new AssertionError("Code should not reach here!");
            }

            h1 ^= mixK1(k1);
            h2 ^= mixK2(k2);
        }

        h1 ^= length;
        h2 ^= length;

        h1 += h2;
        h2 += h1;

        h1 = fmix64(h1);
        h2 = fmix64(h2);

        h1 += h2;
        h2 += h1;

        return (new long[]{h1, h2});
    }

    private static long mixK1(long k1) {
        k1 *= X64_128_C1;
        k1 = Long.rotateLeft(k1, 31);
        k1 *= X64_128_C2;

        return k1;
    }

    private static long mixK2(long k2) {
        k2 *= X64_128_C2;
        k2 = Long.rotateLeft(k2, 33);
        k2 *= X64_128_C1;

        return k2;
    }

    private static long rotl32(long original, int shift) {
        return ((original << shift) & UINT_MASK) | ((original >>> (32 - shift)) & UINT_MASK);
    }

    private static long fmix32(long h) {
        h ^= (h >> 16) & UINT_MASK;
        h = (h * 0x85ebca6bL) & UINT_MASK;
        h ^= (h >> 13) & UINT_MASK;
        h = (h * 0xc2b2ae35) & UINT_MASK;
        h ^= (h >> 16) & UINT_MASK;

        return h;
    }

    private static long fmix64(long k) {
        k ^= k >>> 33;
        k *= 0xff51afd7ed558ccdL;
        k ^= k >>> 33;
        k *= 0xc4ceb9fe1a85ec53L;
        k ^= k >>> 33;

        return k;
    }
}
