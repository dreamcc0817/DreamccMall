package com.dreamcc.mall.util;

import com.dreamcc.mall.common.RedisPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.util
 * @Description:
 * @Author: dreamcc
 * @Date: 2018-10-24 16:18
 * @Version: V1.0
 */
@Slf4j
public class RedisPoolUtil {
	/**
	 * set expire time
	 *
	 * @param key
	 * @param exTime
	 * @return
	 */
	public static Long expire(String key, int exTime) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.expire(key, exTime);
		} catch (Exception e) {
			log.error("expire key:{} value:{} error", key, e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		return result;
	}

	public static String setEx(String key, String value, int exTime) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.setex(key, exTime, value);
		} catch (Exception e) {
			log.error("setex key:{} value:{} error", key, value, e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		return result;
	}

	public static String set(String key, String value) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.set(key, value);
		} catch (Exception e) {
			log.error("set key:{} value:{} error", key, value, e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}

	public static String get(String key) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.get(key);
		} catch (Exception e) {
			log.error("get key:{} error", key, e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}

	public static Long del(String key) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.del(key);
		} catch (Exception e) {
			log.error("del key:{} error", key, e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}
}
