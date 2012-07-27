package com.monkar.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="categoria")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCategoria;
	private String nombre;
	
	
	
	@OneToMany(mappedBy="categoria")
	private List<Sub_categoria> sub_categorias;



	public int getIdCategoria() {
		return idCategoria;
	}



	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public List<Sub_categoria> getSub_categorias() {
		return sub_categorias;
	}



	public void setSub_categorias(List<Sub_categoria> sub_categorias) {
		this.sub_categorias = sub_categorias;
	}

	
	
	
	
}
