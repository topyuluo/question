package com.yuluo.question.controller;

import com.alibaba.fastjson.JSONObject;
import com.yuluo.question.entity.Person;
import org.springframework.web.bind.annotation.*;

/**
 * 定义控制器
 *
 * @author: YuLuo
 * @date: 2019/4/5
 * @since: JDK 1.8
 * @version: v1.0
 */


@RestController
@RequestMapping("/question")
public class QuestionController {

	@PostMapping("/hello")
	public String hello(@RequestBody Person person) {
		System.out.println(person.toString());
		return "hello world ！";
	}

	@PostMapping("/param")
	public String param(@RequestParam("name") String name){
		System.out.println(name);
		return "param";
	}

	@PostMapping("/attribute")
	public String attribute(@ModelAttribute("json") JSONObject jsonObject) {
		System.out.println(jsonObject.toJSONString());
		return "attribute";
	}
}
