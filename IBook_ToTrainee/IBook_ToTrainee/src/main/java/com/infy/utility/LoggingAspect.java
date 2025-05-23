package com.infy.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	private Logger logger=LogManager.getLogger(this.getClass());
	
	@AfterThrowing(pointcut = "execution(* com.infy.repository.*.*(..))", throwing = "exception")
	public void logExceptionFromRepository(Exception exception){

		logger.error(exception.getMessage(),exception);
	}

	@AfterThrowing(pointcut = "execution(* com.infy.service.*Impl.*(..))", throwing = "exception")
	public void logExceptionFromService(Exception exception){
		logger.error(exception.getMessage(),exception);
	}
}
