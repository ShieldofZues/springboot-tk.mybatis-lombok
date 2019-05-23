package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author shieldofzues
 * @Description UserServiceImpl
 * @Date 10:34 2016/5/23
 *
 **/
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public int insertSelective(User user) {
		return userMapper.insertSelective(user);
	}

}
