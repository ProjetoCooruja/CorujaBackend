package br.ifba.cooruja.backend.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "arquivo")
public class ArquivoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "id_usuario", nullable = false)
	private Integer id_usuario;

	@Column(name = "tipo_armazenamento", nullable = true)
	private Integer tipo_armazenamento;
	
	@Column(name = "nome_arquivo", nullable = false)
	private String nome_arquivo;

	@Column(name = "path_arquivo", nullable = false)
	private String path_arquivo;

	@Column(name = "tamanho", nullable = false)
	private Long tamanho;

	@Column(name = "data_cadastro", nullable = true)
	private Timestamp data_cadastro;

	public ArquivoModel(){}

	public ArquivoModel(Integer id_usuario, Integer tipo_armazenamento, String nome_arquivo, String path_arquivo,
			Long tamanho) {
		this.id_usuario = id_usuario;
		this.tipo_armazenamento = tipo_armazenamento;
		this.nome_arquivo = nome_arquivo;
		this.path_arquivo = path_arquivo;
		this.tamanho = tamanho;
		// this.data_cadastro = data_cadastro;
	}

	public ArquivoModel(Long id, Integer id_usuario, Integer tipo_armazenamento, String nome_arquivo, String path_arquivo,
			Long tamanho, Timestamp data_cadastro) {
		this.id = id;
		this.id_usuario = id_usuario;
		this.tipo_armazenamento = tipo_armazenamento;
		this.nome_arquivo = nome_arquivo;
		this.path_arquivo = path_arquivo;
		this.tamanho = tamanho;
		this.data_cadastro = data_cadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public Integer getTipo_armazenamento() {
		return tipo_armazenamento;
	}

	public void setTipo_armazenamento(Integer tipo_armazenamento) {
		this.tipo_armazenamento = tipo_armazenamento;
	}

	public String getPath_arquivo() {
		return path_arquivo;
	}

	public void setPath_arquivo(String path_arquivo) {
		this.path_arquivo = path_arquivo;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public Timestamp getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Timestamp data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public String getNome_arquivo() {
		return nome_arquivo;
	}

	public void setNome_arquivo(String nome_arquivo) {
		this.nome_arquivo = nome_arquivo;
	}

	
}
