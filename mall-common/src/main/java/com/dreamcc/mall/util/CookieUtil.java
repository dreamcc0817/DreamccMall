package com.dreamcc.mall.util;

import com.dreamcc.mall.common.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.util
 * @Description:
 * @Author: dreamcc
 * @Date: 2018-10-25 10:18
 * @Version: V1.0
 */
@Slf4j
public class CookieUtil {

	public static String readLoginToken(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				log.info("read cookieName:{},cookieValue:{}", cookie.getName(), cookie.getValue());
				if (StringUtils.equals(cookie.getName(), Const.COOKIE_NAME)) {
					log.info("return cookieName:{},cookieValue:{}", cookie.getName(), cookie.getValue());
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static void writeLoginToken(HttpServletResponse response, String token) {
		Cookie cookie = new Cookie(Const.COOKIE_NAME, token);
		cookie.setDomain(Const.COOKIE_DOMAIN);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 60 * 24 * 365);
		log.info("write cookieName:{},cookieValue:{}", cookie.getName(), cookie.getValue());
		response.addCookie(cookie);
	}

	public static void delLoginToken(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (StringUtils.equals(cookie.getName(), Const.COOKIE_NAME)) {
					cookie.setDomain(Const.COOKIE_DOMAIN);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					log.info("del cookieName:{},cookieValue:{}", cookie.getName(), cookie.getValue());
					response.addCookie(cookie);
					return;
				}
			}
		}
	}

}
