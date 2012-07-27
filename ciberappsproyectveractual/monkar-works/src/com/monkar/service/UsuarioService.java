package com.monkar.service;

import java.util.List;

import com.monkar.bean.Rol;
import com.monkar.bean.Usuario;

public interface UsuarioService {

	public abstract Usuario obtenerUsuarioXUsuario(Usuario usuario) throws Exception;
	public abstract Usuario obtenerUsuarioXUsuarioValidate(Usuario usuario) throws Exception;
	public abstract List<Rol> obtenerRoles() throws Exception;
	public String GrabaPersona(Usuario usuario) throws Exception;
	public String ActualizaPersona(Usuario usuario) throws Exception;
	
}
