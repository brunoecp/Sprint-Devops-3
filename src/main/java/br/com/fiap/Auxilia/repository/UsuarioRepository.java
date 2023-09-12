package br.com.fiap.Auxilia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Auxilia.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
