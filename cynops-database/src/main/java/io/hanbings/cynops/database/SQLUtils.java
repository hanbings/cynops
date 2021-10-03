package io.hanbings.cynops.database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class SQLUtils {
    /**
     * 使用工具包自带的规则
     *
     * @param sql sql 语句
     * @return 对于有注入风险的语句返回 true
     */
    public static boolean checkInject(String sql) {
        Pattern pattern = Pattern.compile(SQLUtils.defaultRegex);
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
