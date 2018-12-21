package com.dreamcc.mall.common.util;

import com.dreamcc.mall.common.common.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.util
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/6 11:01
 * @Version: V1.0
 */
@Slf4j
public class RedisShardedPoolUtil {
	public static Long expire(String key, int exTime) {
		ShardedJedis jedis = null;
		Long result = null;

		try {
			jedis = RedisShardedPool.getJedis();
			result = jedis.expire(key, exTime);
		} catch (Exception e) {
			log.error("expire key:{} error", key, e);
		} finally {
			RedisShardedPool.closeRedis();
		}
		return result;
	}

	public static String setEx(String key, String value, int exTime) {
		ShardedJedis jedis = null;
		String result = null;

		try {
			jedis = RedisShardedPool.getJedis();
			result = jedis.setex(key, exTime, value);
		} catch (Exception e) {
			log.error("setex key:{} value:{} error", key, value, e);
		} finally {
			RedisShardedPool.closeRedis();
		}
		return result;
	}

	public static String set(String key, String value) {
		ShardedJedis jedis = null;
		String result = null;
		try {
			jedis = RedisShardedPool.getJedis();
			result = jedis.set(key, value);
		} catch (Exception e) {
			log.error("set key:{} value:{} error", key, value, e);
		} finally {
			RedisShardedPool.closeRedis();
		}
		return result;
	}

	public static String getSet(String key, String value) {
		ShardedJedis jedis = null;
		String result = null;

		try {
			jedis = RedisShardedPool.getJedis();
			result = jedis.getSet(key, value);
		} catch (Exception e) {
			log.error("getset key:{} value:{} error", key, value, e);
		} finally {
			RedisShardedPool.closeRedis();
		}
		return result;
	}

	public static String get(String key) {
		ShardedJedis jedis = null;
		String result = null;
		try {
			jedis = RedisShardedPool.getJedis();
			result = jedis.get(key);
		} catch (Exception e) {
			log.error("get key:{} error", key, e);
		} finally {
			RedisShardedPool.closeRedis();
		}
		return result;
	}

	public static Long del(String key) {
		ShardedJedis jedis = null;
		Long result = null;
		try {
			jedis = RedisShardedPool.getJedis();
			result = jedis.del(key);
		} catch (Exception e) {
			log.error("del key:{} error", key, e);
		} finally {
			RedisShardedPool.closeRedis();
		}
		return result;
	}

	public static Long setnx(String key, String value) {
		ShardedJedis jedis = null;
		Long result = null;

		try {
			jedis = RedisShardedPool.getJedis();
			result = jedis.setnx(key, value);
		} catch (Exception e) {
			log.error("setnx key:{} value:{} error", key, value, e);
		} finally {
			RedisShardedPool.closeRedis();
		}
		return result;
	}
}
