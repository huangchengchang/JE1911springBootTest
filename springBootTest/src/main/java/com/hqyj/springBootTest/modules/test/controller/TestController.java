package com.hqyj.springBootTest.modules.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hqyj.springBootTest.modules.test.vo.ConfigBean;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private final static Logger LOGGER =LoggerFactory.getLogger(TestController.class);
	
	@Value("${server.port}")
	private int port;
	@Value("${com.hqyj.name}")
	private String name;
	@Value("${com.hqyj.age}")
	private int age;
	@Value("${com.hqyj.desc}")
	private String desc;
	@Value("${com.hqyj.random}")
	private String random;
	@Autowired
	private ConfigBean configbean;
	
	@RequestMapping("/config")
	@ResponseBody
	public String configTest() {
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(port).append("--").
			append(name).append("--").
			append(age).append("--").
			append(desc).append("--").
			append(random).append("</br>");
		sbBuffer.append(configbean.getName()).append("--").
			append(configbean.getAge()).append("--").
			append(configbean.getDesc()).append("--").
			append(configbean.getRandom()).append("</br>");
		return sbBuffer.toString();
		
	}
	@RequestMapping("/log")
	@ResponseBody
	public String logTest() {
		LOGGER.trace("trace");
		LOGGER.debug("debug");
		LOGGER.info("info");
		LOGGER.warn("warn");
		LOGGER.error("error");
		return "test logaaaaaaaaabbbbbbbbbaaaaaaaaa";
	}
	
	@RequestMapping("/demo1")
	@ResponseBody
	public String demoDesc() {
		return "helloWorld!";
	}
	
	@RequestMapping("/index")
	public String testIndexPage(ModelMap modelMap) {
		modelMap.addAttribute("template", "test/index");
		return "index";
	}
}
