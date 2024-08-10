package org.promisedland.minecraft.eraauth.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis {
    private static final int EXPIRATION_TIME = 3600;
    private static JedisPool jedisPool;
    public static void initRedis() throws Exception{
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPool = new JedisPool(jedisPoolConfig,Config.getConfig("redis.ip"), Integer.parseInt(Config.getConfig("redis.port")));
    }
    public static void recordLogin(String username) {
        try (Jedis jedis = jedisPool.getResource()) {
            // 使用玩家ID作为key，当前时间戳作为value
            String key = "player:login:" + username;
            String value = String.valueOf(System.currentTimeMillis());
            jedis.setex(key, EXPIRATION_TIME, value);
        }
    }

    public static boolean isLoggedIn(String username) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "player:login:" + username;
            return jedis.exists(key);
        }
    }

    public static void logout(String username) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "player:login:" + username;
            jedis.del(key);
        }
    }
}
