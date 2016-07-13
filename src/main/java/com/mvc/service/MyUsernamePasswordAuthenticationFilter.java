package com.mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mvc.jcaptcha.CaptchaService;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private CaptchaService captchaService;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String requestCaptcha = request.getParameter("jcaptcha");
		String captchaId = request.getSession().getAttribute("captchaId").toString();
		boolean result = captchaService.validateResponseForID(captchaId, requestCaptcha);
		if (!result) {
			throw new AuthenticationServiceException("输入的验证码有误");
		}

		return super.attemptAuthentication(request, response);
	}

}
