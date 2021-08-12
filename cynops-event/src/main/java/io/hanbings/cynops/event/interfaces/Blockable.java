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

package io.hanbings.cynops.event.interfaces;

/**
 * 一个 Event 接口, 实现该接口标识事件可以阻断 <br>
 * 使用 setBlocked(true) 来完全阻断事件, 即使事件处理器的注解中 ignoreCanceled 值为 false <br>
 * 事实上, 在 setBlocked(true) 后其他优先级比当前事件处理器低的处理器将被忽略, 直接结束当前一次 callEvent <br>
 * 如果不是必要的, 仍推荐使用 io.hanbings.cynops.event.interfaces.Cancellable 接口
 */
@SuppressWarnings("unused SpellCheckingInspection")
public interface Blockable {
    void setBlocked(boolean block);
    boolean isBlocked();
}
