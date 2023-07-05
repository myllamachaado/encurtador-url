package com.url.shortener.encurtador.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlRequestDTO {

    @Valid
    @NotBlank
    private String url;
    private String dataExpiracao;

}
