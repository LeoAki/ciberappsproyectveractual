package com.monkar.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.fileupload.FileItem;
import org.jfree.chart.axis.SubCategoryAxis;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

import com.monkar.bean.Aplicacion;
import com.monkar.bean.Categoria;
import com.monkar.bean.Sub_categoria;
import com.monkar.bean.Usuario;
import com.monkar.constants.Constants;
import com.monkar.dao.AplicacionDAO;
import com.monkar.service.AplicacionImplementacion;
import com.monkar.service.CategoriaImplementacion;

@ManagedBean
@SessionScoped
public class AplicacionRegistroBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Aplicacion aplicacion;
	
    private boolean skip;  
	private static Logger logger = Logger.getLogger(AplicacionRegistroBB.class.getName());  
	
	private List<FileUploadEvent> imagenesAlmacenadas = new ArrayList<FileUploadEvent>();
	
	private AplicacionImplementacion aplicacionImp;
	private CategoriaImplementacion categoriaImp;
	
	private List<Categoria> listaCategorias;
	private Categoria categoria;
	private Sub_categoria sub_Categoria;
	private List<Sub_categoria> listaSubCategorias;
	
	private int idSub_categoria;
	    
	 public AplicacionRegistroBB(){
		 aplicacion = new Aplicacion();
		 aplicacionImp = new AplicacionImplementacion();
		 categoriaImp = new CategoriaImplementacion();
		 
		 
		 listaCategorias=new ArrayList<Categoria>();
		 listaSubCategorias=new ArrayList<Sub_categoria>();
//		 categoria = new Categoria();
		 sub_Categoria = new Sub_categoria();
		 cargarComboSubCategorias();
	 }
	
//	 private void cargarComboCategorias(){
//		 try {
//			listaCategorias= categoriaImp.listar();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	 }
	 
	 private void cargarComboSubCategorias(){
		 try {
			listaCategorias= categoriaImp.listar();
			for(Categoria c:listaCategorias){
            		for(Sub_categoria sc:c.getSub_categorias()){
            			listaSubCategorias.add(sc);
            		}
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 
	 public void actualizarSub_Categorias(){
		 System.out.println("Actualiza");
//		 if(categoria !=null)  
//	            for(Categoria c:listaCategorias){
//	            	if(c.getIdCategoria()==categoria.getIdCategoria()){
//	            		for(Sub_categoria sc:c.getSub_categorias()){
//	            			listaSubCategorias.add(sc);
//	            		}
//	            	}
//	            }
//	        else  
//	        	listaSubCategorias= new ArrayList<Sub_categoria>();
	 }
	 
	 public void actualizarSub_Categorias(AjaxBehaviorEvent event){
		 System.out.println("Actualiza");
	 }
	 
	 
	 public void save(ActionEvent actionEvent) {  
	        //Persist user  
	        Constants.mostrarMensaje("EXITO", "aplicacion guardada "+aplicacion.getNombre(),1);
	        System.out.println("APLICACION "+aplicacion.getNombre());
	        
	        try {
	        	Usuario usuario = new Usuario(); 
	        	usuario = Constants.obtenerUsuarioDeSesion(usuario);
	        	System.out.println("SUB CATEGORIA :"+idSub_categoria);
	        	Sub_categoria sc = new Sub_categoria();
	        	sc.setIdSub_categoria(idSub_categoria);
	        	aplicacion.setSub_categoria(sc);
	        	aplicacion.setDesarrollador(usuario.getDesarrolladors().get(0));
	        	String xxx= aplicacionImp.grabar(aplicacion, imagenesAlmacenadas);
	        	System.out.println("RESULTADO DE LA INSERCION "+xxx);
	        	
	        	
	        	Constants.mostrarMensaje("Aplicacion Guardada", "La Aplicacion re registro correctamente : ]", 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }  
	 public void handleFileUpload(FileUploadEvent event) { 
		 
		 try {
			 byte[] file = event.getFile().getContents();
	         imagenesAlmacenadas.add(event);
//	         System.out.println("TIPÖ :"+event.getFile().getContentType());
//	         System.out.println("NOMBRE :"+event.getFile().getFileName());
	         
	         
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 Constants.mostrarMensaje("IMAGEN SUBIDA", "Se subio la imagen",1);
		 
	    }  
	 
	 
	 public boolean isSkip() {  
	        return skip;  
	    }  
	  
	    public void setSkip(boolean skip) {  
	        this.skip = skip;  
	    }  
	      
	    public String onFlowProcess(FlowEvent event) {  
	    	
	    	System.out.println("Entro al next");
	        logger.info("Current wizard step:" + event.getOldStep());  
	        logger.info("Next step:" + event.getNewStep());  
	          
	        if(skip) {  
	            skip = false;   //reset in case user goes back  
	            return "confirm";  
	        }  
	        else {  
	            return event.getNewStep();  
	        }  
	    }


		public Aplicacion getAplicacion() {
			return aplicacion;
		}


		public void setAplicacion(Aplicacion aplicacion) {
			this.aplicacion = aplicacion;
		}

		public List<Categoria> getListaCategorias() {
			return listaCategorias;
		}

		public void setListaCategorias(List<Categoria> listaCategorias) {
			this.listaCategorias = listaCategorias;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}

		public Sub_categoria getSub_Categoria() {
			return sub_Categoria;
		}

		public void setSub_Categoria(Sub_categoria sub_Categoria) {
			this.sub_Categoria = sub_Categoria;
		}

		public List<Sub_categoria> getListaSubCategorias() {
			return listaSubCategorias;
		}

		public void setListaSubCategorias(List<Sub_categoria> listaSubCategorias) {
			this.listaSubCategorias = listaSubCategorias;
		}

		public int getIdSub_categoria() {
			return idSub_categoria;
		}

		public void setIdSub_categoria(int idSub_categoria) {
			this.idSub_categoria = idSub_categoria;
		}
		
		
		
	    
	    
}
