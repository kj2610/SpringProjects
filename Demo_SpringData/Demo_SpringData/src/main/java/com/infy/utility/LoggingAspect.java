package com.infy.utility;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	public static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.infy.service.*Impl.*(..))", throwing="exception")
	public void logServiceException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
	}

}
