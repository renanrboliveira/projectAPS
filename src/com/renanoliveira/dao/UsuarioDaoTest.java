package com.renanoliveira.dao;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.renanoliveira.entity.Usuario;

public class UsuarioDaoTest {

	@Test
	public void test() {
		Usuario usuario = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDao();
		
		usuario.setNome("Fulera Teste 33");
		
		usuarioDao.save(usuario);
		
		//assertEquals("Fulera Teste", usuarioDao.find(225).getNome());
	}
	
	@Test
	public void testFindId(){
		UsuarioDao usuarioDao = new UsuarioDao();

		//assertNotNull(usuarioDao.find(225));
		
		//assertEquals(new Double(255), usuarioDao.find(225).getId());
	}

}
