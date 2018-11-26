package com.dreamcc.mall.common;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.common
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/8/11 12:39
 * @Version: V1.0
 */
public class Const {

	public static final String CURRENT_USER = "currentUser";

	public static final String EMAIL = "email";

	public static final String USERNAME = "username";

	public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String COOKIE_DOMAIN = "";

	public static final String COOKIE_NAME = "";

	public interface Role {
		int ROLE_CUSTOMER = 0;
		int ROLE_ADMIN = 1;
	}
}
