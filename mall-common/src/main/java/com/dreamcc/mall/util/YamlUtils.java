package com.dreamcc.mall.util;

import org.apache.commons.lang3.StringUtils;
import org.ho.yaml.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.util
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/8/13 17:22
 * @Version: V1.0
 */
public class YamlUtils {

	private static Logger logger = LoggerFactory.getLogger(YamlUtils.class);

	private static Map map;



	static{
		File config = new File("src/main/resources/application.yml");
		try {
			map = Yaml.loadType(config, HashMap.class);
		} catch (FileNotFoundException e) {
			logger.error("load properties exception",e);
		}
	}

	/**
	 * get system properties
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(String key,String defaultValue){
		String value = (String) map.get(key);
		if(StringUtils.isBlank(value)){
			value = defaultValue;
		}
		return value;
	}
}
