package br.ifba.cooruja.backend.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "post")
public class PostModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_arquivo", referencedColumnName = "id")
  	private ArquivoModel arquivo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
  	private UsuarioModel usuario;

	@Column(name = "titulo", nullable = true)
	private String titulo;

	@Column(name = "tags", nullable = true)
	private String tags;

	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp data_cadastro;

	@OneToMany(cascade = CascadeType.ALL)
  	@JoinColumn(name="id_post")
	@JsonManagedReference
  	private List<ComentarioModel> comentarios;

	@PrePersist
    protected void onCreate() {
        data_cadastro = new Timestamp( System.currentTimeMillis() );
    }

	
}
