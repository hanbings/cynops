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

package io.hanbings.cynops.database.sqlite;

import io.hanbings.cynops.database.interfaces.*;

import java.lang.reflect.Field;
import java.util.Locale;

@SuppressWarnings("unused")
public class SqliteSqlBuilder {
    /**
     * 创建一张数据表
     * @param table 表实体类
     * @return sql 语句
     */
    public static String createTable(Class<?> table) {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ");
        // 获取目标表名
        if (table.isAnnotationPresent(SqliteDataTable.class)) {
            builder.append(table.getAnnotation(SqliteDataTable.class).table());
            builder.append("(");
            // 获取字段
            Field[] fields = table.getDeclaredFields();
            boolean isSetPrimaryKey = false;
            for (int count = 0; count < fields.length - 1; count++) {
                if (count != 0) {
                    // 最后添加分隔符号
                    builder.append(", ");
                }
                // 先排除 NoFollow 的字段
                if (fields[count].isAnnotationPresent(NoFollow.class)) {
                    continue;
                }
                // 字段名
                if (table.getAnnotation(SqliteDataTable.class).upper()) {
                    builder.append((fields[count].getName()).toUpperCase(Locale.ROOT));
                } else {
                    builder.append(fields[count].getName());
                }
                // 获取类型并对应到 sql 中
                builder.append(" ");
                builder.append(checkType(fields[count].getType().getTypeName()));
                // 如果是主键则添加 PRIMARY KEY
                if (!isSetPrimaryKey) {
                    if (fields[count].isAnnotationPresent(PrimaryKey.class)) {
                        builder.append(" PRIMARY KEY");
                        isSetPrimaryKey = true;
                    }
                }
                // 如果有唯一约束则添加 UNIQUE
                if (fields[count].isAnnotationPresent(Unique.class)) {
                    builder.append(" UNIQUE");
                }
                // 如果是非空则添加 NOT NULL
                if (fields[count].isAnnotationPresent(NotNull.class)) {
                    builder.append(" NOT NULL");
                }
            }
            builder.append(");");
        }
        return builder.toString();
    }

    private static String checkType(String type) {
        if (type.contains("String")) {
            return "TEXT";
        }
        if (type.contains("boolean")) {
            return "REAL";
        }
        return "BLOB";
    }

    public static String deleteTable(Class<?> table) {
        if (table.isAnnotationPresent(SqliteDataTable.class)) {
            return "DROP TABLE " + table.getAnnotation(SqliteDataTable.class).table();
        }
        return null;
    }

    public static String deleteTable(String table) {
        return "DROP TABLE " + table;
    }
}
