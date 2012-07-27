package com.monkar.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="desarrollador")
public class Desarrollador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDesarrollador;

	private String empresa;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario = new Usuario();

    public Desarrollador() {
    }

	public int getIdDesarrollador() {
		return this.idDesarrollador;
	}

	public void setIdDesarrollador(int idDesarrollador) {
		this.idDesarrollador = idDesarrollador;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
