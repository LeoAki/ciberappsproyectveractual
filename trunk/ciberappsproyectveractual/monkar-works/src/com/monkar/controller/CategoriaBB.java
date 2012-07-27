package com.monkar.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import com.monkar.bean.Categoria;
import com.monkar.bean.General;
import com.monkar.bean.Sub_categoria;
import com.monkar.service.CategoriaImplementacion;


@ManagedBean
@SessionScoped
public class CategoriaBB implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Categoria categoria;
	private MenuModel menuFavoritos;
	private MenuModel menuCategorias;
	
	 
	 private CategoriaImplementacion categoriaImp;
	 
	 public CategoriaBB(){
		 categoria = new Categoria();
		 categoriaImp= new CategoriaImplementacion();
		 menuFavoritos = new DefaultMenuModel();
		 menuCategorias = new DefaultMenuModel();
		 
		 crearMenuDeCategorias();
		 crearMenuFavoritos();
		 
	 }
	 
	 
	


	private void crearMenuDeCategorias(){
		List<Categoria> listaCategorias = listarCategorias();
	    for(Categoria c:listaCategorias){
	    	Submenu submenu = new Submenu();  
		    submenu.setLabel(c.getNombre());  
		    for(Sub_categoria sc: c.getSub_categorias()){
		    	MenuItem item = new MenuItem();  
		        item.setValue(sc.getNombre());  
		        ExpressionFactory factory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		        MethodExpression methodExpression = 
		        		factory.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), 
		        				"#{aplicacionBB.filtrar_"+sc.getNombre()+"}", String.class, new Class[]{});
		        item.setActionExpression(methodExpression);
		        item.setUpdate(":frmCenterCenter,:growl");
		        submenu.getChildren().add(item);
		    }
	        menuCategorias.addSubmenu(submenu);  
	    }
	 }
	 private void crearMenuFavoritos(){
		List<General> listaFavoritos =listarFavoritos();
	    Submenu submenu2 = new Submenu();  
	    submenu2.setLabel("Favoritos");
	    for(General g:listaFavoritos){
	    	
		    	MenuItem item = new MenuItem();  
		        item.setValue(g.getValor1());  
		        
		        ExpressionFactory factory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		        MethodExpression methodExpression = 
		        		factory.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), 
		        				"#{aplicacionBB."+g.getValor2()+"}", String.class, new Class[]{});
		        item.setActionExpression(methodExpression);
		        item.setUpdate(":frmCenterCenter,:growl");
		        submenu2.getChildren().add(item);
	    }
	    menuFavoritos.addSubmenu(submenu2); 
	 }

	 
	 
	 
	 private List<General> listarFavoritos(){
	       List<General> lista = new ArrayList<General>();
			try {
				General c1 = new General();
				c1.setValor1("Mas Descargados");
				c1.setValor2("listarMasDescargados()");
				c1.setValor3("opMasDescargados");
				
				General c2 = new General();
				c2.setValor1("Mejor Valorados");
				c2.setValor2("listarMejorValorados()");
				c2.setValor3("opMenuMejorValorados");
				
				General c3 = new General();
				c3.setValor1("Del Momento");
				c3.setValor2("listarDelMomento()");
				c3.setValor3("opDelMomento");
				lista.add(c1);
				lista.add(c2);
				lista.add(c3);
				
				
			} catch (Exception e) {
				System.out.println("Exception en CategoriaBB/listarCategoriasTop");
			}
	       return lista;
		 }
	 
	 private List<Categoria> listarCategorias(){
       List<Categoria> list = new ArrayList<Categoria>();
		try {
			list = categoriaImp.listar();
		} catch (Exception e) {
			System.out.println("Exception en CategoriaBB/listarCategorias");
		}
       return list;
	 }




	public Categoria getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}





	public MenuModel getMenuCategorias() {
		return menuCategorias;
	}



	public void setMenuCategorias(MenuModel menuCategorias) {
		this.menuCategorias = menuCategorias;
	}




	public MenuModel getMenuFavoritos() {
		return menuFavoritos;
	}


	public void setMenuFavoritos(MenuModel menuFavoritos) {
		this.menuFavoritos = menuFavoritos;
	}

	 
	 
	 
}
