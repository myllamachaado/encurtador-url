package com.url.shortener.encurtador.ExceptionHandler.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Erro {

    private Integer status;
    private OffsetDateTime data;
    private String message;

}
