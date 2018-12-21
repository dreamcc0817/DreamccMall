package com.dreamcc.mall.common.common;

import com.dreamcc.mall.common.util.YamlUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.common
 * @Description:
 * @Author: dreamcc
 * @Date: 2018-10-24 17:11
 * @Version: V1.0
 */
public class RedisPool {

	private static JedisPool pool;
	private static Integer maxIdle = Integer.parseInt(YamlUtils.getProperty("redis.max.idle", "20"));
	private static Integer maxTotal = Integer.parseInt(YamlUtils.getProperty("redis.max.total", "20"));
	private static Integer minIdle = Integer.parseInt(YamlUtils.getProperty("redis.min.idle", "20"));
	private static Boolean testOnBorrow = Boolean.parseBoolean(YamlUtils.getProperty("redis.test.borrow", "true"));
	private static Boolean testOnReturn = Boolean.parseBoolean(YamlUtils.getProperty("redis.test.return", "true"));
	private static String redisIp = YamlUtils.getProperty("redis.ip", "");
	private static Integer redisPort = Integer.parseInt(YamlUtils.getProperty("redis.port", ""));

	private static void init() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMinIdle(minIdle);
		config.setTestOnBorrow(testOnBorrow);
		config.setTestOnReturn(testOnReturn);
		config.setBlockWhenExhausted(true);
		pool = new JedisPool(config, redisIp, redisPort, 500 * 4);
	}

	static {
		init();
	}

	public static Jedis getJedis() {
		return pool.getResource();
	}

	public static void returnBrokenResource(Jedis jedis) {
		pool.returnBrokenResource(jedis);
	}

	public static void returnResource(Jedis jedis) {
		pool.returnResource(jedis);
	}
}
