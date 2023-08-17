package br.ifba.cooruja.backend.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ComentarioDTO {

    private Long id;
    private Long id_usuario;
    private String nome_usuario;
	private String comentario;
    private Timestamp data_cadastro;
    private UsuarioDTO usuarioDTO;

    public ComentarioDTO(){
        super();
    }

    public ComentarioDTO(Long id, Long id_usuario, String nome_usuario, String comentario, Timestamp data_cadastro) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.comentario = comentario;
        this.data_cadastro = data_cadastro;
    }

}
