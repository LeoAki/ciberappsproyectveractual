package com.monkar.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.monkar.bean.Compra;
import com.monkar.service.CompraImplementacion;


@ManagedBean
@SessionScoped
public class CompraBB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Compra compra;
	
	private CompraImplementacion compraImp;
	
	public CompraBB() {
	}

	
	
//	getters and setters

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
}
