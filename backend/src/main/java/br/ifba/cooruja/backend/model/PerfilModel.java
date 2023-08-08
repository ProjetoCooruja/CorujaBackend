package br.ifba.cooruja.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "perfil_acesso")
public class PerfilModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome_perfil", nullable = false)
	private String nome_perfil;

	@Column(name = "descricao_perfil", nullable = false)
	private String descricao_perfil;
	
	@Column(name = "status", nullable = false)
	private Integer status;

	public PerfilModel() {
		super();
	}

	public PerfilModel(Long id, String nome_perfil, String descricao_perfil, Integer status) {
		super();
		this.id = id;
		this.nome_perfil = nome_perfil;
		this.descricao_perfil = descricao_perfil;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_perfil() {
		return nome_perfil;
	}

	public void setNome_perfil(String nome_perfil) {
		this.nome_perfil = nome_perfil;
	}

	public String getDescricao_perfil() {
		return descricao_perfil;
	}

	public void setDescricao_perfil(String descricao_perfil) {
		this.descricao_perfil = descricao_perfil;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
