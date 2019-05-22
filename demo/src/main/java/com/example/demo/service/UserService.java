package com.example.demo.service;

import com.example.demo.entity.User;

/**
 * <Description> <br>
 *
 * @author shieldofzues<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/05/06 14:51 <br>
 * @see com.example.demo.service <br>
 */
public interface UserService {
	int insertSelective(User user);
}
