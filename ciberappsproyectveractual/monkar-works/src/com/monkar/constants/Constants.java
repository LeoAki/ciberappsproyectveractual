package com.monkar.constants;

import java.io.File;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.monkar.bean.Aplicacion;
import com.monkar.bean.Usuario;


public class Constants implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static ExternalContext G = FacesContext.getCurrentInstance().getExternalContext();
	static HttpSession session = (HttpSession) G.getSession(true);
	
	
public static final  int 

	NUMERO_APLICACIONES_MOSTRAR=40,
	NUMERO_APLICACIONES_CABECERA_MOSTRAR=6,
	INFO=1,
	ALERTA=2,
	PELIGRO=3,
	MENSAJE=4,


	INDEX_FILTRO_INDEX=1,
	
	INDEX_FILTRO_NO_INDEX=2,
	
	INDEX_SUBIR_APLICACION=3,

	INDEX_AGREGAR_USUARIO=4;

	public static final String directorioAplicaciones= "C:\\Users\\OSCAR\\workspace_bk\\monkar-monkar-works-370d6461f46e\\monkar-works\\WebContent\\apps\\";
	

//	PARA CONOCER CUAL ES EL ENTORNO
public static final void mostrarMensaje(String titulo,String mensaje,int tipo){
	 FacesMessage msg = null;  
	 if(tipo==Constants.INFO)
		 msg = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo,mensaje); 
	 else if(tipo==Constants.ALERTA)
		 msg = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo,mensaje);
	 else if(tipo==Constants.PELIGRO)
		 msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo,mensaje);
	 FacesContext.getCurrentInstance().addMessage(null, msg); 
}

//METODO OBTENER USUARIO DE SESION
public static Usuario obtenerUsuarioDeSesion(Usuario usuario){
	try {
		if(session.getAttribute("UsuarioSesion")!=null){
			System.out.println("usuario no null");
			usuario=(Usuario) session.getAttribute("UsuarioSesion");
		}
	} catch (Exception e) {
		System.out.println("Excepcion en Constant/obtenerUsuarioDeSesion");
	}
	finally{
		if(usuario==null){
			usuario=new Usuario();
		}
		
	}
	return usuario;
}
//METODO OBTENER USUARIO DE SESION
public static Aplicacion obtenerAplicacionMostradaDeSesion(Aplicacion aplicacion){
	try {
		if(session.getAttribute("AplicacionSesion")!=null){
			aplicacion=(Aplicacion) session.getAttribute("AplicacionSesion");
		}
	} catch (Exception e) {
		System.out.println("Excepcion en Constant/obtenerAplicacionMostradaDeSesion");
	}
	finally{
		if(aplicacion==null){
			aplicacion=new Aplicacion();
		}
		
	}
	return aplicacion;
}



}
