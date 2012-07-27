package com.monkar.bean;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="compra")
public class Compra implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCompra;
	private String comentario;
	private int valoracion;
	private int comentado;
	

	
	@ManyToOne
	@JoinColumn(name="idAplicacion")
	private Aplicacion aplicacion = new Aplicacion();
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario= new Usuario();
	
	
	
	
	public Compra(){
		
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getComentado() {
		return comentado;
	}

	public void setComentado(int comentado) {
		this.comentado = comentado;
	}

	
	
	
	
}
