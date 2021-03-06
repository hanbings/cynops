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

package io.hanbings.cynops.event;

/**
 * 很高兴的告诉您, 我在抄bukkit的同时 <br>
 * 把这个反人类设计一起抄过来了 <br>
 * 这个枚举使用在 @EventHandler 注解的 priority 参数中 <br>
 * 序号越大的优先级越低, 请不要被字面意思迷惑 <br>
 * 简单来说, LOWEST 优先级最高, 是第一个被触发的等级, MONITOR 优先级最低, 是最后一个被触发的等级 <br><br>
 * 1. LOWEST <br>
 * 2. LOW <br>
 * 3. NORMAL (default) <br>
 * 4. HIGH <br>
 * 5. HIGHEST <br>
 * 6. MONITOR <br>
 */
@SuppressWarnings("unused")
public enum EventPriority {
    LOWEST,
    LOW,
    NORMAL,
    HIGH,
    HIGHEST,
    MONITOR
}
