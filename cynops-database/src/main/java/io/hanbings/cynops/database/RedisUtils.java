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

package io.hanbings.cynops.database;

import redis.clients.jedis.Jedis;

@SuppressWarnings("unused")
public class RedisUtils {
    /**
     * 使用默认端口 链接 无认证 redis
     *
     * @param address 地址
     * @return Redis 连接
     */
    @Deprecated
    public static Jedis getRedisConnection(String address) {
        return new Jedis(address);
    }

    /**
     * 获取 Redis 连接 无用户名认证
     *
     * @param address  地址
     * @param port     端口
     * @param password 密码
     * @return Redis 连接
     */
    public static Jedis getRedisConnection(String address, int port, String password) {
        Jedis jedis = new Jedis(address, port);
        jedis.auth(password);
        return jedis;
    }

    /**
     * 获取 Redis 连接
     *
     * @param address  地址
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return Redis 连接
     */
    public static Jedis getRedisConnection(String address, int port, String username, String password) {
        Jedis jedis = new Jedis(address, port);
        jedis.auth(username, password);
        return jedis;
    }
}
