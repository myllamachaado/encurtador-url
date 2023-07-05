package com.url.shortener.encurtador.ExceptionHandler;

import com.url.shortener.encurtador.ExceptionHandler.exceptions.UrlErrorException;
import com.url.shortener.encurtador.ExceptionHandler.message.Erro;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UrlErrorException.class)
    public ResponseEntity<Object> handleNegocio(UrlErrorException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Erro erro = new Erro(status.value(), OffsetDateTime.now(), ex.getError());
        return super.handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
    }


    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> handleParseData(DateTimeParseException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Erro erro = new Erro(status.value(), OffsetDateTime.now(), "Data inválida, insira um LocalDateTime válido!");
        return super.handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
    }


}
