package ru.kpfu.itis.service.book.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerController {

    @ExceptionHandler({EmptyResultDataAccessException.class, EntityNotFoundException.class})
    public ResponseEntity handleUserNotFound(Exception e) {
        return buildErrorResult(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleBadRequest(IllegalArgumentException e) {
        return buildErrorResult(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleInternalException(Exception e) {
        return buildErrorResult(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<Object> buildErrorResult(HttpStatus status, Exception ex) {
        if (ex != null)
            log.warn(ex.getMessage(), ex);

        return ResponseEntity.status(status).build();
    }

}
