package com.mayuktha.usersmgmt.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mayuktha.usersmgmt.response.ErrorDetails;



@ControllerAdvice
public class UserMgmtResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class )
	public ResponseEntity<Object> handleUserNotFoundExceptionExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errordetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(),"FAIL", ex.getMessage());
		return new ResponseEntity(errordetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class )
	public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request){
		ErrorDetails errordetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),"FAIL",ex.getMessage());
		return new ResponseEntity(errordetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorDetails errordetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),"FAIL",ex.getFieldErrors().stream().map(fe->fe.getField()+":"+fe.getDefaultMessage()).collect(Collectors.joining(",")));
		return new ResponseEntity(errordetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class )
	public ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) {
		ErrorDetails errordetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(),"FAIL", ex.getMessage());
		return new ResponseEntity(errordetails,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserAlreadyExistedException.class)
	public ResponseEntity<Object> handleUserExistedException(Exception ex, WebRequest request) {
		ErrorDetails errordetails = new ErrorDetails(HttpStatus.ALREADY_REPORTED.value(),"FAIL", ex.getMessage());
		return new ResponseEntity(errordetails,HttpStatus.ALREADY_REPORTED);
	}
}
