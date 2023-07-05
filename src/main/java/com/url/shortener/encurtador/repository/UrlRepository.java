package com.url.shortener.encurtador.repository;

import com.url.shortener.encurtador.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    public Url findByUrlEncurtada(String urlEncurtada);
}
