package br.com.fiap.Auxilia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.Auxilia.model.Usuario;
import br.com.fiap.Auxilia.repository.UsuarioRepository;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping
    public List<Usuario> home(){
        return repository.findAll();
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Usuario> show(@PathVariable long id) {
        var usuario = repository.findById(id);

        if(usuario.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario.get());
    }

    @PostMapping
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario) {

        repository.save(usuario);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> delete(@PathVariable long id) {
        var usuario = repository.findById(id);

        if(usuario.isEmpty()) return ResponseEntity.notFound().build();
        repository.delete(usuario.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable long id, @RequestBody Usuario usuario) {
        var usuarioCont = repository.findById(id);

        if(usuarioCont.isEmpty()) return ResponseEntity.notFound().build();

        usuario.setId(id);
        repository.save(usuario);

        return ResponseEntity.ok(usuario);
    }
}
