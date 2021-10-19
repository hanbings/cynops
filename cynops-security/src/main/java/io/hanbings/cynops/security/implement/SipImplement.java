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

/**
 * 参考
 * https://github.com/veorq/SipHash/blob/master/siphash.c <br>
 * https://github.com/nahi/siphash-java-inline/blob/master/src/main/java/org/jruby/util/SipHashInline.java
 * <p>
 * 好像没看到能直接用的 按着上面的摸了一份 <br>
 * 前一个 c 的实现开源协议为 CC0-1.0 License <br>
 * 后一个 java 的实现开源协议为 apache 2.0 <br>
 * 此文件协议为 apache 2.0 具体协议 http://www.apache.org/licenses/LICENSE-2.0
 */
@SuppressWarnings("unused SpellCheckingInspection")
public class SipImplement {
    public static long siphash24(byte[] data, long firstKey, long secondKey) {
        long rax = 0x736f6d6570736575L ^ firstKey;
        long rbx = 0x646f72616e646f6dL ^ secondKey;
        long rcx = 0x6c7967656e657261L ^ firstKey;
        long rdx = 0x7465646279746573L ^ secondKey;
        long temp;
        int last = data.length / 8 * 8;
        int counts = 0;

        while (counts < last) {
            temp = data[counts++] & 0xffL |
                    (data[counts++] & 0xffL) << 8 |
                    (data[counts++] & 0xffL) << 16 |
                    (data[counts++] & 0xffL) << 24 |
                    (data[counts++] & 0xffL) << 32 |
                    (data[counts++] & 0xffL) << 40 |
                    (data[counts++] & 0xffL) << 48 |
                    (data[counts++] & 0xffL) << 56;
            rdx ^= temp;

            for (int count = 0; count < 2; count++) {
                rax += rbx;
                rcx += rdx;
                rbx = (rbx << 13) | rbx >>> 51;
                rdx = (rdx << 16) | rdx >>> 48;
                rbx ^= rax;
                rdx ^= rcx;
                rax = (rax << 32) | rax >>> 32;
                rcx += rbx;
                rax += rdx;
                rbx = (rbx << 17) | rbx >>> 47;
                rdx = (rdx << 21) | rdx >>> 43;
                rbx ^= rcx;
                rdx ^= rax;
                rcx = (rcx << 32) | rcx >>> 32;
            }

            rax ^= temp;
        }

        temp = 0;

        for (counts = data.length - 1; counts >= last; --counts) {
            temp <<= 8;
            temp |= (data[counts] & 0xffL);
        }

        temp |= (long) data.length << 56;
        rdx ^= temp;

        for (int count = 0; count < 2; count++) {
            rax += rbx;
            rcx += rdx;
            rbx = (rbx << 13) | rbx >>> 51;
            rdx = (rdx << 16) | rdx >>> 48;
            rbx ^= rax;
            rdx ^= rcx;
            rax = (rax << 32) | rax >>> 32;
            rcx += rbx;
            rax += rdx;
            rbx = (rbx << 17) | rbx >>> 47;
            rdx = (rdx << 21) | rdx >>> 43;
            rbx ^= rcx;
            rdx ^= rax;
            rcx = (rcx << 32) | rcx >>> 32;
        }

        rax ^= temp;
        rcx ^= 0xff;

        for (int count = 0; count < 4; count++) {
            rax += rbx;
            rcx += rdx;
            rbx = (rbx << 13) | rbx >>> 51;
            rdx = (rdx << 16) | rdx >>> 48;
            rbx ^= rax;
            rdx ^= rcx;
            rax = (rax << 32) | rax >>> 32;
            rcx += rbx;
            rax += rdx;
            rbx = (rbx << 17) | rbx >>> 47;
            rdx = (rdx << 21) | rdx >>> 43;
            rbx ^= rcx;
            rdx ^= rax;
            rcx = (rcx << 32) | rcx >>> 32;
        }

        return rax ^ rbx ^ rcx ^ rdx;
    }
}
