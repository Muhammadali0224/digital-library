package uz.pdp.digitallibrary.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.digitallibrary.payload.ErrorDTO;
import uz.pdp.digitallibrary.util.ApiResult;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles @Valid validation errors inside DTOs
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return ApiResult.error("Validation failed", errors);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult<Map<String, String>> handleConstraintViolationExceptions(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();

        exception
                .getConstraintViolations()
                .forEach(violation ->
                        errors.put(violation.getPropertyPath().toString(), violation.getMessage())
                );

        return ApiResult.error("Invalid request parameters. Please correct the following errors:", errors);
    }

    @ExceptionHandler(value = RestException.class)
    public ResponseEntity<ErrorDTO> handleRestException(RestException e) {
        ErrorDTO errorDTO = new ErrorDTO(
                LocalDate.now(),
                e.getMessage(),
                400
        );
        System.out.println("errorDTO = " + errorDTO);

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }


}
