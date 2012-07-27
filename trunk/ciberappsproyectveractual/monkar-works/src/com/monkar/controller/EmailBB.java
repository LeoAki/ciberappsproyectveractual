package com.monkar.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;


import com.monkar.bean.Usuario;
import com.monkar.email.enviaMail;

@ManagedBean
@RequestScoped
public class EmailBB {
	
	private String titulo;
	private String cuerpo;
	
	ExternalContext G = FacesContext.getCurrentInstance().getExternalContext();
	HttpSession session = (HttpSession) G.getSession(true);
	
	public void enviaMensaje(ActionEvent event){
		RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean paso = true;  
        
        Usuario user = (Usuario) session.getAttribute("UsuarioSesion");
		
		try {
			
			System.out.println(titulo);

			if(user!=null){
				enviaMail.sendEmail(titulo, cuerpo + "\nResponder a:"+ user.getNombre()+" "+user.getApellido()+" \n Email: "+user.getEmail());
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje Enviado ", user.getNombre());
			}
			else {
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje No Enviado ", "Debe Registrarse"); 
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);  
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje No Enviado ", user.getNombre()); 
			paso= false;
		}
		
		context.addCallbackParam("paso", paso); 
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	

}
