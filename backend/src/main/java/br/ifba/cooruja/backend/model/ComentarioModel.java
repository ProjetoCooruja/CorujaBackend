package br.ifba.cooruja.backend.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "comentario")
public class ComentarioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
  	private UsuarioModel usuario;

	@Column(name = "comentario", nullable = true)
	private String comentario;

	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp data_cadastro;

	@ManyToOne
    @JoinColumn(name = "id_post")
	@JsonBackReference
    private PostModel postModel;
	
	@PrePersist
    protected void onCreate() {
        data_cadastro = new Timestamp( System.currentTimeMillis() );
    }
}
