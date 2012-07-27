package com.monkar.service;

import java.util.List;

import com.monkar.bean.Categoria;
import com.monkar.bean.Sub_categoria;
import com.monkar.dao.CategoriaDAO;
import com.monkar.factory.FactoryDAO;

public class CategoriaImplementacion implements CategoriaService{
	
	FactoryDAO factory = FactoryDAO.getFactory(FactoryDAO.MYSQL);
	CategoriaService dao =  factory.getCategoriaService();
	@Override
	public List<Categoria> listar() throws Exception {
		// TODO Auto-generated method stub
		return dao.listar();
	}
	@Override
	public List<Sub_categoria> listarSub() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Sub_categoria obtenerXNombre(Sub_categoria sc) throws Exception {
		return dao.obtenerXNombre(sc);
	}

}
