package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: shieldofzues
 * @Date: 2018/10/11 15:54
 * @Description:
 */
@Aspect
@Component
public class HttpAspect {

	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

	@Pointcut("within(com.example.demo.controller..*) && !within(com.example.demo.controller.UserController)")
	public void pointCutMethod() {
	}

	@Before("pointCutMethod()")
	public void doBefore(JoinPoint joinPoint) {

		logger.info("------------before------------");
	}

	@AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
	public void saveLogByAfterThrowing(JoinPoint joinPoint, Throwable e) throws Throwable {
		logger.error("日志切面操作失败:{}", e);
		e.printStackTrace();
	}

	@AfterReturning(returning = "object", pointcut = "pointCutMethod()")
	public void doAfterReturning(JoinPoint joinPoint, Object object) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		/**
		 * url
		 */

		logger.info("------------url={}------------", request.getRequestURL());

		/**
		 * method
		 */

		logger.info("------------method={}------------", request.getMethod());

		/**
		 * ip
		 */

		logger.info("------------ip={}------------", request.getRemoteAddr());

		/**
		 * 类方法
		 */

		logger.info("------------class_method={}------------", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

		/**
		 * 参数
		 */

		logger.info("------------args={}------------", joinPoint.getArgs());
		logger.info("------------response={}------------", null == object ? "" : object.toString());
	}

}
