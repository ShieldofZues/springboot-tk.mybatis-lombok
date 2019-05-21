package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.DateSqlUtil;
import com.example.demo.util.Md5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import tk.mybatis.mapper.entity.Example;

/**
 * <Description> <br>
 *
 * @author zlr<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/05/06 14:51 <br>
 * @see com.example.demo.service.impl <br>
 */
@Service
@Validated
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;


	@Override
	public User selectOneUserByPhone(String telephoneNumber) {
		Example example = new Example(User.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("telephoneNumber", telephoneNumber);
		return userMapper.selectOneByExample(example);
	}
	@Override
	public PageInfo<User> selectAllUser(String pageNum, String pageSize, String startDate, String endDate) {
		if (StringUtils.isEmpty(pageNum)) {
			pageNum = "1";
		}
		if (StringUtils.isEmpty(pageSize)) {
			pageSize = "10";
		}
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		Example example = new Example(User.class);
		Example.Criteria criteria = example.createCriteria();
		DateSqlUtil.dateSqlUtil(startDate, endDate, criteria);
		example.setOrderByClause("gmt_create desc");
		return new PageInfo<User>(UserMapper.selectByExample(example));
	}
}
