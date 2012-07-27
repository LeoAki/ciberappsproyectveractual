package com.monkar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.monkar.bean.Categoria;
import com.monkar.bean.Sub_categoria;
import com.monkar.constants.Querys;
import com.monkar.service.CategoriaService;

public class CategoriaDAO implements CategoriaService{

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("monkar");
	EntityManager em=emf.createEntityManager();
	
	
//	@Override
	public ArrayList<Categoria> listar() throws Exception {
		
		ArrayList<Categoria> categorias=new ArrayList<Categoria>();
		try {
			em.getTransaction().begin();
			Query q=em.createQuery(Querys.LISTAR_CATEGORIAS_TODO);
			List lista=q.getResultList();
			Categoria cat;
			for(int i=0;i<lista.size();i++){
				cat=(Categoria)lista.get(i);
//				System.out.println("CATEGORIA :"+cat.getNombre());
				for (Sub_categoria sb : cat.getSub_categorias()) {
//					System.out.println("\t\tSUB CATEGORIA"+sb.getNombre());
				}
				categorias.add(cat);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.getTransaction().rollback();
			categorias =null;
		}
			return categorias;
	}
	
//	@Override
	public ArrayList<Sub_categoria> listarSub() throws Exception {
		
		ArrayList<Sub_categoria> sub_categorias=new ArrayList<Sub_categoria>();
		try {
			em.getTransaction().begin();
			Query q=em.createQuery("select a from Sub_categoria a ");
			List lista=q.getResultList();
			Sub_categoria cat;
			for(int i=0;i<lista.size();i++){
				cat=(Sub_categoria)lista.get(i);
				sub_categorias.add(cat);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.getTransaction().rollback();
			sub_categorias =null;
		}
			return sub_categorias;
	}
	
	public Sub_categoria obtenerXNombre( Sub_categoria sc) throws Exception {
		
		ArrayList<Sub_categoria> sub_categorias=new ArrayList<Sub_categoria>();
		try {
			em.getTransaction().begin();
			Query q=em.createQuery("select sc from Sub_categoria sc  where sc.nombre = :nombre");
			q.setParameter("nombre",sc.getNombre());
			q.setMaxResults(1);
			List lista=q.getResultList();
			Sub_categoria cat;
			for(int i=0;i<lista.size();i++){
				cat=(Sub_categoria)lista.get(i);
				sub_categorias.add(cat);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.getTransaction().rollback();
			sub_categorias =null;
		}
			return sub_categorias.get(0);
	}
}
