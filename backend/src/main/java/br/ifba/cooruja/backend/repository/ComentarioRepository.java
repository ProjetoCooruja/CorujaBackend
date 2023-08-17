package br.ifba.cooruja.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifba.cooruja.backend.model.ComentarioModel;

public interface ComentarioRepository extends JpaRepository<ComentarioModel, Long>
{
}
