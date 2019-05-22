package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.ResultInfoVO;
import com.example.demo.util.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@ApiOperation("新增用户")
	@ApiImplicitParam(name = "user", value = "单个用户", dataType = "User")
	@PostMapping("/insertUser")
	public ResultInfoVO insertSelective(@RequestBody User user) {
		if (userService.insertSelective(user) > 0) {
			return new ResultInfoVO(ResultMessage.SUCCESS.code, null, ResultMessage.SUCCESS.message);
		} else {
			return new ResultInfoVO(ResultMessage.ERROR.code, null, ResultMessage.ERROR.message);
		}
	}

}
