package com.example.demo.service;

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
public interface UserService {
	PageInfo<User> selectAllUser(String pageNum, String pageSize, String startDate, String endDate);
}
