package com.andersen.hoover.oleg.controller.handler;

import com.andersen.hoover.oleg.controller.handler.error.AppError;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String errorMessage = e.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(". "));

        log.error(errorMessage, e);

        return new ResponseEntity<>(
                new AppError(
                        HttpStatus.BAD_REQUEST.value(),
                        errorMessage
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
