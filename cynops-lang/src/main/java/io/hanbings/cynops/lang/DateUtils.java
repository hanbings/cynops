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

import java.util.Date;

/**
 * 日期工具类
 * 封装 Date 提供 日期相加减 格式化 特殊日期 等
 */
@SuppressWarnings("unused")
public class DateUtils {
    /**
     * 获取当前的时间
     *
     * @return 返回当前的时间
     */
    public static Date getNowDate() {
        return new Date();
    }
}
