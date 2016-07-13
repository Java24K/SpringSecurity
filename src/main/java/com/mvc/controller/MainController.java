package com.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	protected final transient Log logger = LogFactory.getLog(MainController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest h, @RequestParam(value = "error", required = false) boolean error,
			ModelMap model) {
		if (error == true) {
			// Assign an error message
			model.put("error", "用户名或密码错误!");
		}
		return "login";
	}

	// 默认请求根目录
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "welcom";
	}

}
