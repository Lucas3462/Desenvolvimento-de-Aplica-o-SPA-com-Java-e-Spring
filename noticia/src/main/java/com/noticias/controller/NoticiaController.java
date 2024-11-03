package com.noticias.controller;

import com.noticias.models.Noticia;
import com.noticias.repositories.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {
    

    @Autowired
    private NoticiaRepository noticiaRepository;

    @GetMapping
    public List<Noticia> listarNoticias() {
        return noticiaRepository.findAll();
    }

    @PostMapping
    public Noticia criarNoticia(@RequestBody Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Noticia> obterNoticia(@PathVariable Long id) {
        return noticiaRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Noticia> atualizarNoticia(@PathVariable Long id, @RequestBody Noticia noticiaDetalhes) {
        return noticiaRepository.findById(id)
            .map(noticia -> {
                noticia.setTitulo(noticiaDetalhes.getTitulo());
                noticia.setConteudo(noticiaDetalhes.getConteudo());
                noticia.setAutor(noticiaDetalhes.getAutor());
                return ResponseEntity.ok(noticiaRepository.save(noticia));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> excluirNoticia(@PathVariable Long id) {
    if (noticiaRepository.existsById(id)) {
        noticiaRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // Retorna status 204
    } else {
        return ResponseEntity.notFound().build();   // Retorna status 404
    }
}
}