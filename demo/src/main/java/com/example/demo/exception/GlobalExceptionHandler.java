package com.example.demo.exception;


import com.example.demo.util.ResultInfoVO;
import com.example.demo.util.ResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * @Author shieldofzues
 * @Description 统一处理
 * @Date 11:10 2016/5/23
 **/

@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 判断
     */
	/*public static boolean isAjax(HttpServletRequest httpRequest) {
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
	}*/

    /**
     * 以下异常处理可以处理controller层 参数校验产生的异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultInfoVO exceptionHandler(BindException ex) {
        BindingResult result = ex.getBindingResult();
        return processBindingResult(result);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultInfoVO exceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return processBindingResult(result);
    }

    private ResultInfoVO processBindingResult(BindingResult result) {
        ResultInfoVO resultInfoVO = new ResultInfoVO();
        if (result.hasErrors()) { // 现在表示执行的验证出现错误
            String errorMessage = "";
            Iterator<ObjectError> iterator = result.getAllErrors().iterator(); // 获取全部错误信息
            while (iterator.hasNext()) {
                ObjectError error = iterator.next();    // 取出每一个错误
                errorMessage += "[" + error.getDefaultMessage() + "]";
            }
            resultInfoVO.setCode(ResultMessage.ERROR.code);
            resultInfoVO.setMessage(errorMessage);
        }
        return resultInfoVO;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultInfoVO errorHandler(HttpServletRequest reqest,
                                     HttpServletResponse response, Exception e) throws Exception {
        if (e instanceof CustomException) {
            CustomException customException = (CustomException) e;
            return new ResultInfoVO(customException.getCode(), null, customException.getMessage());
        } else {
            logger.error(String.valueOf(ResultMessage.SYS_ERROR.code), e);
            return new ResultInfoVO(ResultMessage.SYS_ERROR.code, null, ResultMessage.SYS_ERROR.message);
        }
    }

}