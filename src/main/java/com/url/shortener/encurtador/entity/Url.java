package com.url.shortener.encurtador.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Url {

    public Url(String urlOriginal, String urlEncurtada,
               LocalDateTime dataCreacao, LocalDateTime dataExpiracao) {
        this.urlOriginal = urlOriginal;
        this.urlEncurtada = urlEncurtada;
        this.dataCreacao = dataCreacao;
        this.dataExpiracao = dataExpiracao;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @NotBlank
    private String urlOriginal;
    @NotBlank
    private String urlEncurtada;
    @NotBlank
    private LocalDateTime dataCreacao;
    @NotBlank
    private LocalDateTime dataExpiracao;

}
