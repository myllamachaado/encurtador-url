package com.url.shortener.encurtador.service.implementacao;

import com.google.common.hash.Hashing;
import com.url.shortener.encurtador.dto.request.UrlRequestDTO;
import com.url.shortener.encurtador.ExceptionHandler.exceptions.UrlErrorException;
import com.url.shortener.encurtador.entity.Url;
import com.url.shortener.encurtador.repository.UrlRepository;
import com.url.shortener.encurtador.service.interfaces.UrlService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class UrlServiceImp implements UrlService {

    private UrlRepository urlRepository;

    @Override
    public Url gerarLinkEncurtado(UrlRequestDTO urlDTO) {
        String urlEncurtada = encodeUrl(urlDTO.getUrl());
        LocalDateTime horaCriacao = LocalDateTime.now();
        var retorno = new Url(urlDTO.getUrl(), urlEncurtada, horaCriacao,
                getDataExpiracaoUrl(urlDTO.getDataExpiracao(), horaCriacao));
        return this.guardarLinkEncurtado(retorno);
    }

    @Transactional
    @Override
    public Url guardarLinkEncurtado(Url url) {
        return urlRepository.save(url);
    }

    @Override
    public Url retornaUrlEncurtada(String url) {
        return urlRepository.findByUrlEncurtada(url);
    }

    @Transactional
    @Override
    public void deletarUrlEncurtada(Url url) {
        urlRepository.delete(url);
    }

    public void validaRequestBody(UrlRequestDTO urlDto){
        if (urlDto.getUrl() == null){
            throw new UrlErrorException("Url nula, insira uma url no json da requisição!");
        }
        if(urlDto.getUrl().equals("")){
            throw new UrlErrorException("Url vazia, insira uma url no json da requisição!");
        }
    }

    @Transactional
    public Url validaRedirecionamentoUrl(String urlEncurtada) throws UrlErrorException {
        if (StringUtils.isEmpty(urlEncurtada)) {
            throw new UrlErrorException("URL Inválida!");
        }
        else{
            var urlResponse = this.retornaUrlEncurtada(urlEncurtada);

            if (urlResponse == null) {
                throw new UrlErrorException("URL não cadastrada!");
            }

            if (urlResponse.getDataExpiracao().isBefore(LocalDateTime.now())){
                this.deletarUrlEncurtada(urlResponse);
                throw new UrlErrorException("Url expirada, cadastre a Url novamente!");
            }

            return urlResponse;
        }
    }

    private String encodeUrl(String url) {
        String urlEncurtada = "";
        LocalDateTime time = LocalDateTime.now();
        urlEncurtada = Hashing.murmur3_32_fixed()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return urlEncurtada;
    }

    private LocalDateTime getDataExpiracaoUrl(String dataExpiracao, LocalDateTime horaCriacaoUrl) {
        if(StringUtils.isBlank(dataExpiracao)){
            return horaCriacaoUrl.plusMinutes(10);
        }
        return LocalDateTime.parse(dataExpiracao);
    }

}
