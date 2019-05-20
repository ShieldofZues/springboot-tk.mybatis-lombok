package com.example.demo.controller;

import com.github.pagehelper.PageInfo;
import com.lanxum.platform.entity.AdminUser;
import com.lanxum.platform.service.AdminUserService;
import com.lanxum.platform.util.Md5Util;
import com.lanxum.platform.util.ResultInfoVO;
import com.lanxum.platform.util.ResultMessage;
import com.lanxum.platform.util.SessionUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <Description> <br>
 *
 * @author zlr<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/05/06 14:52 <br>
 * @see com.lanxum.platform.controller <br>
 */
@RestController
@RequestMapping("/adminUser")
public class AdminUserController {
	@Autowired
	private AdminUserService adminUserService;

	@PostMapping("/login")
	public ResultInfoVO userLogin(@RequestBody AdminUser adminUser, HttpServletRequest request) {
		if (null == adminUser || StringUtils.isEmpty(adminUser.getAdminPassword()) || StringUtils.isEmpty(adminUser.getTelephoneNumber())) {
			return new ResultInfoVO(ResultMessage.ERROR.code, null, ResultMessage.USR_LOGIN_ERROR.message);
		} else {
			AdminUser admin = adminUserService.selectOneUserByPhone(adminUser.getTelephoneNumber());
			if (null != admin) {
				if (null != admin.getAdminPassword() && Md5Util.validPassword(adminUser.getAdminPassword(), admin.getAdminPassword())) {
					SessionUserUtil.setUser(admin, request);
					return new ResultInfoVO(ResultMessage.SUCCESS.code, admin, ResultMessage.SUCCESS.message);
				} else {
					return new ResultInfoVO(ResultMessage.USR_PASS_ERROR.code, null, ResultMessage.USR_PASS_ERROR.message);
				}
			} else {
				return new ResultInfoVO(ResultMessage.USR_LOGIN_ERROR.code, null, ResultMessage.USR_LOGIN_ERROR.message);
			}
		}
	}

	@PostMapping("/saveOneUser")
	public ResultInfoVO saveOneUser(@RequestBody AdminUser adminUser) {
		if (null == adminUser || StringUtils.isEmpty(adminUser.getAdminPassword()) || StringUtils.isEmpty(adminUser.getTelephoneNumber())) {
			return new ResultInfoVO(ResultMessage.FAIL.code, null, ResultMessage.FAIL.message);
		} else {
			if (adminUserService.isExistUser(adminUser.getTelephoneNumber()) > 0) {
				return new ResultInfoVO(ResultMessage.USR_REPE_USER.code, null, ResultMessage.USR_REPE_USER.message);
			} else {
				if (adminUserService.saveOneUser(adminUser) > 0) {
					return new ResultInfoVO(ResultMessage.SUCCESS.code, null, ResultMessage.SUCCESS.message);
				} else {
					return new ResultInfoVO(ResultMessage.ERROR.code, null, ResultMessage.ERROR.message);
				}
			}
		}
	}

	@PutMapping("/updateOneUser")
	public ResultInfoVO updateOneUser(@RequestBody AdminUser adminUser) {
		if (null == adminUser  || StringUtils.isEmpty(adminUser.getAdminUserId())) {
			return new ResultInfoVO(ResultMessage.FAIL.code, null, ResultMessage.FAIL.message);
		} else {
			if ( !StringUtils.isEmpty(adminUser.getTelephoneNumber()) && adminUserService.isExistUser(adminUser.getTelephoneNumber()) > 0) {
				return new ResultInfoVO(ResultMessage.USR_REPE_USER.code, null, ResultMessage.USR_REPE_USER.message);
			} else {
				if (adminUserService.updateOneUser(adminUser) > 0) {
					return new ResultInfoVO(ResultMessage.SUCCESS.code, null, ResultMessage.SUCCESS.message);
				} else {
					return new ResultInfoVO(ResultMessage.ERROR.code, null, ResultMessage.ERROR.message);
				}
			}
		}

	}

	@GetMapping("/selectAllUser")
	public ResultInfoVO selectAllUser(HttpServletRequest request) {
		PageInfo<AdminUser> adminUserPageInfo = adminUserService.selectAllUser(request.getParameter("pageNum"), request.getParameter("pageSize"), request.getParameter("startDate"), request.getParameter("endDate"));
		if (null != adminUserPageInfo && !StringUtils.isEmpty(adminUserPageInfo.getList())) {
			return new ResultInfoVO(ResultMessage.SUCCESS.code, adminUserPageInfo, ResultMessage.SUCCESS.message);
		} else {
			return new ResultInfoVO(ResultMessage.SEC_SUCCESS.code, null, ResultMessage.SEC_SUCCESS.message);
		}
	}

	@PostMapping("/signOut")
	public ResultInfoVO<String> loginOut(HttpServletRequest request) {
		SessionUserUtil.removeUser(request);
		return new ResultInfoVO(ResultMessage.SUCCESS.code, null, ResultMessage.SUCCESS.message);

	}
}
