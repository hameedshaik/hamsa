package com.sgl.smartpra.master.app.common.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonLoggingAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonLoggingAdvice.class);

	@Before("execution(* com.sgl.smartpra.master.app.*.*.*(..))")
	public void beforeInvoke(JoinPoint joinPoint) throws Throwable {
		LOGGER.info("Starting method execution: {} in class:{} ", joinPoint.getSignature().getName(),
				joinPoint.getSignature().getDeclaringTypeName());
	}

	@After("execution(* com.sgl.smartpra.master.app.*.*.*(..))")
	public void afterInvoke(JoinPoint joinPoint) throws Throwable {
		LOGGER.info("Exiting method execution:{} in class:{}", joinPoint.getSignature().getName(),
				joinPoint.getSignature().getDeclaringTypeName());
	}

	@AfterThrowing(pointcut = "execution(* com.sgl.smartpra.master.app.*.*.*(..))", throwing = "error")
	public void exceptionAdvice(JoinPoint jp, Throwable error) {
		LOGGER.error("throwing concern");
		LOGGER.error("Method Signature: " + jp.getSignature());
		LOGGER.error("Exception is: " + error);
		LOGGER.error("end of after throwing advice...");

	}

}
