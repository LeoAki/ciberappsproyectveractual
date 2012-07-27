package com.monkar.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import com.monkar.bean.General;
import com.monkar.bean.Opcion;
import com.monkar.bean.Rol;
import com.monkar.bean.Usuario;
import com.monkar.constants.Constants;
import com.monkar.hilos.HiloUsuario;
import com.monkar.service.UsuarioImplementacion;


@ManagedBean
@SessionScoped
public class UsuarioBB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public static int contador=0;
	
	ExternalContext G = FacesContext.getCurrentInstance().getExternalContext();
	HttpSession session = (HttpSession) G.getSession(true);
	
	private UsuarioImplementacion usuarioImp;
	
	
//	solo necesito esto
	private Usuario usuario;
	private MenuModel mnOpcionesUsuario;
	
	private Usuario usuario1;

	private String contrasenia,contrasenia1;
	
	private boolean activo;
	
	public UsuarioBB(){
		usuarioImp = new UsuarioImplementacion();
		mnOpcionesUsuario=new DefaultMenuModel();
		usuario = new Usuario();
		usuario1= new Usuario();
		usuario=Constants.obtenerUsuarioDeSesion(usuario);
		

		
	}
	
//	METODO DE ACCESO
	public void login(ActionEvent actionEvent) throws Exception {
		RequestContext context = RequestContext.getCurrentInstance();
		String msgTitulo = "Error al Acceder";
		String msgContenido= "Credenciales Invalidas";
		int msgTipo=Constants.ALERTA;
		boolean loggedIn = false;
		try {
			
			System.out.println("Entrara al buscar usuario con pss");
			Usuario u;
			if (usuarioImp.obtenerUsuarioXUsuario(usuario) != null) {
				 u = usuarioImp.obtenerUsuarioXUsuario(usuario);
				if (u.getActivo()==1) {
					session.setAttribute("UsuarioSesion", u);
					loggedIn = true;
					msgTitulo = "Bienvenido ";
					msgContenido= usuario.getUsuario();
					msgTipo=Constants.INFO;
					
					crearMenuSesion();
				}else{
					msgTitulo = "Usuario Inactivo ";
					msgContenido= usuario.getUsuario();
					msgTipo=Constants.ALERTA;
				}
				
			}
			
			//metodo para bloquear cuenta
			else{
				System.out.println("Entrara al buscar usuario sin pss");
				 System.out.println("buscara si existe");
				if (usuarioImp.obtenerUsuarioXUsuarioValidate(usuario) != null) {
					u = usuarioImp.obtenerUsuarioXUsuarioValidate(usuario);
						contador++;
					if (contador==3){
						System.out.println("El contador es igual a 3");
						u.setActivo(0);
						usuarioImp.ActualizaPersona(u);
						HiloUsuario hilo= new HiloUsuario(u);
						Thread hiloUser = new Thread(hilo);
						hiloUser.start();
						msgTitulo = "Numero de intentos Max 3";
						msgContenido="Se bloqueo la cuenta: "+ usuario.getUsuario()+ "\n vuelva a intentarlo dentro de 2 hrs";
						msgTipo=Constants.ALERTA;
						
					}else{
						msgTitulo = "Contraseña incorrecta";
						msgContenido="Se bloqueara la cuenta: "+ usuario.getUsuario()+ "\n en "+ (contador-3) +" intentos";
						msgTipo=Constants.ALERTA;
						
					}
					
					
					
					}
					
					
					
				
					
				}
			msgTitulo = "Contraseña incorrecta";
			msgContenido="Se bloqueara la cuenta: "+ usuario.getUsuario()+ "\n en "+ (contador) +" intentos mas";
			msgTipo=Constants.ALERTA;
			
			if(contador==3){
				msgTitulo = "Numero de intentos Max 3";
				msgContenido="Se bloqueo la cuenta: "+ usuario.getUsuario()+ "\n vuelva a intentarlo dentro de 2 hrs";
				msgTipo=Constants.ALERTA;
			}else {
				System.out.println("paso todo");
				Constants.mostrarMensaje(msgTitulo, msgContenido, msgTipo);
				context.addCallbackParam("loggedIn", loggedIn);
				System.out.println(msgTitulo + msgContenido + msgTipo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Constants.mostrarMensaje(msgTitulo, msgContenido, msgTipo);
			context.addCallbackParam("loggedIn", loggedIn);
		}

	}

	 
//	 CERRAR SESION
	 public void logout(ActionEvent actionEvent) {  
		 	try {
				session.removeAttribute("UsuarioSesion");
				String msgTitulo = "Sesion Finalzada";
				String msgContenido= "Bye :(";
				int msgTipo=Constants.INFO;
				
				eliminarMenuSesion();
				Constants.mostrarMensaje(msgTitulo, msgContenido, msgTipo);
				
				usuario = new Usuario();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    } 
	 
	 private void eliminarMenuSesion() throws Exception {
		 mnOpcionesUsuario = new DefaultMenuModel();
	 }

	 private void crearMenuSesion(){
		 System.out.println("entro a crear menu");
			List<General> list = listarOpcionesSesion();
			    for(General g:list){
			    	System.out.println("ITEM");
			    	MenuItem item = new MenuItem();  
			        item.setValue(g.getValor1());  
			        ExpressionFactory factory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
			        
//		        (*)hacer dinamica la parte del envio a un controlador no estatico
			        MethodExpression methodExpression = 
			        		factory.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), 
			        				"#{aplicacionBB."+g.getValor2()+"}", String.class, new Class[]{});
			        item.setActionExpression(methodExpression);
//		        (*)dinamico que paneles se actualizaran
			        item.setUpdate(":frmCenterCenter,:growl");
			    	
			        mnOpcionesUsuario.addMenuItem(item);
			    }
	 }
	 
	private List<General> listarOpcionesSesion(){
		List<General> list = new ArrayList<General>();
		try {
			Usuario u= new Usuario();
			u = Constants.obtenerUsuarioDeSesion(u);
			for (Opcion o : u.getRol().getOpcions()) {
				General c = new General();
				c.setValor1(o.getNombre());
				c.setValor2(o.getAccion());
				// (*)agregar icono
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion UsuarioBB/listarOpcionesSesion");
		}
		return list;
	}
	
	
	 public String agregarUsuario(ActionEvent actionEvent){
		 RequestContext context = RequestContext.getCurrentInstance();
		 
		 	boolean paso= true;
		 	
			System.out.println("1");
			
			Rol rol= new Rol();
			rol.setIdRol(1);
			usuario1.setRol(rol);
			System.out.println("2");
			
			usuario1.setActivo(1);
			FacesMessage msg = null;  
			
			try {
				if (usuarioImp.obtenerUsuarioXUsuarioValidate(usuario1)==null){
				System.out.println("3");
				usuarioImp.GrabaPersona(usuario1);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Agregado ","Nuevo Usuario: "+ usuario1.getNombre()); 
				}else{
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario Invalido", "El usuario ya existe");
					paso=false;
				} 
					
				System.out.println("4");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				paso=false;
			}
			FacesContext.getCurrentInstance().addMessage(null, msg); 
			
	        context.addCallbackParam("paso", paso); 
	        return "grabo";
		}
	 
	 public void  validateMismaContra(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException{
		    String fieldVal = (String)object;

		    Usuario user = (Usuario) session.getAttribute("UsuarioSesion");
		    if(!user.getContrasenia().equalsIgnoreCase(fieldVal)){
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Verifique su contraseña","Contraseña incorrecta");
		        throw new ValidatorException(message);
		    }
		} 
	 
	 public void actualizarPass(ActionEvent event){
		 RequestContext context = RequestContext.getCurrentInstance();
		 	boolean paso= true;
		 FacesMessage msg = null;
		 try {
			 
			 Usuario user = (Usuario) session.getAttribute("UsuarioSesion");
			 
			 if(activo) user.setActivo(0);
			 
			 user.setContrasenia(contrasenia1);
			 System.out.println(user.toString());
			 
			 
			usuarioImp.ActualizaPersona(user);
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Actualizado","Usuario: "+ user.getNombre());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario No Actualizado","Usuario: "+ usuario1.getNombre());
			paso=false;
		}
		 FacesContext.getCurrentInstance().addMessage(null, msg); 
		 
			
	        context.addCallbackParam("paso", paso); 
	 }
	
//	GETTERS Y SETTERS
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public MenuModel getMnOpcionesUsuario() {
		return mnOpcionesUsuario;
	}

	public void setMnOpcionesUsuario(MenuModel mnOpcionesUsuario) {
		this.mnOpcionesUsuario = mnOpcionesUsuario;
	}

	public Usuario getUsuario1() {
		return usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getContrasenia1() {
		return contrasenia1;
	}

	public void setContrasenia1(String contrasenia1) {
		this.contrasenia1 = contrasenia1;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	 
	

	

}
