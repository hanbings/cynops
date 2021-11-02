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

package io.hanbings.cynops.database.interfaces;

import java.lang.annotation.*;

/**
 * 用于标记实体类的注解 <br>
 * 被此注解标记后 使用对应数据库的 sql builder 传入实体类即可自动生成 sql 语句 <br>
 * 有需要时 使用 sql check 来检查恶意参数 <br>
 * <p>
 * 注解有三个参数 <br>
 * table - 字符串 数据表名
 * isToUpper - 布尔 是否需要全部转为大写 默认为 false 即保持字段原本的大小写
 * isCover - 布尔 是否覆盖已有的数据表 默认为 false
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SuppressWarnings("unused")
public @interface SqliteDataTable {
    String table();

    boolean isToUpper() default false;

    boolean isCover() default false;
}
