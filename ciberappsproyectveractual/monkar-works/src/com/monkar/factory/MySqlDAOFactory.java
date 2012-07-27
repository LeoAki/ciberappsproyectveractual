package com.monkar.factory;
import com.monkar.dao.AplicacionDAO;
import com.monkar.dao.CategoriaDAO;
import com.monkar.dao.CompraDAO;
import com.monkar.dao.UsuarioDAO;
import com.monkar.service.AplicacionService;
import com.monkar.service.CategoriaService;
import com.monkar.service.CompraService;
import com.monkar.service.UsuarioService;



public class MySqlDAOFactory extends FactoryDAO{

	@Override
	public CategoriaService getCategoriaService() {
		return new CategoriaDAO();
	}

	@Override
	public AplicacionService getAplicacionService() {
		return new AplicacionDAO();
	}
	
	@Override
	public UsuarioService getUsuarioService() {
		return new UsuarioDAO();
	}
	
	@Override
	public CompraService getCompraService() {
		return new CompraDAO();
	}

}
