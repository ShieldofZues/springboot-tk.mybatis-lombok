package com.example.demo.exception;


import com.example.demo.util.ResultInfoVO;
import com.example.demo.util.ResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Author shieldofzues
 * @Description 统一处理
 * @Date 11:10 2016/5/23
 *
 **/

@ControllerAdvice
public class GlobalExceptionHandler {
	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 判断
	 */
	public static boolean isAjax(HttpServletRequest httpRequest) {
		return (httpRequest.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest"
				.equals(httpRequest.getHeader("X-Requested-With").toString()));
	}
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResultInfoVO errorHandler(HttpServletRequest reqest,
									 HttpServletResponse response, Exception e) throws Exception {
		ResultInfoVO jsonResult = new ResultInfoVO();

		if (isAjax(reqest)) {
			jsonResult.setMessage("系统发生异常,请联系管理员！");
			jsonResult.setCode(ResultMessage.SYS_ERROR.code);
			return jsonResult;
		} else {
			if (e instanceof CustomException) {
				CustomException customException = (CustomException) e;
				jsonResult.setMessage(customException.getMessage());
				jsonResult.setCode(customException.getCode());
				return jsonResult;
			} else {
				logger.error("发生系统异常", e);
				jsonResult.setMessage("系统发生异常,请联系管理员！");
				jsonResult.setCode(ResultMessage.SYS_ERROR.code);
				return jsonResult;
			}
		}
	}
}