package travelling.api.app.controller;

import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import travelling.api.app.exception.*;
import travelling.api.app.model.response.ObjectResponse;

@RestControllerAdvice
@AllArgsConstructor
public class WebHandelApiController extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(setStatus(null, "ERROR", ex.getMessage(), status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(setStatus(null, "ERROR", ex.getMessage(), status.value()));
    }

    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<ObjectResponse> handleHibernateException(HibernateException e) {
        return error("HIBERNATE", e.getMessage(), 400);

    }

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<ObjectResponse> handleValidator(ValidatorException val) {
        return error("VALIDATE", val.getMessage(), 400);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ObjectResponse> handleObjectNotFoundException(ObjectNotFoundException notFoundException) {
        return error("NOT_FOUND", notFoundException.getMessage(), 400);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ObjectResponse> handleInternalServerException(InternalServerException internalServerException) {
        return error("INTERNAL_SERVER", internalServerException.getMessage(), 403);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ObjectResponse> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        return error("ACCESS_DENIED", accessDeniedException.getMessage(), 403);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ObjectResponse> handleLoginFalse(LoginException loginException) {
        return error("USER-01", loginException.getMessage(), 400);
    }

    public ResponseEntity<ObjectResponse> error(String code, String message, int httpStatus) {
        ObjectResponse objectResponse = setStatus(null, code, message, httpStatus);

        return new ResponseEntity<>(objectResponse, HttpStatus.valueOf(httpStatus));
    }

    private ObjectResponse setStatus(Object data, String code, String message, int httpStatus) {
        ObjectResponse objectResponse = new ObjectResponse();
        ObjectResponse.ResponseStatus status = new ObjectResponse.ResponseStatus();
        status.setCode(code);
        status.setMessage(message);
        status.setStatusCode(httpStatus);
        objectResponse.setData(data);

        return objectResponse;
    }
}
