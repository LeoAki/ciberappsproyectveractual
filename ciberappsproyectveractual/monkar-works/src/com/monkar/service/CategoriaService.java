package com.monkar.service;

import java.util.List;

import com.monkar.bean.Categoria;
import com.monkar.bean.Sub_categoria;

public interface CategoriaService {
	public abstract List<Categoria> listar() throws Exception;
	
	public abstract Sub_categoria obtenerXNombre(Sub_categoria sc) throws Exception;
	
	
	public abstract List<Sub_categoria> listarSub() throws Exception;
}
