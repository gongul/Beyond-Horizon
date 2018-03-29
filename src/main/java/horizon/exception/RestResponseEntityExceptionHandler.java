package horizon.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestControllerAdvice
//@Order(1)
public class RestResponseEntityExceptionHandler extends
  ResponseEntityExceptionHandler {
	Logger log = Logger.getLogger(this.getClass());
	
    @ExceptionHandler({ RepositoryConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleAccessDeniedException(HttpServletRequest req,RepositoryConstraintViolationException e,WebRequest wr) throws IOException {
 
		String message = "Validation failed.\n"+e.getErrors().getAllErrors().stream()
													.map(p -> p.toString())
													.collect(Collectors.joining("\n"));
		  
		Map<String,Object> map = new HashMap<>();
		map.put("timestamp" , new Timestamp(System.currentTimeMillis()));
		map.put("error", "Bad Request");
		map.put("message", message);
		
		map.put("path", req.getRequestURI());
//		map.put("query", req.getParameterMap());
		map.put("status", 400);
		
		return map;
		
    }
    
}