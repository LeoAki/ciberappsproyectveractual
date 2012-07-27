package com.monkar.hilos;

import com.monkar.bean.Usuario;
import com.monkar.controller.UsuarioBB;
import com.monkar.service.UsuarioImplementacion;

public class HiloUsuario implements Runnable {
	
	Usuario user;
	UsuarioImplementacion usuimpl = new UsuarioImplementacion();
	
	public HiloUsuario(Usuario user){
			this.user=user;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("entro al hilo");
			Thread.sleep(72000000);
			
			user.setActivo(1);
			usuimpl.ActualizaPersona(user);
			UsuarioBB.contador=0;
			
			System.out.println("paso el hilo Estado usuario:"+ user.getActivo());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	

}
