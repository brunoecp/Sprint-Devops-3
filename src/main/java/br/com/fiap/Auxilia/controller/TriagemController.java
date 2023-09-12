package br.com.fiap.Auxilia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.Auxilia.model.Triagem;
import br.com.fiap.Auxilia.repository.TriagemRepository;
import br.com.fiap.Auxilia.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/Triagem")
public class TriagemController {
    
    @Autowired
    TriagemRepository repository;
    @Autowired
    UsuarioRepository uRepository;

    @GetMapping
    public List<Triagem> home(){
        return repository.findAll();
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Triagem> show(@PathVariable long id) {
        var triagem = repository.findById(id);

        if(triagem.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(triagem.get());
    }

    @PostMapping
    public ResponseEntity<Triagem> insert(@RequestBody Triagem triagem) {
        triagem.setUsuario(uRepository.findById(triagem.getUsuario().getId()).get());

        repository.save(triagem);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(triagem);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Triagem> delete(@PathVariable long id) {
        var triagem = repository.findById(id);

        if(triagem.isEmpty()) return ResponseEntity.notFound().build();
        repository.delete(triagem.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Triagem> update(@PathVariable long id, @RequestBody Triagem triagem) {
        var triagemCont = repository.findById(id);

        if(triagemCont.isEmpty()) return ResponseEntity.notFound().build();

        triagem.setId(id);
        repository.save(triagem);

        return ResponseEntity.ok(triagem);
    }
}
