package com.url.shortener.encurtador.controller;

import com.url.shortener.encurtador.dto.request.UrlRequestDTO;
import com.url.shortener.encurtador.dto.response.UrlResponseDTO;
import com.url.shortener.encurtador.ExceptionHandler.exceptions.UrlErrorException;
import com.url.shortener.encurtador.service.implementacao.UrlServiceImp;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/encurtador")
public class EncurtadorUrlController {

    private UrlServiceImp urlService;

    @PostMapping("/gerar")
    public ResponseEntity<UrlResponseDTO> gerarUrlEncurtada(@Valid @RequestBody UrlRequestDTO urlDto)
                                                                            throws UrlErrorException {
        urlService.validaRequestBody(urlDto);
        var url = urlService.gerarLinkEncurtado(urlDto);
        var response = new UrlResponseDTO(url.getUrlOriginal(), url.getUrlEncurtada(),
                url.getDataExpiracao().toString());
        return new ResponseEntity<UrlResponseDTO>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{urlEncurtada}")
    public ResponseEntity<Void> redirecionaParaUrlOriginal(@PathVariable String urlEncurtada,
                                                        HttpServletResponse response)
                                                        throws IOException, UrlErrorException {

        var urlValidada = urlService.validaRedirecionamentoUrl(urlEncurtada);
        response.sendRedirect(urlValidada.getUrlOriginal());
        return ResponseEntity.noContent().build();
    }
}
