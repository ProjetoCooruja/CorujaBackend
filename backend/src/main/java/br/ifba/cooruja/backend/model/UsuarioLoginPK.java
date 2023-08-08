package br.ifba.cooruja.backend.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioLoginPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id_usuario;
	private Timestamp data_login;
	
	public UsuarioLoginPK(Long id_usuario) {
		super();
		this.id_usuario = id_usuario;
		this.data_login = new Timestamp(System.currentTimeMillis());
	}
	public Long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public Timestamp getData_login() {
		return data_login;
	}
	public void setData_login(Timestamp data_login) {
		this.data_login = data_login;
	}
	
	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		UsuarioLoginPK pk = (UsuarioLoginPK) o;
		return Objects.equals( this.getId_usuario(), pk.getId_usuario() ) &&
			   Objects.equals( this.getData_login(), pk.getData_login() );
	}
	
	
}