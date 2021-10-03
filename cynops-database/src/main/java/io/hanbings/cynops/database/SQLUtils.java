package io.hanbings.cynops.database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class SQLUtils {
    public static boolean checkInject(String sql) {
        Pattern pattern = Pattern.compile(SQLUtils.defaultRegex);
        Matcher matcher = pattern.matcher(sql.toLowerCase());
        return matcher.find();
    }

    public static boolean checkInject(String sql, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sql.toLowerCase());
        return matcher.find();
    }

    public static String defaultRegex = "\\b(and|exec|insert|select|drop|grant|alter|delete" +
            "|update|count|chr|mid|master|truncate|char|declare|or)\\b|(\\*|;|\\+|'|%)\n";
}
