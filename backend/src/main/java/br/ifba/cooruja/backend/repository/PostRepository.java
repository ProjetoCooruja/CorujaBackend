package br.ifba.cooruja.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ifba.cooruja.backend.model.PostModel;
import jakarta.persistence.Tuple;

import java.util.List;


public interface PostRepository extends JpaRepository<PostModel, Long>
{
    @Query(value = "select p.id, p.titulo, p.comentario, p.tags, a.id as 'id_arquivo', a.path_arquivo, u.nome, p.data_cadastro from post as p left join arquivo as a on p.id_arquivo = a.id left join usuario as u on p.id_usuario = u.id", nativeQuery = true)
    List<Tuple> findByAllPosts();
}
