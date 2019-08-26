package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.ResultInfoVO;
import com.example.demo.util.ResultMessage;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author shieldofzues
 * @Description 用户管理
 * @Date 16:50 2016/5/23
 **/
@Api("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("新增用户")
    @ApiImplicitParam(name = "user", value = "单个用户", required = true, dataType = "User")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"), // 响应对应编码的描述
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    /**
     *  @Validated({Severitys.Update.class}) 可以做类似这种的分组校验
     */

    @PostMapping("/insertUser")
    public ResultInfoVO insertSelective(@RequestBody @ApiParam(value = "新增用户实体") @Validated User user) {
        if (userService.insertSelective(user) > 0) {
            return new ResultInfoVO(ResultMessage.SUCCESS.code, null, ResultMessage.SUCCESS.message);
        } else {
            return new ResultInfoVO(ResultMessage.ERROR.code, null, ResultMessage.ERROR.message);
        }
    }

}
