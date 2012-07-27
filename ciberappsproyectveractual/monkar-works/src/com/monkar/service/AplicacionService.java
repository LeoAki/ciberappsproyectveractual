package com.monkar.service;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.FileUploadEvent;

import com.monkar.bean.Aplicacion;
import com.monkar.bean.Sub_categoria;
import com.monkar.bean.Usuario;

public interface AplicacionService {
	
	
	public abstract List<Aplicacion> filtrarXSub_categoria(Sub_categoria sc) throws Exception;

	public abstract List<Aplicacion> listar(int cantidad) throws Exception;

	public abstract List<Aplicacion> listarTopDescargas(int cantidad) throws Exception;

	public abstract List<Aplicacion> listarTopValoradas(int cantidad) throws Exception;

	public abstract List<Aplicacion> listarTopNuevas(int cantidad) throws Exception;
	
	public abstract Aplicacion obtenerAplicacion(Aplicacion a) throws Exception;

	public abstract List<Aplicacion> listarXUsuario(Usuario usuario) throws Exception;
	
	public abstract String grabar(Aplicacion aplicacion,List<FileUploadEvent> imagenesAlmacenadas) throws Exception;
	
	public ArrayList<String> filtrarXTag() throws Exception;
	
	public List<Aplicacion> filtrarXTag(String tag) throws Exception;
}
