package br.ifba.tarefa.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ifba.tarefa.backend.model.UsuarioModel;


public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>
{

	Optional<UsuarioModel> findByEmail(String email);
}
