package com.noticias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.noticias.models.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
}
