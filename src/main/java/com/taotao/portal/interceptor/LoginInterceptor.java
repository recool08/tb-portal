package com.taotao.portal.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.portal.service.impl.UserServiceImpl;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//在Handler执行之前处理
		//判断用户是否登录
		//从cookie中取token
		/*
		Cookie killMyCookie = new Cookie("TT_TOKEN", "");
        killMyCookie.setMaxAge(0);
//        killMyCookie.setPath("/");
        response.addCookie(killMyCookie);
		*/
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		
		// 解决SpringBoot中拦截器无法注入Service的问题
		if (userService == null) {
			System.out.println("userService is null!!!"); 
	        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
	        userService = (UserServiceImpl) factory.getBean("userServiceImpl"); 
	    } 
		
		System.out.println("==============================");
		System.out.println(userService);
		System.out.println(token);
		System.out.println("==============================");
		System.out.println(request.getRequestURL());
		
		//根据token换取用户信息，调用sso系统的接口。
		TbUser user = userService.getUserByToken(token);
		//取不到用户信息
		if (null == user) {
			//跳转到登录页面，把用户请求的url作为参数传递给登录页面。
			response.sendRedirect(userService.SSO_DOMAIN_BASE_USRL + userService.SSO_PAGE_LOGIN 
					+ "?redirect=" + request.getRequestURL());
			//返回false
			return false;
		}
		//取到用户信息，放行
		//把用户信息放入Request
		request.setAttribute("user", user);
		//返回值决定handler是否执行。true：执行，false：不执行。
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// handler执行之后，返回ModelAndView之前

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 返回ModelAndView之后。
		//响应用户之后。

	}

}
