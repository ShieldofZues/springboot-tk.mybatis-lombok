package com.example.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.example.demo.entity.AdminUser;
import com.example.demo.mapper.AdminUserMapper;
import com.example.demo.service.AdminUserService;
import com.example.demo.util.DateSqlUtil;
import com.example.demo.util.Md5Util;
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
public class AdminUserServiceImpl implements AdminUserService {
	@Autowired
	private AdminUserMapper adminUserMapper;


	@Override
	public AdminUser selectOneUserByPhone(String telephoneNumber) {
		Example example = new Example(AdminUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("telephoneNumber", telephoneNumber);
		return adminUserMapper.selectOneByExample(example);
	}

	@Override
	public int saveOneUser(AdminUser adminUser) {
		adminUser.setAdminPassword(Md5Util.getEncryptedPwd(adminUser.getAdminPassword()));
		return adminUserMapper.insertSelective(adminUser);
	}

	@Override
	public int isExistUser(String telephoneNumber) {
		Example example = new Example(AdminUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("telephoneNumber", telephoneNumber);
		return adminUserMapper.selectCountByExample(example);
	}

	@Override
	public int updateOneUser(AdminUser adminUser) {
		if (StringUtil.isNotEmpty(adminUser.getAdminPassword())) {
			adminUser.setAdminPassword(Md5Util.getEncryptedPwd(adminUser.getAdminPassword()));
		}
		return adminUserMapper.updateByPrimaryKeySelective(adminUser);
	}

	@Override
	public PageInfo<AdminUser> selectAllUser(String pageNum, String pageSize, String startDate, String endDate) {
		if (StringUtils.isEmpty(pageNum)) {
			pageNum = "1";
		}
		if (StringUtils.isEmpty(pageSize)) {
			pageSize = "10";
		}
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		Example example = new Example(AdminUser.class);
		Example.Criteria criteria = example.createCriteria();
		DateSqlUtil.dateSqlUtil(startDate, endDate, criteria);
		example.setOrderByClause("gmt_create desc");
		return new PageInfo<AdminUser>(adminUserMapper.selectByExample(example));
	}
}
