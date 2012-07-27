package com.monkar.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.primefaces.event.FileUploadEvent;

import com.lowagie.text.Image;
import com.monkar.bean.Aplicacion;
import com.monkar.bean.Categoria;
import com.monkar.bean.Compra;
import com.monkar.bean.Imagen;
import com.monkar.bean.Sub_categoria;
import com.monkar.bean.Usuario;
import com.monkar.constants.Constants;
import com.monkar.constants.Querys;
import com.monkar.service.AplicacionService;

public class AplicacionDAO implements AplicacionService{

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("monkar");
	EntityManager em=emf.createEntityManager();
	
	
	
	public void open(){		
		emf = Persistence.createEntityManagerFactory("monkar");
		em= emf.createEntityManager();		
	}
	
	public void close(){		
		em.close();	
		emf.close();
			
	}
	
	
	@Override
	public List<Aplicacion> filtrarXSub_categoria(Sub_categoria sc) throws Exception {
              
              ArrayList<Aplicacion> apps=new ArrayList<Aplicacion>();
              try {
                      em.getTransaction().begin();
                      Query q=em.createQuery(Querys.FILTRAR_APLICACIONES_X_SUB_CATEGORIA);
                      q.setParameter("sub_categoria",sc);
                      System.out.println(q);
                      @SuppressWarnings("rawtypes")
                      List lista=q.getResultList();
                      Aplicacion app;
                      for(int i=0;i<lista.size();i++){
                              app=(Aplicacion)lista.get(i);
                              apps.add(app);
                      }
                      em.getTransaction().commit();
              } catch (Exception e) {
                      // TODO: handle exception
                      e.printStackTrace();
                      em.getTransaction().rollback();
                      apps =null;
              }
                      return apps;
	}
	
	
	@Override
	public List<Aplicacion> listar(int cantidad) throws Exception {
              
              ArrayList<Aplicacion> apps=new ArrayList<Aplicacion>();
              try {
                      em.getTransaction().begin();
                      Query q=em.createQuery(Querys.LISTAR_APLICACIONES);
//                      q.setFirstResult(firstPosition);
                      q.setMaxResults(cantidad);
                      System.out.println(q);
                      @SuppressWarnings("rawtypes")
                      List lista=q.getResultList();
                      Aplicacion app;
                      for(int i=0;i<lista.size();i++){
                              app=(Aplicacion)lista.get(i);
                              apps.add(app);
                      }
                      em.getTransaction().commit();
              } catch (Exception e) {
                      // TODO: handle exception
                      e.printStackTrace();
                      em.getTransaction().rollback();
                      apps =null;
              }
                      return apps;
	}
	
	
	@Override
	public List<Aplicacion> listarTopDescargas(int cantidad) throws Exception {
              ArrayList<Aplicacion> apps=new ArrayList<Aplicacion>();
              try {
                      em.getTransaction().begin();
                      Query q=em.createQuery(Querys.LISTAR_APLICACIONES_TOP_DESCARGAS);
//                      q.setFirstResult(firstPosition);
                      q.setMaxResults(cantidad);
                      System.out.println(q);
                      @SuppressWarnings("rawtypes")
                      List lista=q.getResultList();
                      Aplicacion app;
                      for(int i=0;i<lista.size();i++){
                              app=(Aplicacion)lista.get(i);
                              apps.add(app);
                      }
                      em.getTransaction().commit();
              } catch (Exception e) {
                      // TODO: handle exception
                      e.printStackTrace();
                      em.getTransaction().rollback();
                      apps =null;
              }
                      return apps;
	}
	
	@Override
	public List<Aplicacion> listarTopNuevas(int cantidad) throws Exception {
              ArrayList<Aplicacion> apps=new ArrayList<Aplicacion>();
              try {
                      em.getTransaction().begin();
                      Query q=em.createQuery(Querys.LISTAR_APLICACIONES_TOP_NUEVAS);
//                      q.setFirstResult(firstPosition);
                      q.setMaxResults(cantidad);
                      System.out.println(q);
                      @SuppressWarnings("rawtypes")
                      List lista=q.getResultList();
                      Aplicacion app;
                      for(int i=0;i<lista.size();i++){
                              app=(Aplicacion)lista.get(i);
                              apps.add(app);
                      }
                      em.getTransaction().commit();
              } catch (Exception e) {
                      // TODO: handle exception
                      e.printStackTrace();
                      em.getTransaction().rollback();
                      apps =null;
              }
                      return apps;
	}
	
	@Override
	public List<Aplicacion> listarTopValoradas(int cantidad) throws Exception {
              ArrayList<Aplicacion> apps=new ArrayList<Aplicacion>();
              try {
                      em.getTransaction().begin();
                      Query q=em.createQuery(Querys.LISTAR_APLICACIONES_TOP_VALORADAS);
//                      q.setFirstResult(firstPosition);
                      q.setMaxResults(cantidad);
                      System.out.println(q);
                      @SuppressWarnings("rawtypes")
                      List lista=q.getResultList();
                      Aplicacion app;
                      for(int i=0;i<lista.size();i++){
                              app=(Aplicacion)lista.get(i);
                              apps.add(app);
                      }
                      em.getTransaction().commit();
              } catch (Exception e) {
                      // TODO: handle exception
                      e.printStackTrace();
                      em.getTransaction().rollback();
                      apps =null;
              }
                      return apps;
	}


	@Override
	public Aplicacion obtenerAplicacion(Aplicacion a) throws Exception {
		 ArrayList<Aplicacion> apps=new ArrayList<Aplicacion>();
         try {
                 em.getTransaction().begin();
                 Query q=em.createQuery(Querys.OBTENER_APLICACION);
                 q.setParameter("idAplicacion", a.getIdAplicacion());
//                 q.setFirstResult(firstPosition);
                 q.setMaxResults(1);
                 System.out.println(q);
                 @SuppressWarnings("rawtypes")
                 List lista=q.getResultList();
                 Aplicacion app;
                 for(int i=0;i<lista.size();i++){
                         app=(Aplicacion)lista.get(i);
                         apps.add(app);
                 }
                 em.getTransaction().commit();
         } catch (Exception e) {
                 // TODO: handle exception
                 e.printStackTrace();
                 em.getTransaction().rollback();
                 apps =null;
         }
                 return apps.get(0);
	}

	


	@Override
	public List<Aplicacion> listarXUsuario(Usuario usuario) throws Exception {
		 ArrayList<Aplicacion> apps=new ArrayList<Aplicacion>();
         try {
                 em.getTransaction().begin();
                 Query q=em.createQuery(Querys.OBTENER_COMPRA_X_USUARIO);
                 q.setParameter("usuario", usuario.getIdUsuario());
                 @SuppressWarnings("rawtypes")
                 List lista=q.getResultList();
                 Compra compra;
                 for(int i=0;i<lista.size();i++){
                         compra=(Compra)lista.get(i);
                         apps.add(compra.getAplicacion());
                 }
                 em.getTransaction().commit();
         } catch (Exception e) {
                 // TODO: handle exception
                 e.printStackTrace();
                 em.getTransaction().rollback();
         }
                 return apps;
	}


	@Override
	public String grabar(Aplicacion aplicacion,List<FileUploadEvent> imagenesAlmacenadas) throws Exception {
		String codigo;
		String carpeta=Constants.directorioAplicaciones;
		try {
			
		List<Imagen>imagenes= new ArrayList<Imagen>();
			
			
		Aplicacion apAux =obtenerUltimaAplicacion();
		
//		genero el nombre de la carpeta
		int x=10000+apAux.getIdAplicacion()+1;
		codigo="a-"+x;
		
		carpeta+=codigo;
		boolean status = new File(carpeta).mkdir();
		
//		por ahora esto es estatico
//		primero la imagen min
		FileUploadEvent imagenMin=imagenesAlmacenadas.get(0);
		String img_min=grabaFile(imagenMin, carpeta, "img_min");
		
		FileUploadEvent imagenBig=imagenesAlmacenadas.get(1);
		String img_big=grabaFile(imagenBig, carpeta, "img_big");
		
		FileUploadEvent descargable=imagenesAlmacenadas.get(2);
		String ruta_app=grabaFile(descargable, carpeta, "app");
		
		aplicacion.setCodigo(codigo);
		aplicacion.setImagen_min(codigo+"/"+img_min);
		aplicacion.setImagen_big(codigo+"/"+img_big);
		aplicacion.setRuta_aplicacion(codigo+"/"+ruta_app);
		
//		las imagenes
		if(imagenesAlmacenadas.size()>3){
			int y=0;
			for(FileUploadEvent fu:imagenesAlmacenadas){
				if(y>2){
					String carpetaGaleria=carpeta+"/gal";
					boolean st = new File(carpetaGaleria).mkdir();
					
					String ryta_Gal=grabaFile(fu, carpetaGaleria, "gal"+(y-2));
					Imagen img = new Imagen();
					img.setRuta(codigo+"/gal/"+ryta_Gal);
					
					imagenes.add(img);
				}
				y++;
			}
			System.out.println("a setear las imagenes");
		}
			open();
			 em.getTransaction().begin();
			em.persist(aplicacion);
			em.getTransaction().commit();
			

		if(imagenes.size()>0){
			for(Imagen im:imagenes){
				im.getAplicacion().setIdAplicacion(apAux.getIdAplicacion()+1);
				grabarImagen(im);
			}
		}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		close();
		return "SUCCESS";
	}
	
	private String grabarImagen(Imagen imagen) throws Exception{
		EntityManagerFactory emf2=Persistence.createEntityManagerFactory("monkar");
		EntityManager em2=emf2.createEntityManager();
			em2.getTransaction().begin();
			em2.persist(imagen);
			em2.getTransaction().commit();

			// TODO: handle exception
			em2.close();	
			emf2.close();
		return "";
	}
	
	private String grabaFile(FileUploadEvent archivo,String ruta,String nombre){
		String resultado="";
		try {
			
			String tipo;
			tipo = archivo.getFile().getContentType();
			int inicio = tipo.indexOf("/")+1;
			int fin = tipo.length();
			
			System.out.println("TIPO :"+tipo);
			tipo = tipo.substring(inicio, fin);
			System.out.println("TIPO B:"+tipo);
			
			if (tipo.equals("x-ms-dos-executable")) tipo="exe";
			
			
			resultado=nombre+"."+tipo;
			
			
			String name=ruta+"/"+nombre+"."+tipo;
			
			byte[] grupoDeBytes= archivo.getFile().getContents();
			
			
	    	File file = new File(name);
	    	file.createNewFile();
	    	
	    	FileInputStream fileInputStream = new FileInputStream(name);
	    	ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(grupoDeBytes);
	    	OutputStream outputStream = new FileOutputStream(name);
	    	int data;
	    	while ((data = byteArrayInputStream.read()) != -1) {
	    	outputStream.write(data);
	    	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	
	private Aplicacion obtenerUltimaAplicacion() throws Exception {
		 ArrayList<Aplicacion> apps=new ArrayList<Aplicacion>();
        try {
               open();
                Query q=em.createQuery(Querys.OBTENER_ULTIMA_APLICACION_REGISTRADA);
                q.setMaxResults(1);
                System.out.println(q);
                @SuppressWarnings("rawtypes")
                List lista=q.getResultList();
                Aplicacion app;
                for(int i=0;i<lista.size();i++){
                        app=(Aplicacion)lista.get(i);
                        apps.add(app);
                }
        } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                apps =null;
        }finally{
        	close();
        }
                return apps.get(0);
	}
	
	public ArrayList<String> filtrarXTag() throws Exception {
        
        ArrayList<String> tags=new ArrayList<String>();
        try {
                em.getTransaction().begin();
                Query q=em.createQuery(Querys.LISTA_X_TAG2);
                System.out.println(q);
                @SuppressWarnings("rawtypes")
                List lista=q.getResultList();
                String[] app;
                boolean pasa=true;
               
                for(int i=0;i<lista.size();i++){
                	
                		System.out.println(((Aplicacion)lista.get(i)).getTags());
                		
                		if(((Aplicacion)lista.get(i)).getTags()!=null){
                			app=((Aplicacion)lista.get(i)).getTags().split(",");
                        
	                        for (String string : app) {
	                        	
	                        	for (String strg : tags) {
	                        		
	                        		if(strg.equals(string) || strg.equals(""))
	                        			pasa=false;
	                        		
								}
	                        	
	                        	if(pasa)
	                        	tags.add(string);
	                        	pasa=true;
	                        	
							}
                		}
                        
                        
                }
                em.getTransaction().commit();
        } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                em.getTransaction().rollback();
                tags =null;
        }
                return tags;
	}

	public List<Aplicacion> filtrarXTag(String tag) throws Exception {
        
        ArrayList<Aplicacion> apps=new ArrayList<Aplicacion>();
        try {
                em.getTransaction().begin();
                Query q=em.createQuery(Querys.LISTA_X_TAG);
                q.setParameter("tag","%"+tag+"%");
                System.out.println(q);
                @SuppressWarnings("rawtypes")
                List lista=q.getResultList();
                Aplicacion app;
                for(int i=0;i<lista.size();i++){
                        app=(Aplicacion)lista.get(i);
                        apps.add(app);
                }
                em.getTransaction().commit();
        } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                em.getTransaction().rollback();
                apps =null;
        }
                return apps;
	}

}
