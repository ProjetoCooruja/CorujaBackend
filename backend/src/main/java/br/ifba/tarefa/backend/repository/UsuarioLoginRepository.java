package br.ifba.tarefa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ifba.tarefa.backend.model.UsuarioLoginModel;


public interface UsuarioLoginRepository extends JpaRepository<UsuarioLoginModel, Long>
{

}
