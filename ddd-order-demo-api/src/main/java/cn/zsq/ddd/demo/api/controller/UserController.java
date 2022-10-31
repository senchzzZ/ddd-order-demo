package cn.zsq.ddd.demo.api.controller;

import cn.zsq.ddd.demo.application.convertor.UserConvertor;
import cn.zsq.ddd.demo.application.pojo.cmd.UserCreateCmd;
import cn.zsq.ddd.demo.application.pojo.query.UserQuery;
import cn.zsq.ddd.demo.application.pojo.response.UserResponse;
import cn.zsq.ddd.demo.application.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {


	@Resource
	private UserService userService;

	@PostMapping(name = "/create")
    public UserResponse create(UserCreateCmd cmd) {
		return UserConvertor.convertToResponse(userService.createUser(cmd));
	}

	@PostMapping(name = "/find")
	public UserResponse find(UserQuery query) {
		return UserConvertor.convertToResponse(userService.findById(query.getId()));
	}
}