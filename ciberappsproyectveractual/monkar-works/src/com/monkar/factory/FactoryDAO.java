package com.monkar.factory;

import com.monkar.service.AplicacionService;
import com.monkar.service.CategoriaService;
import com.monkar.service.CompraService;
import com.monkar.service.UsuarioService;


public abstract class FactoryDAO {
	public static final int MYSQL=1;
	public static final int SQLSERVER=3;
	
	public abstract CategoriaService getCategoriaService();
	public abstract AplicacionService getAplicacionService();
	public abstract UsuarioService getUsuarioService();
	public abstract CompraService getCompraService();
	
	public static FactoryDAO getFactory(int bd){
		switch(bd){
		case 1:
			return new MySqlDAOFactory();
		}
		return null;
	}
}
