package com.monkar.service;

import java.util.List;

import com.monkar.bean.Rol;
import com.monkar.bean.Usuario;
import com.monkar.factory.FactoryDAO;

public class UsuarioImplementacion implements UsuarioService{

	
	FactoryDAO factory=FactoryDAO.getFactory(FactoryDAO.MYSQL);
	UsuarioService dao=factory.getUsuarioService();
	
	@Override
	public Usuario obtenerUsuarioXUsuario(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.obtenerUsuarioXUsuario(usuario);
	}
	
	@Override
	public List<Rol> obtenerRoles() throws Exception {
		// TODO Auto-generated method stub
		return dao.obtenerRoles();
	}

	@Override
	public String GrabaPersona(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.GrabaPersona(usuario);
	}

	@Override
	public Usuario obtenerUsuarioXUsuarioValidate(Usuario usuario)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.obtenerUsuarioXUsuarioValidate(usuario);
	}

	@Override
	public String ActualizaPersona(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.ActualizaPersona(usuario);
	}


}
