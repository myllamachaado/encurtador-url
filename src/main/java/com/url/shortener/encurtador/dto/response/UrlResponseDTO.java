package com.url.shortener.encurtador.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlResponseDTO {

    private String urlOriginal;
    private String urlEncurtada;
    private String dataExpiracao;

}
