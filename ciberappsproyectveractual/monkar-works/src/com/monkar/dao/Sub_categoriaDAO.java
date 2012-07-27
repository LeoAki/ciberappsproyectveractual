package com.monkar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.monkar.bean.Categoria;
import com.monkar.bean.Sub_categoria;

public class Sub_categoriaDAO {

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("monkar");
	EntityManager em=emf.createEntityManager();
	
	
//	@Override
	

	
	
	public ArrayList<Sub_categoria> listar() throws Exception {
		
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
	

}
