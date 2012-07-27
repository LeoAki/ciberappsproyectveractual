package com.monkar.bean;




import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="opcion")
public class Opcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOpcion;

	private String accion;

	private String nombre;

	//bi-directional many-to-many association to Rol
    @ManyToMany
   	@JoinTable(
   		name="acceso"
   		, joinColumns={
   			@JoinColumn(name="idOpcion")
   			}
   		, inverseJoinColumns={
   			@JoinColumn(name="idRol")
   			}
   		)
	private List<Rol> rols;

    public Opcion() {
    }

	public int getIdOpcion() {
		return this.idOpcion;
	}

	public void setIdOpcion(int idOpcion) {
		this.idOpcion = idOpcion;
	}

	public String getAccion() {
		return this.accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Rol> getRols() {
		return rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}


	
}
