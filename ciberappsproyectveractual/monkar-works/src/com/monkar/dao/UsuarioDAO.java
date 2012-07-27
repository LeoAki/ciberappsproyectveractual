package com.monkar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.monkar.bean.Rol;
import com.monkar.bean.Usuario;
import com.monkar.constants.Querys;
import com.monkar.service.UsuarioService;

public class UsuarioDAO implements UsuarioService{

	EntityManagerFactory emf=Persistence.createEntityManagerFactory("monkar");
	EntityManager em=emf.createEntityManager();
	
//	@Override
	public Usuario obtenerUsuarioXUsuario(Usuario usuario) {
		System.out.println("obtenerUsuarioXUsuario");
		ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
		Usuario user;
		try {
			em.getTransaction().begin();
			Query q=em.createQuery(Querys.OBTENER_USUARIO_X_USUARIO);
			 q.setParameter("usuario",usuario.getUsuario());
			 q.setParameter("contrasenia",usuario.getContrasenia());
			List lista=q.getResultList();
			Usuario cat;
			for(int i=0;i<lista.size();i++){
				cat=(Usuario)lista.get(i);
				usuarios.add(cat);
			}
			em.getTransaction().commit();
			
			user =usuarios.get(0);
			System.out.println("usuario existe");
		} catch (Exception e) {
			user =null;
			System.out.println("usuario no existe");
		}
			return user;
	}
	
	
	@Override
	public List<Rol> obtenerRoles() throws Exception {
		em=emf.createEntityManager();
		ArrayList<Rol> roles= new ArrayList<Rol>();
		Rol rol;
		
		try {
			em.getTransaction().begin();
			Query q= em.createQuery(Querys.OBTENER_ROLES);
			List lista= q.getResultList();
			
			for(int i=0;i<lista.size();i++){
				rol=(Rol)lista.get(i);
				roles.add(rol);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			em.clear();
		}
		
		
		return roles;
	}
	
	public String GrabaPersona(Usuario usuario) {
		em=emf.createEntityManager();
		try {
			
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
			System.out.println(e);
			e.printStackTrace();
		}finally{
			em.clear();
		}
		System.err.println("grabo");
		return "grabo";
	}


	@Override
	public Usuario obtenerUsuarioXUsuarioValidate(Usuario usuario)
			throws Exception {
		ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
		Usuario user;
		try {
			em.getTransaction().begin();
			Query q=em.createQuery(Querys.OBTENER_USUARIO);
			 q.setParameter("usuario",usuario.getUsuario());
			List lista=q.getResultList();
			Usuario cat;
			for(int i=0;i<lista.size();i++){
				cat=(Usuario)lista.get(i);
				usuarios.add(cat);
			}
			em.getTransaction().commit();
			
			user =usuarios.get(0);
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
//			em.getTransaction().rollback();
			user =null;
		}
			return user;
	}


	@Override
	public String ActualizaPersona(Usuario usuario) throws Exception{
		em=emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
			System.out.println(e);
			e.printStackTrace();
		}finally{
			em.clear();
		}
		System.err.println("Actualizo");
		return "Actualizo";
	}
}
	

