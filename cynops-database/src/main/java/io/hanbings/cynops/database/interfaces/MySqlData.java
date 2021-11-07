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
 * 被 DataTable 注解后 将一个字段标记为被扫描字段 <br>
 * 即使用此注解标记将被转换为数据库对象 <br>
 * <p>
 * 注解参数
 * type - 字符串 数据库的类型 默认为 blob
 * isNotNull - 布尔 是否不为空 默认为 false
 * isPrimaryKey - 布尔 是否为主键 默认为 false
 * isUnique - 是否添加唯一约束 默认为 false
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@SuppressWarnings("unused")
public @interface MySqlData {
    String type() default MySqlDataType.BLOB;

    boolean isNotNull() default false;

    boolean isPrimaryKey() default false;

    boolean isUnique() default false;

    boolean isAutoincrement() default false;
}
