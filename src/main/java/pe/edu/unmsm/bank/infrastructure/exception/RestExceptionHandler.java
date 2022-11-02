package pe.edu.unmsm.bank.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pe.edu.unmsm.bank.infrastructure.validation.ValidationError;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomerNotFoundException.class, AccountNotFoundException.class, MovementNotFoundException.class})
    public final ResponseEntity<ExceptionResponse> handleEntityNotFoundException(DomainException ex, WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DomainException.class)
    public final ResponseEntity<ExceptionResponse> handleDomainException(DomainException ex, WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PersistenceException.class)
    public final ResponseEntity<ExceptionResponse> handlePersistenceException(PersistenceException ex, WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getCause().getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAll(PersistenceException ex, WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getCause().getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        List<ValidationError> validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ValidationError.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .toList();
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .validationErrors(validationErrors)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          @NonNull HttpHeaders headers,
                                                                          @NonNull HttpStatus status,
                                                                          @NonNull WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             @NonNull HttpHeaders headers,
                                                             @NonNull HttpStatus status,
                                                             @NonNull WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
