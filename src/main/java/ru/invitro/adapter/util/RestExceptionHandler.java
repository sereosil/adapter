package ru.invitro.adapter.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.invitro.adapter.model.exceptions.CustomApiException;
import ru.invitro.adapter.model.exceptions.DocumentNotFoundException;
import ru.invitro.adapter.model.exceptions.MissingEntityException;

/**
 * Обработчик исключений.
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ DocumentNotFoundException.class })
    public ResponseEntity<String> handleException(final DocumentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({ CustomApiException.class })
    public ResponseEntity<String> handleException(final CustomApiException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler({ MissingEntityException.class })
    public ResponseEntity<String> handleException(final MissingEntityException ex) {
        return ResponseEntity.status(HttpStatus.GONE).body(ex.getMessage());
    }


}

