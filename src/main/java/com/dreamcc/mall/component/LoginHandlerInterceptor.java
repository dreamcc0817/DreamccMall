package com.dreamcc.mall.component;

import com.dreamcc.mall.common.Const;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.component
 * @Description: login interceptor
 * @Author: dreamcc
 * @Date: 2018/8/20 21:53
 * @Version: V1.0
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object user = request.getSession().getAttribute(Const.USERNAME);
		if (user == null) {
			request.getSession().setAttribute("msg", "You do not have permission,please Login");
			request.getRequestDispatcher("/login.html");
			return true;
		} else {
			return true;
		}

	}
}
