package com.monkar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.monkar.bean.Aplicacion;
import com.monkar.bean.Compra;
import com.monkar.bean.Usuario;
import com.monkar.constants.Querys;
import com.monkar.service.CompraService;

public class CompraDAO implements CompraService{

	
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("monkar");
	EntityManager em=emf.createEntityManager();
	
	@Override
	public String grabar(Compra compra) throws Exception {
		String mensaje="";
		boolean existe = false;
			em=emf.createEntityManager();
			try {
//				valido que no existan compras similares
				for(Compra c :listaCompra()){
					if(compra.getUsuario().getIdUsuario()==c.getUsuario().getIdUsuario()
							 && compra.getAplicacion().getIdAplicacion() == c.getAplicacion().getIdAplicacion()){
						existe = true;
					}
				}
				
				if(!existe){
					em.getTransaction().begin();
					em.persist(compra);
					em.getTransaction().commit();
					mensaje="Se grabó la compra";
					
					aumentarContadorDeLaAplicacion(compra.getAplicacion());
					mensaje="GRABO";
				}else{
					mensaje="El usuario ya compró esta aplicación";
					aumentarContadorDeLaAplicacion(compra.getAplicacion());
					mensaje="MODIFICO";
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				em.getTransaction().rollback();
				System.out.println(e);
				e.printStackTrace();
				mensaje="ERRROR AL GRABAR LA COMPRA EN EL DAO";
			}finally{
				 em.close();
			}
			System.err.println("mensaje");
			return mensaje;
	}
	
	private List<Compra> listaCompra() {
		List<Compra> compras = new ArrayList<Compra>();
		EntityManager emx=emf.createEntityManager();
		try {
			
			emx.getTransaction().begin();
			Query q = em.createQuery(Querys.LISTAR_COMPRAS);
			@SuppressWarnings("rawtypes")
			List lista = q.getResultList();
			Compra compra;
			for (int i = 0; i < lista.size(); i++) {
				compra = (Compra) lista.get(i);
				compras.add(compra);
			}
			emx.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			emx.getTransaction().rollback();
		}
		finally{
			emx.close();
		}
		
		return compras;
	}
	
	private void aumentarContadorDeLaAplicacion(Aplicacion aplicacion){
		  try{
			em=emf.createEntityManager();
		    em.getTransaction().begin();
		    aplicacion = em.find(Aplicacion.class, aplicacion.getIdAplicacion());
		    aplicacion.setNum_descargas(aplicacion.getNum_descargas()+1);
		    em.getTransaction().commit();
		  } catch(Exception e){
			  System.out.println("no se pudo aumentar las descargas");
		  }finally {
		  }
	}
}
