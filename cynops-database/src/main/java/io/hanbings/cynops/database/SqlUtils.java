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

package io.hanbings.cynops.database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class SqlUtils {
    /**
     * 使用工具包自带的规则
     *
     * @param sql sql 语句
     * @return 对于有注入风险的语句返回 true
     */
    public static boolean checkInject(String sql) {
        Pattern pattern = Pattern.compile(SqlUtils.defaultRegex);
        Matcher matcher = pattern.matcher(sql.toLowerCase());
        return matcher.find();
    }

    /**
     * 接受自定义正则的检查器
     *
     * @param sql   sql 语句
     * @param regex 正则表达式
     * @return 返回判断结果
     */
    public static boolean checkInject(String sql, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sql.toLowerCase());
        return matcher.find();
    }

    // 屏蔽没别的 看得不太爽而已
    @SuppressWarnings("ALL")
    public static String defaultRegex = "\\b(and|exec|insert|select|drop|grant|alter|delete" +
            "|update|count|chr|mid|master|truncate|char|declare|or)\\b|(\\*|;|\\+|'|%)\n";
}
