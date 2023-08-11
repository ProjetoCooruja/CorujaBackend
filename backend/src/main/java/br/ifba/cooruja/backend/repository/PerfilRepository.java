package br.ifba.cooruja.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ifba.cooruja.backend.model.PerfilModel;


public interface PerfilRepository extends JpaRepository<PerfilModel, Long>
{

}
