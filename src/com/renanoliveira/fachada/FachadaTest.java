package com.renanoliveira.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.renanoliveira.entity.Atividade;
import com.renanoliveira.entity.Cliente;
import com.renanoliveira.entity.Projeto;
import com.renanoliveira.entity.Usuario;

public class FachadaTest{

	private Usuario usuario;
	private Fachada fachada;
	private Projeto projeto;
	private Atividade atividade;
	private Cliente cliente;	

	
	@Before
	public void setUp() throws Exception {
		fachada = new Fachada();
	}

	

	@Test
	public void criarProjeto() {
		
		//setando os dados do projeto
		projeto = new Projeto();
		projeto.setNome("APS PROJETO,33");
		
		//criando o projeto
		fachada.criarProjeto(projeto);
		
		//buscando o projeto criado
		Projeto projetoFind = fachada.buscaProjetoPorNome("APS PROJETO,33");
		
		//verificando se o projeto foi criado
		Assert.assertEquals(projetoFind.getNome(), projeto.getNome());
		
		//Limpando o projeto criado do banco
		fachada.removerProjeto(projetoFind);
		

	}

	@Test
	public void criarUsuario() {
		
		//inserindo os dados do usuário
		usuario = new Usuario();
		usuario.setCargo("ASPIRA");
		usuario.setEmail("renan@gmail.com");
		usuario.setLogin("renan");
		usuario.setNome("Renan Oliveira");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);
		
		//criando o usuário no banco
		fachada.criarUsuario(usuario);
		
		//buscando o usuário criado no banco
		Usuario usuarioFind = fachada.buscarUsuarioPorNome("Renan Oliveira");
		//verificando se o usuario foi criado		
		Assert.assertEquals(usuarioFind, usuario.getNome());
		//removendo o usuario criado
		fachada.removerUsuario(usuarioFind);		

	}

	@Test
	public void criarCliente() {
		
		//lista de projetos
		List<Projeto> projetos = new ArrayList<Projeto>();
		
		//inserindo os dados do cliento
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar");
		
		//um cliente tem uma lista de projetos
		projeto = new Projeto();
		projeto.setNome("Projeto 1");
		projetos.add(projeto);
		projeto = new Projeto();
		projeto.setNome("Projeto 2");
		projetos.add(projeto);
		projeto = new Projeto();
		projeto.setNome("Projeto 3");
		projetos.add(projeto);
		
		//inserindo os projetos no cliente
		cliente.setProjetos(projetos);
		//criando um cliente
		fachada.criarCliente(cliente);
		//buscando o cliente criado
		Cliente clienteFind = fachada.buscarClientePorNome("PROF Rodrigo Vilar");
		//verificando se o cliente foi criado
		Assert.assertEquals(clienteFind.getNome(), cliente.getNome());
		//verficando se os 3 projetos foram adicionados
		Assert.assertEquals(clienteFind.getProjetos().size(), 3);
		//removendo o cliente criado
		fachada.removerCliente(clienteFind);
	}

	@Test
	public void criarAtividade() {
		//inserindo os dados da atividade
		atividade = new Atividade();
		atividade.setNome("[MELHORIA] Boleto Bancário Test");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade
				.setDescricao("asdfffasdfggasdfggas3edmd.");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("IMPORTANTE");

		// inserindo os dados do usuário
		usuario = new Usuario();
		usuario.setCargo("ASPIRA");
		usuario.setEmail("renan@gmail.com");
		usuario.setLogin("renan");
		usuario.setNome("Renan Oliveira");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);
		//inserindo o usuario
		atividade.setUsuario(usuario);
		//criando a atividade
		fachada.criarAtividade(atividade);
		//buscando a atividade no banco
		Atividade atividadeFind = fachada.buscarAtividadePorNome("[MELHORIA] Boleto Bancário Test");
		//verificando se a atividade foi criada
		Assert.assertEquals(atividadeFind.getNome(), atividade.getNome());
		//removendo atividade
		fachada.removerAtividade(atividadeFind);
	}

	@Test
	public void criarProjetoSemCliente() {

		projeto = new Projeto();
		projeto.setNome("APS PROJETO");
		projeto.setClientes(null);
		fachada.criarProjeto(projeto);
		
		Assert.assertNotNull(fachada.buscarClientePorNome("APS PROJETO"));

	}

	@Test
	public void criarProjetoSemAtividade() {
		
		String stringTest = "APS PROJETO Sem Atividade";
		
		projeto = new Projeto();
		projeto.setNome(stringTest);
		projeto.setAtividades(null);		
		fachada.criarProjeto(projeto);
		
		Assert.assertNotNull(fachada.buscarClientePorNome(stringTest));

	}

	@Test(expected=Exception.class)
	public void criarAtividadeSemProjeto() {

		atividade.setNome("[Troca] Boleto de troca");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade
				.setDescricao("O sistema est� permitindo gerar boleto de troca do mesmo or�amento. "
						+ "Exe:Foi gerado boleto de troca no nome da cliente JOANA DARC "
						+ "na loja de Caruaru com o n�mero 45 do or�amento 9246. Logo em seguida o "
						+ "pessoal conseguiu gerar um outro boleto 46 com o nome de JOANA DARC CINTRA FARIAS"
						+ " ZEFERINO do mesmo or�amento. (A troca foi referente a quantidade total do or�amento).");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("URGENTE");
		atividade.setUsuario(usuario);
		atividade.setProjeto(null);
		fachada.criarAtividade(atividade);
		
	}

	@Test(expected=Exception.class)
	public void criarAtividadeSemUsuario() {
		atividade = new Atividade();
		
		atividade.setNome("[Troca] Boleto de troca 2");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade
				.setDescricao("O sistema safasdfasdfasdfasdfasdfsadfas fsdfasf");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("FECHADA");
		atividade.setPrioridade("IMPORTANTE");
		atividade.setUsuario(null);
		
	}

	
	@Test
	public void criarUsuarioSemAtividade() {
		
		usuario = new Usuario();
		usuario.setCargo("ASPIRA");
		usuario.setEmail("renan@gmail.com");
		usuario.setLogin("renan");
		usuario.setNome("Renan 1");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		usuario.setAtividades(null);
		fachada.criarUsuario(usuario);
		
		Assert.assertNotNull(fachada.buscarUsuarioPorNome("Renan 1"));
		
	}

	@Test
	public void criarClienteSemProjeto() {
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste");
		cliente.setProjetos(null);
		fachada.criarCliente(cliente);
		Assert.assertNull(fachada.buscarClientePorNome("PROF Rodrigo Vilar Teste"));
	}



	@Test
	public void listarTodosUsuariosTest() {

		Assert.assertNotNull(fachada.buscarTodosUsuarios());

	}

	@Test
	public void listarTodosClientesTest() {

		Assert.assertNotNull(fachada.buscarTodosClientes());

	}

	@Test
	public void listarTodasAtividadesTest() {

		Assert.assertNotNull(fachada.buscarTodasAtividades());

	}

	@Test
	public void listarTodosProjetosTest() {

		Assert.assertNotNull(fachada.buscarTodosProjetos());

	}

	@Test
	public void alterarClienteTest() {
		
		Cliente clienteNovo = new Cliente();
		clienteNovo.setNome("JoaoZinho");
		Cliente clienteParaAlterar = fachada.buscarClientePorId(1);
		clienteParaAlterar.setNome(clienteNovo.getNome());
		fachada.alterarCliente(clienteParaAlterar);
		
		Assert.assertEquals(fachada.buscarClientePorId(1).getNome(), clienteNovo.getNome());
		
		
	}

	@Test
	public void alterarAtividadeTest() {
		Atividade atividadeNova = new Atividade();
		atividadeNova.setNome("Atividade outra coisa");
		atividadeNova.setPrioridade("IMPORTANTE");
		Atividade atividadeParaAlterar = fachada.buscarAtividadePorId(1);
		atividadeParaAlterar.setNome(atividadeNova.getNome());
		fachada.alterarAtividade(atividadeParaAlterar);
		
		Assert.assertEquals(fachada.buscarClientePorId(1).getNome(), atividadeNova.getNome());
		
	}

	@Test
	public void alterarProjetoTest() {
		Cliente clienteNovo = new Cliente();
		clienteNovo.setNome("APS Distribuidora");
		Cliente clienteParaAlterar = fachada.buscarClientePorId(1);
		clienteParaAlterar.setNome(clienteNovo.getNome());
		fachada.alterarCliente(clienteParaAlterar);
		
		Assert.assertEquals(fachada.buscarClientePorId(1).getNome(), clienteNovo.getNome());
		
	}

	@Test
	public void alterarUsuarioTest() {
		Usuario usuarioNovo = new Usuario();
		usuarioNovo.setNome("JoaoZinho");
		Usuario usuarioParaAlterar = fachada.buscarUsuarioPorId(1);
		usuarioParaAlterar.setNome(usuarioNovo.getNome());
		fachada.alterarUsuario(usuarioParaAlterar);
		
		Assert.assertEquals(fachada.buscarClientePorId(1).getNome(), usuarioNovo.getNome());
		
	}

	@Test
	public void removerUsuarioTest() {
		Usuario usuario = fachada.buscarUsuarioPorId(2);		
		fachada.removerUsuario(usuario);
		Usuario usuarioRemovido = fachada.buscarUsuarioPorId(2);		
		Assert.assertNull(usuarioRemovido);
		


	}

	@Test
	public void removerProjetoTest() {
		Projeto projeto = fachada.buscarProjetoPorID(2);		
		fachada.removerProjeto(projeto);
		Projeto projetoRemovido = fachada.buscarProjetoPorID(2);		
		Assert.assertNull(projetoRemovido);
	}

	@Test
	public void removerClienteTest() {
		Cliente cliente = fachada.buscarClientePorId(2);		
		fachada.removerCliente(cliente);
		Cliente clienteRemovido = fachada.buscarClientePorId(2);		
		Assert.assertNull(clienteRemovido);
	}

	@Test
	public void removerAtividadeTest() {
		Atividade atividade = fachada.buscarAtividadePorId(2);		
		fachada.removerAtividade(atividade);
		Atividade atividadeRemovida = fachada.buscarAtividadePorId(2);		
		Assert.assertNull(atividadeRemovida);
	}
	

	@Test
	public void localizarUsuarioPorNome() {
		
		Usuario usuario = fachada.buscarUsuarioPorNome("Renan 1");
		Assert.assertNotNull(usuario);

	}

	@Test
	public void localizarProjetoPorNome() {
		Projeto projeto = fachada.buscaProjetoPorNome("APS Projeto");
		Assert.assertNotNull(projeto);
		
	}

	@Test
	public void localizarClientePorNome() {
		Cliente cliente = fachada.buscarClientePorNome("PROF Rodrigo Vilar");
		Assert.assertNotNull(cliente);
	}

	@Test
	public void localizarAtividadePorNome() {
		Atividade atividade = fachada.buscarAtividadePorNome("Agua e Sal");
		Assert.assertNotNull(atividade);
	}
	
	@Test
	public void localizarUsuarioPorID() {
		Usuario usuario = fachada.buscarUsuarioPorId(2);
		Assert.assertNotNull(usuario);
	}

	@Test
	public void localizarProjetoPorID() {
	  Projeto projeto = fachada.buscarProjetoPorID(1);
	  Assert.assertNotNull(projeto);
	}

	@Test
	public void localizarClientePorID() {
		
		Cliente cliente = fachada.buscarClientePorId(3);
		Assert.assertNotNull(cliente);
	}

	@Test
	public void localizarAtividadePorID() {
		Atividade atividade = fachada.buscarAtividadePorId(4);
		Assert.assertNotNull(atividade);
	}

}
