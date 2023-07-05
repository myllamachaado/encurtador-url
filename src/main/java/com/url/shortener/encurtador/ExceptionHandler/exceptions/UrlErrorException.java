package com.url.shortener.encurtador.ExceptionHandler.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlErrorException extends RuntimeException {
    private String error;
}
