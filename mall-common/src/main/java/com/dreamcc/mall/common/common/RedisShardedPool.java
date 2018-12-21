package com.dreamcc.mall.common.common;

import com.dreamcc.mall.common.util.YamlUtils;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.common
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/6 11:13
 * @Version: V1.0
 */
public class RedisShardedPool {
	private static ShardedJedisPool pool;
	private static Integer maxTotal = Integer.parseInt(YamlUtils.getProperty("", "20"));
	private static Integer maxIdle = Integer.parseInt(YamlUtils.getProperty("", "20"));
	private static Integer minIdle = Integer.parseInt(YamlUtils.getProperty("", "20"));

	private static Boolean testOnBorrow = Boolean.parseBoolean(YamlUtils.getProperty("", ""));
	private static Boolean testOnReturn = Boolean.parseBoolean(YamlUtils.getProperty("", ""));

	private static String redis1Ip = YamlUtils.getProperty("", "");
	private static Integer redis1Port = Integer.parseInt(YamlUtils.getProperty("", ""));
	private static String redis2Ip = YamlUtils.getProperty("", "");
	private static Integer redis2Port = Integer.parseInt(YamlUtils.getProperty("", ""));

	static {
		initPool();
	}

	private static void initPool() {
		JedisPoolConfig config = new JedisPoolConfig();

		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);

		config.setTestOnBorrow(testOnBorrow);
		config.setTestOnReturn(testOnReturn);

		config.setBlockWhenExhausted(true);//连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。

		JedisShardInfo info1 = new JedisShardInfo(redis1Ip, redis1Port, 1000 * 2);

		JedisShardInfo info2 = new JedisShardInfo(redis2Ip, redis2Port, 1000 * 2);

		List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>(2);

		jedisShardInfoList.add(info1);
		jedisShardInfoList.add(info2);

		pool = new ShardedJedisPool(config, jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);

	}

	public static ShardedJedis getJedis() {
		return pool.getResource();
	}

	public static void closeRedis() {
		pool.close();
	}
}
