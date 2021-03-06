package com.portifoliodharan.dharanportifolio.controller;

import com.portifoliodharan.dharanportifolio.models.ThemeModel;
import com.portifoliodharan.dharanportifolio.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/themes")
public class ThemeController {

    @Autowired
    private ThemeRepository repository;

    @GetMapping
    public ResponseEntity<List<ThemeModel>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeModel> getById(@PathVariable long id){
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{descricao}")
    public ResponseEntity<List<ThemeModel>> getByDescircao(@PathVariable String descricao){
        return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @GetMapping("/name/{linguagem}")
    public ResponseEntity<List<ThemeModel>> getByLinguagem(@PathVariable String linguagem){
        return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(linguagem));
    }

    @PostMapping
    public ResponseEntity<ThemeModel> postTheme(@RequestBody ThemeModel theme){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(theme));
    }

    @PutMapping
    public ResponseEntity<ThemeModel> putTheme(@RequestBody ThemeModel theme){
        return ResponseEntity.ok(repository.save(theme));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        repository.deleteById(id);
    }
}
