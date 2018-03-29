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
		map.put("message", "��õ��� �ʴ� �����Դϴ�. �����ڿ��� �����ϼ���");
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
		map.put("message", "�������� URL�� �Է����ֽʽÿ�.");
		map.put("status", 404);
		map.put("path", e.getRequestURL());
		
		
		return map;
	}
	
	@ExceptionHandler({ResourceNotFoundException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,Object> resourceFoundException(HttpServletRequest req,ResourceNotFoundException e) {
		Map<String,Object> map = new HashMap<>();
		String message = "�߸��� Query�̰ų� Query�� ���� �����Ͱ� �����ϴ�. Query�� Ȯ���ϼ��� ["+req.getQueryString()+"]";
		
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
