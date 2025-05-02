package com.infy.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {
	public static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
	// Log before any method in ICricketApplication is called
    @Before("execution(* com.infy.ICricketApplication.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info("Method called: " + joinPoint.getSignature().getName());
    }

    // Log after any method in ICricketApplication successfully returns
    @AfterReturning(pointcut = "execution(* com.infy.ICricketApplication.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        LOGGER.info("Method finished: " + joinPoint.getSignature().getName());
    }

    // Log if any method throws an exception
    @AfterThrowing(pointcut = "execution(* com.infy.ICricketApplication.*(..))", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
    }

	@AfterThrowing(pointcut = "execution(* com.infy.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception){
		LOGGER.error(exception.getMessage(), exception);
	}
	
	

}
