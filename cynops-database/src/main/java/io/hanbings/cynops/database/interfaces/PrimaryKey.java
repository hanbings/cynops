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
 * 被 DataTable 注解后 希望其中某一个字段为主键 <br>
 * 可以使用此注解标记 <br>
 * 若没有任意字段被注解标记 将自动选择没有 NoFollow 注解的一个字段作为主键  <br>
 * 若错误的设置了多个主键 将选择被扫描到的第一个字段作为主键
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@SuppressWarnings("unused")
public @interface PrimaryKey {
}
