package staff.location.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles {@link ConstraintViolationException} thrown during request processing.
     * Returns a {@link ResponseEntity} containing a map with an "error" message and a {@link HttpStatus#BAD_REQUEST}
     * status.
     *
     * @param ex the exception thrown when a constraint is violated
     * @return ResponseEntity<Map<String, String>> containing the error message
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(final ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(Collections.singletonMap("error", "invalid characters"));
    }
}
