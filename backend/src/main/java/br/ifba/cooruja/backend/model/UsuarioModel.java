package br.ifba.cooruja.backend.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioModel {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "sobrenome", nullable = false)
	private String sobrenome;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "termos_aceite", nullable = false)
	private Boolean termos_aceite;

	@Column(name = "id_perfil", nullable = false)
	private String id_perfil;

	@Column(name = "data_cadastro", nullable = true, columnDefinition = "TIMESTAMP DEFAULT now()")
	private Date data_cadastro;

	@Column(name = "data_modificacao", nullable = true)
	private Date data_modificacao;

	@Column(name = "imagem_usuario", nullable = true)
	private String imagem;

	public UsuarioModel() {
		super();
	}

	

	public UsuarioModel(long id, String nome, String sobrenome, String email, String senha, Boolean termos_aceite,
			String id_perfil, Date data_cadastro, Date data_modificacao, String imagem) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.termos_aceite = termos_aceite;
		this.id_perfil = id_perfil;
		this.data_cadastro = data_cadastro;
		this.data_modificacao = data_modificacao;
		this.imagem = imagem;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getTermos_aceite() {
		return termos_aceite;
	}

	public void setTermos_aceite(Boolean termos_aceite) {
		this.termos_aceite = termos_aceite;
	}

	public String getId_perfil() {
		return id_perfil;
	}

	public void setId_perfil(String id_perfil) {
		this.id_perfil = id_perfil;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Date getData_modificacao() {
		return data_modificacao;
	}

	public void setData_modificacao(Date data_modificacao) {
		this.data_modificacao = data_modificacao;
	}
	
	

	
}
