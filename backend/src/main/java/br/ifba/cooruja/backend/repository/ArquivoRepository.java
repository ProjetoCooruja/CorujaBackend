package br.ifba.cooruja.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.ifba.cooruja.backend.model.ArquivoModel;


public interface ArquivoRepository extends JpaRepository<ArquivoModel, Long>
{

}
