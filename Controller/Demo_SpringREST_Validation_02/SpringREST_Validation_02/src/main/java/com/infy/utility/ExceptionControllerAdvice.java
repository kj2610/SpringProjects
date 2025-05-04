package com.infy.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.exception.InfyBankException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice{
	
	@Autowired
	org.springframework.core.env.Environment environment;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo>exceptionHandler(Exception exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(InfyBankException.class)
	public ResponseEntity<ErrorInfo>infyBankExceptionHandler (InfyBankException infyBankException) {
			ErrorInfo error = new ErrorInfo();
			error.setErrorMessage(environment.getProperty(infyBankException.getMessage()));
			error.setErrorCode(HttpStatus.NOT_FOUND.value());
			error.setTimeStamp(LocalDateTime.now());
			return new ResponseEntity<ErrorInfo> (error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo>exceptionHandler(MethodArgumentNotValidException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(LocalDateTime.now());
		error.setErrorMessage(exception.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(", ")));
		
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class) 
	public ResponseEntity<ErrorInfo> pathExceptionHandler(ConstraintViolationException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(LocalDateTime.now());
		error.setErrorMessage(exception.getConstraintViolations().stream().map(x-> x.getMessage()).collect(Collectors.joining(", ")));
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.BAD_REQUEST);
	}
}


































//
//@RestControllerAdvice
//public class ExceptionControllerAdvice {
//	@Autowired
//	Environment environment;
//	public static final Logger LOGGER = LogManager.getLogger(ExceptionControllerAdvice.class);
//
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
//		ErrorInfo error = new ErrorInfo();
//		error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
//		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//		error.setTimestamp(LocalDateTime.now());
//		LOGGER.error(exception.getMessage(), exception);
//		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@ExceptionHandler(InfyBankException.class)
//	public ResponseEntity<ErrorInfo> infyBankexceptionHandler(InfyBankException exception) {
//		ErrorInfo error = new ErrorInfo();
//		error.setErrorMessage(environment.getProperty(exception.getMessage()));
//		error.setTimestamp(LocalDateTime.now());
//		error.setErrorCode(HttpStatus.NOT_FOUND.value());
//		LOGGER.error(exception.getMessage(), exception);
//		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
//	}
//
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<ErrorInfo> exceptionHandler(MethodArgumentNotValidException exception) {
//
//		ErrorInfo errorInfo = new ErrorInfo();
//		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
//
//		String errorMsg = exception.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
//				.collect(Collectors.joining(", "));
//
//		errorInfo.setErrorMessage(errorMsg);
//		errorInfo.setTimestamp(LocalDateTime.now());
//		LOGGER.error(exception.getMessage(), exception);
//		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
//	}
//
//
//}
