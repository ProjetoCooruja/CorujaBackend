package br.ifba.cooruja.backend.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
	private Boolean termos_aceite;
	private String id_perfil;
	private Date data_cadastro;
    private Date data_modificacao;

    public UsuarioDTO(){
        super();
    }

	public UsuarioDTO(Long id, String nome, String sobrenome, String email, Boolean termos_aceite,
            String id_perfil, Date data_cadastro, Date data_modificacao) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.termos_aceite = termos_aceite;
        this.id_perfil = id_perfil;
        this.data_cadastro = data_cadastro;
        this.data_modificacao = data_modificacao;
    }

    

    

}
