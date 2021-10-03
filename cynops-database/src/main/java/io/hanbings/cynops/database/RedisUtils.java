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
