package com.monkar.bean;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name="aplicacion")
public class Aplicacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAplicacion;
	
	private String codigo;
	private String nombre;
	private String breveDescripcion;
	private String imagen_logo;
	private String imagen_min;
	private String imagen_big;
	private String fec_registro;
	private int num_descargas;
	private int puntuacion;
	private double precio;
	private String tags;
	
	private String subtitulo;
	
	
	private String descripcionTotal;
	private String version;
	private String fec_ultima_modificacion;
	
	
	private String ruta_aplicacion;
	
	@ManyToOne
	@JoinColumn(name="idDesarrollador")
	private Desarrollador desarrollador= new Desarrollador();
	
	@ManyToOne
	@JoinColumn(name="idSub_categoria")
	private Sub_categoria sub_categoria= new Sub_categoria();
	
	
	
	@OneToMany(mappedBy="aplicacion")
	private List<Compra> compras;
	
	@OneToMany(mappedBy="aplicacion")
	private List<Imagen> imagenes;
	
	
	public Aplicacion(){
		compras= new ArrayList<Compra>();
		imagenes = new ArrayList<Imagen>();
	}
	

	public int getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(int idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getBreveDescripcion() {
		return breveDescripcion;
	}

	public void setBreveDescripcion(String breveDescripcion) {
		this.breveDescripcion = breveDescripcion;
	}

	public String getImagen_logo() {
		return imagen_logo;
	}

	public void setImagen_logo(String imagen_logo) {
		this.imagen_logo = imagen_logo;
	}

	public String getImagen_min() {
		return imagen_min;
	}

	public void setImagen_min(String imagen_min) {
		this.imagen_min = imagen_min;
	}

	public String getImagen_big() {
		return imagen_big;
	}

	public void setImagen_big(String imagen_big) {
		this.imagen_big = imagen_big;
	}

	public String getFec_registro() {
		return fec_registro;
	}

	public void setFec_registro(String fec_registro) {
		this.fec_registro = fec_registro;
	}

	public int getNum_descargas() {
		return num_descargas;
	}

	public void setNum_descargas(int num_descargas) {
		this.num_descargas = num_descargas;
	}

	public int getPuntuacion() {
		return puntuacion;
	}



	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Desarrollador getDesarrollador() {
		return desarrollador;
	}

	public void setDesarrollador(Desarrollador desarrollador) {
		this.desarrollador = desarrollador;
	}

	public Sub_categoria getSub_categoria() {
		return sub_categoria;
	}

	public void setSub_categoria(Sub_categoria sub_categoria) {
		this.sub_categoria = sub_categoria;
	}

	public String getDescripcionTotal() {
		return descripcionTotal;
	}
	
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public void setDescripcionTotal(String descripcionTotal) {
		this.descripcionTotal = descripcionTotal;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	


	public String getRuta_aplicacion() {
		return ruta_aplicacion;
	}


	public void setRuta_aplicacion(String ruta_aplicacion) {
		this.ruta_aplicacion = ruta_aplicacion;
	}


	public String getFec_ultima_modificacion() {
		return fec_ultima_modificacion;
	}

	public void setFec_ultima_modificacion(String fec_ultima_modificacion) {
		this.fec_ultima_modificacion = fec_ultima_modificacion;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}


	public List<Imagen> getImagenes() {
		return imagenes;
	}


	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}
	
	
	
	
	
	
	

}
