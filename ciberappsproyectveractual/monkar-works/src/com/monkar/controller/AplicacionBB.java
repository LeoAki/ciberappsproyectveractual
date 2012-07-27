package com.monkar.controller;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.monkar.bean.Aplicacion;
import com.monkar.bean.Compra;
import com.monkar.bean.Sub_categoria;
import com.monkar.bean.Usuario;
import com.monkar.constants.Constants;
import com.monkar.service.AplicacionImplementacion;
import com.monkar.service.CategoriaImplementacion;
import com.monkar.service.CompraImplementacion;

@ManagedBean
@SessionScoped
public class AplicacionBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6740514196088979953L;
	private List<Aplicacion> listaAplicaciones;
	private List<Aplicacion> listaTopCabecera;
	private Aplicacion aplicacion;
	private Compra compra;
	
	private StreamedContent file;

	private AplicacionImplementacion aplicacionImp;
	private CompraImplementacion compraImp;
	private CategoriaImplementacion categoriaImp;
	
	private String tag;
	
	private int index;
	// para recibir parametros
	ExternalContext G = FacesContext.getCurrentInstance().getExternalContext();
	HttpSession session = (HttpSession) G.getSession(true);
	
	// constructor
	public AplicacionBB() {
		aplicacionImp = new AplicacionImplementacion();
		compraImp= new CompraImplementacion();
		categoriaImp = new CategoriaImplementacion();
		index = Constants.INDEX_FILTRO_INDEX;
		listaAplicaciones= new ArrayList<Aplicacion>();
		aplicacion= new Aplicacion();
		compra= new Compra();
		aplicacion=Constants.obtenerAplicacionMostradaDeSesion(aplicacion);
		
		
		filtrarAplicaciones("empty");
		
		if(index==Constants.INDEX_FILTRO_INDEX){
			obtenerAplicacionesCabecera();
		}

	}
	
	public void misAplicaciones() throws Exception{
		filtrarAplicaciones("MisDescargas");
		Constants.mostrarMensaje("Accion", "Mis Aplicaciones Descargadas", Constants.INFO);
	}
	public void subirAplicacion() throws Exception{
		Constants.mostrarMensaje("Accion", "Subir Aplicacion", Constants.INFO);
//		este es el prototipo de masomenos como  seria el metodo Dx
		index = Constants.INDEX_SUBIR_APLICACION;
	}
	
	public void agregarUsuario(ActionEvent event){
		System.out.println("Entro a Agregar Usuario");
		Constants.mostrarMensaje("Accion", "Agregar Usuario", Constants.INFO);
//		este es el prototipo de masomenos como  seria el metodo Dx
		index = Constants.INDEX_AGREGAR_USUARIO;
	}
	
	public void moderar() throws Exception{
		Constants.mostrarMensaje("Accion", "Moderar", Constants.INFO);
	}
	
	public void listarVacio() throws Exception{
		filtrarAplicaciones("empty");
	}
	
	public void listarMasDescargados() throws Exception{
		filtrarAplicaciones("MasDescargados");
		Constants.mostrarMensaje("Filtro", "Mas Descargados", Constants.INFO);
	}
	public void listarMejorValorados() throws Exception{
		filtrarAplicaciones("MejorValorados");
		Constants.mostrarMensaje("Filtro", "Mejor Valorados", Constants.INFO);
	}
	public void listarDelMomento() throws Exception{
		filtrarAplicaciones("DelMomento");
		Constants.mostrarMensaje("Filtro", "Del Momento", Constants.INFO);
	}
	
//	aca inicia la ñanga
	
	public void filtrar_Relojes() throws Exception{
		filtrarAplicaciones("Relojes");
		Constants.mostrarMensaje("Filtro", "Relojes", Constants.INFO);
	}
	public void filtrar_Calculadora() throws Exception{
		filtrarAplicaciones("Calculadora");
		Constants.mostrarMensaje("Filtro", "Calculadora", Constants.INFO);
	}
	public void filtrar_Blocks() throws Exception{
		filtrarAplicaciones("Blocks");
		Constants.mostrarMensaje("Filtro", "Blocks", Constants.INFO);
	}
	public void filtrar_Puzles() throws Exception{
		filtrarAplicaciones("Puzles");
		Constants.mostrarMensaje("Filtro", "Puzles", Constants.INFO);
	}
	public void filtrar_Mesa() throws Exception{
		filtrarAplicaciones("Mesa");
		Constants.mostrarMensaje("Filtro", "Mesa", Constants.INFO);
	}
	public void filtrar_Arcade() throws Exception{
		filtrarAplicaciones("Arcade");
		Constants.mostrarMensaje("Filtro", "Arcade", Constants.INFO);
	}
	public void filtrar_Academicos() throws Exception{
		filtrarAplicaciones("Academicos");
		Constants.mostrarMensaje("Filtro", "Academicos", Constants.INFO);
	}
	public void filtrar_Idiomas() throws Exception{
		filtrarAplicaciones("Idiomas");
		Constants.mostrarMensaje("Filtro", "Idiomas", Constants.INFO);
	}
	
	private void obtenerAplicacionesCabecera() {
		try {
			listaTopCabecera = aplicacionImp
					.listarTopDescargas(Constants.NUMERO_APLICACIONES_CABECERA_MOSTRAR);
		} catch (Exception e) {
			System.out.println("Exception AplicacionBB/obtenerAplicacionesCabecera");
		}

	}
	// metodo que filtra por subcategoria
	private void filtrarAplicaciones(String param) {
		try {
			switch (param) {
			case "empty":
				listaAplicaciones = aplicacionImp
						.listar(Constants.NUMERO_APLICACIONES_MOSTRAR);
				index=Constants.INDEX_FILTRO_INDEX;
				break;
			case "MasDescargados":
				listaAplicaciones = aplicacionImp
						.listarTopDescargas(Constants.NUMERO_APLICACIONES_MOSTRAR);
				index=Constants.INDEX_FILTRO_NO_INDEX;
				break;
			case "DelMomento":
				listaAplicaciones =  aplicacionImp
						.listar(Constants.NUMERO_APLICACIONES_MOSTRAR);
				index=Constants.INDEX_FILTRO_NO_INDEX;
				break;
			case "MejorValorados":
				listaAplicaciones = aplicacionImp
						.listarTopValoradas(Constants.NUMERO_APLICACIONES_MOSTRAR);
				index=Constants.INDEX_FILTRO_NO_INDEX;
				break;
			
			case "MisDescargas":
				Usuario usu = new Usuario();
				usu = Constants.obtenerUsuarioDeSesion(usu);
				listaAplicaciones = aplicacionImp
						.listarXUsuario(usu);
				index=Constants.INDEX_FILTRO_NO_INDEX;
				break;

			default:
				
				Sub_categoria sub = new Sub_categoria();
				sub.setNombre(param);
				sub = categoriaImp.obtenerXNombre(sub);
				listaAplicaciones = aplicacionImp.filtrarXSub_categoria(sub);
				index=Constants.INDEX_FILTRO_NO_INDEX;
				break;
			}

		} catch (Exception e) {
			System.out.println("Exception AplicacionBB/filtrarAplicaciones");
		}
	}
	
	public List<String> complete(String query){
		try {
			List<String> results = new ArrayList<String>();
			
			for (String str : aplicacionImp.filtrarXTag()) {
				if(str.startsWith(query))
					results.add(str);
				
			}

			return results;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void listarxtag(){
		try {
			listaAplicaciones.clear();
			System.out.println("TAG :"+tag);
			listaAplicaciones= aplicacionImp.filtrarXTag(tag);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void  mostrarDetalles(ActionEvent event) throws Exception{
		
		compra=null;
		compra= new Compra();
		System.out.println("ENTRO A MOSTRAR DETALLE");
		String xxx  = event.getComponent().getAttributes().get("idAplicacion").toString();
		Aplicacion app = new Aplicacion();
		app.setIdAplicacion(Integer.parseInt(xxx));
		app= aplicacionImp.obtenerAplicacion(app);
		session.setAttribute("AplicacionSesion", app);
		aplicacion = Constants.obtenerAplicacionMostradaDeSesion(app);
		
		
//		capturo si existe la compra
		Usuario u= new Usuario();
		u=Constants.obtenerUsuarioDeSesion(u);
		System.out.println("ID USUARIO :"+u.getIdUsuario());
		for(Compra c: app.getCompras()){
			System.out.println("ID USUARIO EN COMPRA :"+c.getUsuario().getIdUsuario());
			if(c.getUsuario().getIdUsuario()==u.getIdUsuario()){
				compra=c;
				System.out.println("Compra actualizada :"+c.getIdCompra());
			}
		}
	}
	
	public String comprarAplicacion() throws Exception{
		System.out.println("SE COMPRA LA APLICACION");
		String msgTitulo = "Ocurrio un Problema";
		String msgContenido= "Lo sentimos, la descarga no se realizó con éxito.";
		int msgTipo=Constants.ALERTA;
		
		try {
		Compra compra = new Compra();
		Aplicacion a= new Aplicacion();
		Usuario u = new Usuario();
		
		compra.setAplicacion(Constants.obtenerAplicacionMostradaDeSesion(a));
		compra.setUsuario(Constants.obtenerUsuarioDeSesion(u));
		compra.setComentario("Vacio");
		String aux = compraImp.grabar(compra);
		
		msgTitulo="Descarga Realizada";
		msgContenido="Aplicacion "+compra.getAplicacion().getNombre()+" comprada con Exito :] ";
		msgTipo=Constants.INFO;
		
		} catch (Exception e) {
			System.out.println("Exception AplicacionBB/comprarAplicacion");
		}
		finally{
			Constants.mostrarMensaje(msgTitulo, msgContenido, msgTipo);
		}
		return "";
	}
	

	// GETTERS AND SETTERS
	public List<Aplicacion> getListaAplicaciones() {
		return listaAplicaciones;
	}

	public void setListaAplicaciones(List<Aplicacion> listaAplicaciones) {
		this.listaAplicaciones = listaAplicaciones;
	}

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}


	public List<Aplicacion> getListaTopCabecera() {
		return listaTopCabecera;
	}

	public void setListaTopCabecera(List<Aplicacion> listaTopCabecera) {
		this.listaTopCabecera = listaTopCabecera;
	}

	


	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public StreamedContent getFile() throws FileNotFoundException {
		
		try{
			Aplicacion app = (Aplicacion) session.getAttribute("aplicacionMostrada");
			 InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/apps/"+app.getRuta_aplicacion());
			 file = new DefaultStreamedContent(stream, app.getRuta_aplicacion());
		}catch (Exception e) {
			System.out.println("ERROR AL LEER EL ARCHIVO");
			// TODO: handle exception
		}
		
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
