package com.renanoliveira.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.renanoliveira.entity.Atividade;
import com.renanoliveira.entity.Cliente;
import com.renanoliveira.entity.Projeto;
import com.renanoliveira.entity.Usuario;

public class FachadaTest {
	
	private Usuario usuario;
	private Fachada fachada;
	private Projeto projeto;
	private Atividade atividade;
	private Cliente cliente;
	
	
	public FachadaTest(){
		
		usuario = new Usuario();
		projeto = new Projeto();
		atividade = new Atividade();
		cliente = new Cliente();
		fachada = new Fachada();
		
		montarDadosUsuario();
		montarDadosAtividade();
		montarDadosCliente();
		montarDadosProjeto();

		
	}
	
	public void montarDadosAtividade(){
		
		atividade.setNome("[Troca] Boleto de troca");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("O sistema está permitindo gerar boleto de troca do mesmo orçamento. " +
				"Exe:Foi gerado boleto de troca no nome da cliente JOANA DARC " +
				"na loja de Caruaru com o número 45 do orçamento 9246. Logo em seguida o " +
				"pessoal conseguiu gerar um outro boleto 46 com o nome de JOANA DARC CINTRA FARIAS" +
				" ZEFERINO do mesmo orçamento. (A troca foi referente a quantidade total do orçamento).");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("URGENTE");
		
		
	}
	public void montarDadosProjeto(){
		projeto.setNome("APS PROJETO");
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente);
		projeto.setClientes(clientes);
	}
	public void montarDadosCliente(){
		cliente.setNome("PROF Rodrigo Vilar");		
	}
	
	public void montarDadosUsuario(){
		usuario.setCargo("ASPIRA");
		usuario.setEmail("renan@gmail.com");
		usuario.setLogin("renan");
		usuario.setNome("Renan Oliveira");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);
		
	}

	@Test
	public void adcionarUsuarioTest() {
		
		
		fachada.criarUsuario(usuario);
				
		Assert.assertEquals("renan", fachada.buscarUsuarioPorId(usuario.getId()).getLogin());

	}
	
	@Test
	public void adicionarProjetoTest(){
		
		
	}
	
	@Test
	public void adicionarClienteTest(){
		
		
	}
	
	@Test
	public void adicionarAtividadeTest(){
		
		
	}
	
	
	@Test
	public void listarTodosUsuariosTest(){
		
		Assert.assertNotNull(fachada.buscarTodosUsuarios());
		
	}
	
	@Test
	public void listarTodosClientesTest(){
		
		
	}
	
	@Test
	public void listarTodasAtividadesTest(){
		
		
	}
	
	@Test
	public void listarTodosProjetosTest(){
		
		
	}
	
	@Test
	public void alerarClienteTest(){
		
		
	}
	
	@Test
	public void alerarAtividadeTest(){
		
		
	}
	
	@Test
	public void alerarProjetoTest(){
		
		
	}
	
	@Test
	public void alerarUsuarioTest(){
		
		
	}
	
	@Test
	public void removerUsuarioTest(){
		
		fachada.removerUsuario(usuario);
		
		Assert.assertNull(fachada.buscarUsuarioPorId(usuario.getId()).getLogin());
		
	}
	

	
	
	
	
	

}
