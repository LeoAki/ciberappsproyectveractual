package com.monkar.constants;

import java.io.Serializable;

public class Querys implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final  String 
	
	LISTAR_CATEGORIAS_TODO="SELECT a FROM Categoria a",
	
	OBTENER_APLICACION="SELECT a FROM Aplicacion a WHERE a.idAplicacion = :idAplicacion",
	
	FILTRAR_APLICACIONES_X_SUB_CATEGORIA="SELECT a FROM Aplicacion a WHERE a.sub_categoria = :sub_categoria",
	
	LISTAR_APLICACIONES="SELECT a FROM Aplicacion a ORDER BY a.idAplicacion",
	
	OBTENER_ULTIMA_APLICACION_REGISTRADA="SELECT a FROM Aplicacion a ORDER BY a.idAplicacion DESC",
	
	LISTAR_APLICACIONES_TOP_DESCARGAS="SELECT a FROM Aplicacion a WHERE a.num_descargas > 4 ORDER BY a.num_descargas DESC ",
	
	LISTAR_APLICACIONES_TOP_NUEVAS="SELECT a FROM Aplicacion a 4 ORDER BY a.fec_registro",
			
	LISTAR_APLICACIONES_TOP_VALORADAS="SELECT a FROM Aplicacion a WHERE a.puntuacion > 3 ORDER BY a.puntuacion DESC",
	
	OBTENER_USUARIO_X_USUARIO="SELECT a FROM Usuario a WHERE a.usuario = :usuario AND a.contrasenia = :contrasenia",
	
	OBTENER_USUARIO="SELECT a FROM Usuario a WHERE a.usuario = :usuario",
	
	OBTENER_COMPRA_X_USUARIO="SELECT c FROM Compra c WHERE c.usuario.idUsuario = :usuario GROUP BY  c.aplicacion",
	
	OBTENER_ROLES="SELECT r FROM Rol r",
	
	LISTAR_COMPRAS="SELECT c FROM Compra c ",
	
	LISTA_X_TAG="SELECT a FROM Aplicacion a WHERE a.tags like :tag",
			
	LISTA_X_TAG2="SELECT a FROM Aplicacion a ";
	

}
