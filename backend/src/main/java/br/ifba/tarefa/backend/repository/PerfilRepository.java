package br.ifba.tarefa.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ifba.tarefa.backend.model.PerfilModel;


public interface PerfilRepository extends JpaRepository<PerfilModel, Long>
{

}
