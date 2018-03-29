package horizon.exception;

import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@RestControllerAdvice
//@Order(2)
public class ResponseException{
	Logger log = Logger.getLogger(this.getClass());
	
	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String,Object> baseHandle(HttpServletRequest req,Exception e) {
		Map<String,Object> map = new HashMap<>();
		map.put("timestamp" , new Timestamp(System.currentTimeMillis()));
		map.put("error", e.getClass().getSimpleName());
		map.put("message", "명시되지 않는 에러입니다. 개발자에게 문의하세요");
		map.put("path", req.getRequestURI());
		map.put("query", req.getQueryString());
		
		return map;
	}
	
	
	
	@ExceptionHandler({NoHandlerFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String,Object> pageFoundException(NoHandlerFoundException e) {
		Map<String,Object> map = new HashMap<>();
		
		map.put("timestamp" , new Timestamp(System.currentTimeMillis()));
		map.put("error", "Not Found");
		map.put("message", "정상적인 URL을 입력해주십시오.");
		map.put("status", 404);
		map.put("path", e.getRequestURL());
		
		
		return map;
	}
	
	@ExceptionHandler({ResourceNotFoundException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,Object> resourceFoundException(HttpServletRequest req,ResourceNotFoundException e) {
		Map<String,Object> map = new HashMap<>();
		String message = "잘못된 Query이거나 Query에 대한 데이터가 없습니다. Query를 확인하세요 ["+req.getQueryString()+"]";
		
		map.put("timestamp" , new Timestamp(System.currentTimeMillis()));
		map.put("error", "Bad Requset.");
		map.put("message", message);
		map.put("status", 400);
		map.put("path", req.getRequestURI());
		
		
		return map;
	}
	
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public Map<String,Object> methodNotAllowedException(HttpServletRequest req,HttpRequestMethodNotSupportedException e) {
		Map<String,Object> map = new HashMap<>();
		String message = "Request method '"+e.getMethod()+"' not supported";
		
		map.put("timestamp" , new Timestamp(System.currentTimeMillis()));
		map.put("error", "Method Not Allowed");
		map.put("message", message);
		map.put("status", 405);
		map.put("path", req.getRequestURI());
		
		return map;
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,Object> messageNotReadableException(HttpServletRequest req,HttpMessageNotReadableException e) {
		Map<String,Object> map = new HashMap<>();
//		String message = "Request method '"+e.getMethod()+"' not supported";
		
	
		map.put("timestamp" , new Timestamp(System.currentTimeMillis()));
		map.put("error", "Bad Request");
		map.put("message", e.getMessage());
		map.put("status", 400);
		map.put("path", req.getRequestURI());
		
		return map;
	}
	
	
}
