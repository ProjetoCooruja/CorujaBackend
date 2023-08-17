package br.ifba.cooruja.backend.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class PostDTO {

    private Long id;
	private String titulo;
	private String tags;
    private Long id_arquivo;
    private String path_arquivo;
    private String nome;
    private Timestamp data_cadastro;
    private List<ComentarioDTO> comentarios;

    public PostDTO(){
        super();
    }

    public PostDTO(Long id, String titulo, 
        String tags, Long id_arquivo, String path_arquivo, 
        String nome, Timestamp data_cadastro, List<ComentarioDTO> comentarios ) 
    {
        this.id = id;
        this.titulo = titulo;
        this.tags = tags;
        this.id_arquivo = id_arquivo;
        this.path_arquivo = path_arquivo;
        this.nome = nome;
        this.data_cadastro = data_cadastro;
        this.comentarios = comentarios;
    }


    
}
