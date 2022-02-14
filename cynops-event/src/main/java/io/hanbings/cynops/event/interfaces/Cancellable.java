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
 * 一个 Event 接口, 实现该接口标识事件可以取消 <br>
 * 请注意这一个设计, 取消并不意味这事件会在 setCancelled(true) 后停止向下一个事件处理器传播 <br>
 * 如果事件处理器的注解中 ignoreCancelled 值为 true, 事件处理器将会被正常触发 <br>
 * 反之则不会被触发, 完全阻断事件请使用 io.hanbings.cynops.event.interfaces.Blockable 接口
 */
@SuppressWarnings("unused SpellCheckingInspection")
public interface Cancellable {
    void setCancelled(boolean cancel);
    boolean isCancelled();
}
