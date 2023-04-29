package ir.encoding.order.exception;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {	
		
	@ExceptionHandler(value = MalformedJwtException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ResponseEntity<EntityModel<Object>> handleMalformedJwtException(MalformedJwtException malformedJwtException) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(EntityModel.of(malformedJwtException.getMessage()));
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<CollectionModel<String>> handleConstraintViolationException(ConstraintViolationException exception){
		List<String> violatedConstraints = exception.getConstraintViolations().stream().map(cv -> cv.getMessage()).toList();
		return ResponseEntity.badRequest().body(CollectionModel.of(violatedConstraints));
	}
}
