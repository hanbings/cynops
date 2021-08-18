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

package io.hanbings.cynops.lang;

@SuppressWarnings("unused")
public class StringUtils {
    /**
     * 字符串首字母大写 <br>
     * 传说中有一种ascii移位转换首字母的 <br>
     * 但似乎需要几层判断 有需要以后再补
     *
     * @param source 输入字符串
     * @return 返回字符串
     */
    public static String initialToUpperCase(String source) {
        return source.substring(0, 1).toUpperCase() + source.substring(1);
    }

    /**
     * 字符串首字母小写
     * @param source 输入字符串
     * @return 返回字符串
     */
    public static String initialToLowerCase(String source){
        return source.substring(0,1).toLowerCase() + source.substring(1);
    }
}
