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

import io.hanbings.cynops.database.interfaces.SqliteData;
import io.hanbings.cynops.database.interfaces.SqliteDataTable;

import java.lang.reflect.Field;
import java.util.Locale;

@SuppressWarnings("unused")
public class SqliteSqlBuilder {
    public static String createTable(Class<?> table) {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ");
        if (table.isAnnotationPresent(SqliteDataTable.class)) {
            // 获取表名
            builder.append(table.getAnnotation(SqliteDataTable.class).table()).append(" (");
            Field[] fields = table.getDeclaredFields();
            // 使用一个 flag 绕过第一次有效字段循环 以避免错误添加的分隔符
            boolean isAdd = false;
            boolean isHavePrimary = false;
            // 扫描字段
            for (Field field : fields) {
                // 扫描有效字段
                if (field.isAnnotationPresent(SqliteData.class)) {
                    // 添加分隔符
                    if (isAdd) {
                        builder.append(", ");
                    } else {
                        isAdd = true;
                    }
                    // 获取注解
                    SqliteData data = field.getAnnotation(SqliteData.class);
                    // 字段名 类型 主键 唯一约束 非空
                    if (table.getAnnotation(SqliteDataTable.class).isToUpper()) {
                        builder.append(field.getName().toUpperCase(Locale.ROOT));
                    } else {
                        builder.append(field.getName());
                    }
                    // 添加类型
                    builder.append(" ").append(data.type());
                    // 判断是否为主键 当然 只允许一个主键
                    if (!isHavePrimary) {
                        if (data.isPrimaryKey()) {
                            builder.append(" PRIMARY KEY");
                            isHavePrimary = true;
                        }
                    }
                    // 唯一约束
                    if (data.isUnique()) {
                        builder.append(" UNIQUE");
                    }
                    // 非空
                    if (data.isNotNull()) {
                        builder.append(" NOT NULL");
                    }
                }
            }
        }
        return builder.append(");").toString();
    }

    public static String deleteTable(Class<?> table) {
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE ");
        if (table.isAnnotationPresent(SqliteDataTable.class)) {
            builder.append(table.getAnnotation(SqliteDataTable.class).table());
        }
        return builder.append(";").toString();
    }
}
