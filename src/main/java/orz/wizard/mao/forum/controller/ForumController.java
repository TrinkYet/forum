package orz.wizard.mao.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import orz.wizard.mao.forum.entity.User;
import orz.wizard.mao.forum.service.UserService;


@Controller

public class ForumController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/*.json")
	@ResponseBody
	public User displayUser(){
		return userService.getUser(1);
	}
	
}
