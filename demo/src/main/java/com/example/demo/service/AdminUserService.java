package com.example.demo.service;

import com.example.demo.entity.AdminUser;
import com.github.pagehelper.PageInfo;

/**
 * <Description> <br>
 *
 * @author zlr<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/05/06 14:51 <br>
 * @see com.example.demo.service <br>
 */
public interface AdminUserService {
	AdminUser selectOneUserByPhone(String telephoneNumber);

	int saveOneUser(AdminUser adminUser);

	int isExistUser(String telephoneNumber);

	int updateOneUser(AdminUser adminUser);

	PageInfo<AdminUser> selectAllUser(String pageNum, String pageSize, String startDate, String endDate);
}
