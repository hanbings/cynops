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

package io.hanbings.cynops.extra.lang;

/**
 * 按照 apache common lang 的 Javadocs 来列举需要实现的方法 <br>
 * 有部分代码直接修改于 apache common lang <br>
 * 根据 Apache 协议 应该指出被更改的代码的详细内容 这部分工作将会在具体的方法注释中进行 <br>
 * 因为参照 apache common lang 的方法名 参数和功能 所以示例也采用 apache common lang 以保证方法的实现正确
 */
@SuppressWarnings("unused")
public class StringUtils {
    /**
     * 检查所有 CharSequences 是否为 null
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isAllNull(CharSequence... source) {
        for (CharSequence sequence : source) {
            if (sequence == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查 CharSequences 是否为 null
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isNull(CharSequence source) {
        return source == null;
    }

    /**
     * 检查所有 CharSequences 是否为空 ("")、null 或仅空白 <br>
     * apache common lang 使用了他们包下面的 ArrayUtil 所以这一个方法自己实现
     *
     * <pre>
     * StringUtils.isAllBlank(null)             = true
     * StringUtils.isAllBlank(null, "foo")      = false
     * StringUtils.isAllBlank(null, null)       = true
     * StringUtils.isAllBlank("", "bar")        = false
     * StringUtils.isAllBlank("bob", "")        = false
     * StringUtils.isAllBlank("  bob  ", null)  = false
     * StringUtils.isAllBlank(" ", "bar")       = false
     * StringUtils.isAllBlank("foo", "bar")     = false
     * StringUtils.isAllBlank(new String[] {})  = true
     * </pre>
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isAllBlank(CharSequence... source) {
        return false;
    }

    /**
     * 检查所有 CharSequences 是否为空 ("") 或 null
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isAllEmpty(CharSequence... source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否仅包含小写字符
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isAllLowerCase(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否仅包含大写字符
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isAllUpperCase(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否仅包含 Unicode 字母
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isAlpha(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否仅包含 Unicode 字母或数字
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isAlphanumeric(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否仅包含 Unicode 字母、数字或空格 ( ' ')
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isAlphanumericSpace(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否仅包含 Unicode 字母和空格 (' ')
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isAlphaSpace(CharSequence source) {
        return false;
    }

    /**
     * 检查是否有任何 CharSequences 为空 ("") 或 null 或仅空格
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isAnyBlank(CharSequence... source) {
        return false;
    }

    /**
     * 检查是否有任何 CharSequences 为空 ("") 或 null
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isAnyEmpty(CharSequence... source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否仅包含 ASCII 可打印字符
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */

    public static boolean isAsciiPrintable(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否为空 ("")、null 或仅空白
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isBlank(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否为空 ("") 或 null
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isEmpty(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否包含大写和小写字符的混合大小写
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isMixedCase(CharSequence source) {
        return false;
    }

    /**
     * 检查是否没有一个 CharSequences 为空 ("")、null 或空白
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isNoneBlank(CharSequence... source) {
        return false;
    }

    /**
     * 检查是否没有一个 CharSequences 为空 ("") 或 null
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isNoneEmpty(CharSequence... source) {
        return false;
    }


    /**
     * 检查 CharSequence 是否不为空 ("")、不为空且不为空白
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isNotBlank(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否不为空 ("") 且不为 null
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isNotEmpty(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否仅包含 Unicode 数字
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isNumeric(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否仅包含 Unicode 数字或空格 ( ' ')
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isNumericSpace(CharSequence source) {
        return false;
    }

    /**
     * 检查 CharSequence 是否仅包含空格
     *
     * @param source 待处理 CharSequence
     * @return 返回判断结果
     */
    public static boolean isWhitespace(CharSequence source) {
        return false;
    }

}
