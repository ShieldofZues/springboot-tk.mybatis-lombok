package com.example.demo.service;

import com.example.demo.entity.User;

/**
 * @Author shieldofzues
 * @Description UserService
 * @Date 10:35 2016/5/23
 *
 **/
public interface UserService {
	int insertSelective(User user);
}
