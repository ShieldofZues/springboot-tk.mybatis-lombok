package com.example.demo.controller;

import com.github.pagehelper.PageInfo;
import com.example.demo.service.UserService;
import com.example.demo.util.Md5Util;
import com.example.demo.util.ResultInfoVO;
import com.example.demo.util.ResultMessage;
import com.example.demo.util.SessionUserUtil;
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
 * @see com.example.demo.controller <br>
 */
@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResultInfoVO userLogin(@RequestBody User User, HttpServletRequest request) {
		if (null == User || StringUtils.isEmpty(User.getAdminPassword()) || StringUtils.isEmpty(User.getTelephoneNumber())) {
			return new ResultInfoVO(ResultMessage.ERROR.code, null, ResultMessage.USR_LOGIN_ERROR.message);
		} else {
			User admin = UserService.selectOneUserByPhone(User.getTelephoneNumber());
			if (null != admin) {
				if (null != admin.getAdminPassword() && Md5Util.validPassword(User.getAdminPassword(), admin.getAdminPassword())) {
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
	public ResultInfoVO saveOneUser(@RequestBody User User) {
		if (null == User || StringUtils.isEmpty(User.getAdminPassword()) || StringUtils.isEmpty(User.getTelephoneNumber())) {
			return new ResultInfoVO(ResultMessage.FAIL.code, null, ResultMessage.FAIL.message);
		} else {
			if (UserService.isExistUser(User.getTelephoneNumber()) > 0) {
				return new ResultInfoVO(ResultMessage.USR_REPE_USER.code, null, ResultMessage.USR_REPE_USER.message);
			} else {
				if (UserService.saveOneUser(User) > 0) {
					return new ResultInfoVO(ResultMessage.SUCCESS.code, null, ResultMessage.SUCCESS.message);
				} else {
					return new ResultInfoVO(ResultMessage.ERROR.code, null, ResultMessage.ERROR.message);
				}
			}
		}
	}

	@PutMapping("/updateOneUser")
	public ResultInfoVO updateOneUser(@RequestBody User User) {
		if (null == User  || StringUtils.isEmpty(User.getUserId())) {
			return new ResultInfoVO(ResultMessage.FAIL.code, null, ResultMessage.FAIL.message);
		} else {
			if ( !StringUtils.isEmpty(User.getTelephoneNumber()) && UserService.isExistUser(User.getTelephoneNumber()) > 0) {
				return new ResultInfoVO(ResultMessage.USR_REPE_USER.code, null, ResultMessage.USR_REPE_USER.message);
			} else {
				if (UserService.updateOneUser(User) > 0) {
					return new ResultInfoVO(ResultMessage.SUCCESS.code, null, ResultMessage.SUCCESS.message);
				} else {
					return new ResultInfoVO(ResultMessage.ERROR.code, null, ResultMessage.ERROR.message);
				}
			}
		}

	}

	@GetMapping("/selectAllUser")
	public ResultInfoVO selectAllUser(HttpServletRequest request) {
		PageInfo<User> UserPageInfo = UserService.selectAllUser(request.getParameter("pageNum"), request.getParameter("pageSize"), request.getParameter("startDate"), request.getParameter("endDate"));
		if (null != UserPageInfo && !StringUtils.isEmpty(UserPageInfo.getList())) {
			return new ResultInfoVO(ResultMessage.SUCCESS.code, UserPageInfo, ResultMessage.SUCCESS.message);
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
