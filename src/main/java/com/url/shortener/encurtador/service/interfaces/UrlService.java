package com.url.shortener.encurtador.service.interfaces;

import com.url.shortener.encurtador.dto.request.UrlRequestDTO;
import com.url.shortener.encurtador.entity.Url;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {
    public Url gerarLinkEncurtado(UrlRequestDTO urlDTO);
    public Url guardarLinkEncurtado(Url url);
    public Url retornaUrlEncurtada(String url);
    public void deletarUrlEncurtada(Url url);
}
