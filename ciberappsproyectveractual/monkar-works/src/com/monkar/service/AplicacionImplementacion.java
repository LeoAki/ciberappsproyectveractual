package com.monkar.service;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.FileUploadEvent;

import com.monkar.bean.Aplicacion;
import com.monkar.bean.Sub_categoria;
import com.monkar.bean.Usuario;
import com.monkar.factory.FactoryDAO;


public class AplicacionImplementacion implements AplicacionService{


	FactoryDAO factory=FactoryDAO.getFactory(FactoryDAO.MYSQL);
	AplicacionService dao=factory.getAplicacionService();
	
	@Override
	public List<Aplicacion> filtrarXSub_categoria(Sub_categoria sc) throws Exception {
		// TODO Auto-generated method stub
		return dao.filtrarXSub_categoria(sc);
	}

	@Override
	public List<Aplicacion> listar(int cantidad) throws Exception {
		// TODO Auto-generated method stub
		return dao.listar(cantidad);
	}

	@Override
	public List<Aplicacion> listarTopDescargas(int cantidad) throws Exception {
		// TODO Auto-generated method stub
		return dao.listarTopDescargas(cantidad);
	}

	@Override
	public List<Aplicacion> listarTopValoradas(int cantidad) throws Exception {
		// TODO Auto-generated method stub
		return dao.listarTopValoradas(cantidad);
	}

	@Override
	public List<Aplicacion> listarTopNuevas(int cantidad) throws Exception {
		// TODO Auto-generated method stub,
		return dao.listarTopNuevas(cantidad);
	}

	@Override
	public Aplicacion obtenerAplicacion(Aplicacion a) throws Exception {
		// TODO Auto-generated method stub
		return dao.obtenerAplicacion(a);
	}

	@Override
	public List<Aplicacion> listarXUsuario(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		return dao.listarXUsuario(usuario);
	}

	@Override
	public String grabar(Aplicacion aplicacion,List<FileUploadEvent> imagenesAlmacenadas) throws Exception {
		// TODO Auto-generated method stub
		return dao.grabar(aplicacion,imagenesAlmacenadas);
	}

	@Override
	public ArrayList<String> filtrarXTag() throws Exception {
		// TODO Auto-generated method stub
		return dao.filtrarXTag();
	}

	@Override
	public List<Aplicacion> filtrarXTag(String tag) throws Exception {
		// TODO Auto-generated method stub
		return dao.filtrarXTag(tag);
	}

}
