package br.ifba.tarefa.backend.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario_login")
public class UsuarioLoginModel {

	@EmbeddedId
	private UsuarioLoginPK usuarioLoginID;

	public UsuarioLoginModel(UsuarioLoginPK usuarioLoginID) {
		super();
		this.usuarioLoginID = usuarioLoginID;
	}

	public UsuarioLoginPK getUsuarioLoginID() {
		return usuarioLoginID;
	}

	public void setUsuarioLoginID(UsuarioLoginPK usuarioLoginID) {
		this.usuarioLoginID = usuarioLoginID;
	}
}
