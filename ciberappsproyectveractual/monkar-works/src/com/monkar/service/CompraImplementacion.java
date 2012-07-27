package com.monkar.service;

import com.monkar.bean.Compra;
import com.monkar.dao.CompraDAO;
import com.monkar.factory.FactoryDAO;

public class CompraImplementacion implements CompraService{

//	private CompraDAO dao = new CompraDAO();
	FactoryDAO factory = FactoryDAO.getFactory(FactoryDAO.MYSQL);
	CompraService dao =  factory.getCompraService();
	
	@Override
	public String grabar(Compra compra) throws Exception {
		return dao.grabar(compra);
	}

}
