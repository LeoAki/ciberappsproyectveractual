package com.monkar.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="sub_categoria")
public class Sub_categoria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSub_categoria;
	private String nombre;
	
	@OneToMany(mappedBy="sub_categoria")
	private List<Aplicacion> aplicaciones;
	
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria=new Categoria();
	
	
	public int getIdSub_categoria() {
		return idSub_categoria;
	}

	public void setIdSub_categoria(int idSub_categoria) {
		this.idSub_categoria = idSub_categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Aplicacion> getAplicaciones() {
		return aplicaciones;
	}

	public void setAplicaciones(List<Aplicacion> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}


	
	
}
