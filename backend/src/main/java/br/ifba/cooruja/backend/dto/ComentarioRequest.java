package br.ifba.cooruja.backend.dto;

import lombok.Data;

@Data
public class ComentarioRequest {

    private Long id_usuario;
    private Long id_post;
	private String comentario;
    
    public ComentarioRequest(){
        super();
    }

    public ComentarioRequest(Long id_usuario, Long id_post, String comentario) {
        this.id_usuario = id_usuario;
        this.id_post = id_post;
        this.comentario = comentario;
    }

}
