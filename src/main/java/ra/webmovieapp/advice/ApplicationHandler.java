package ra.webmovieapp.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.webmovieapp.exception.CustomException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationHandler {
	public ResponseEntity<Map<String, String>> handleInvalidException(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), err.getDefaultMessage());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String> handleCustomException(CustomException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
