package com.renanoliveira.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.renanoliveira.dao.RepositoryException;
import com.renanoliveira.entity.Atividade;
import com.renanoliveira.entity.Cliente;
import com.renanoliveira.entity.Projeto;
import com.renanoliveira.entity.Usuario;
import com.renanoliveira.logic.LogicException;

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
	public void criarProjeto() throws RepositoryException {
		
		//setando os dados do projeto
		projeto = new Projeto();
		projeto.setNome("APS PROJETO,33");		
		//criando o projeto
		fachada.criarProjeto(projeto);		
		//buscando o projeto criado
		Projeto projetoFind = fachada.buscaProjetoPorNome("APS PROJETO,33");		
		//verificando se o projeto foi criado
		Assert.assertEquals(projetoFind.getNome(), projeto.getNome());		
	

	}

	@Test
	public void criarUsuario() throws RepositoryException {
		
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
		Assert.assertEquals(usuarioFind.getNome(), usuario.getNome());
	

	}

	@Test
	public void criarCliente() throws RepositoryException {
		
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

	}

	@Test
	public void criarAtividade() throws RepositoryException {
		//inserindo os dados da atividade
		atividade = new Atividade();
		atividade.setNome("[MELHORIA] Boleto Bancário Test");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("asdfffasdfggasdfggas3edmd.");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("IMPORTANTE");

		// inserindo os dados do  usuário
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
	}

	@Test(expected=LogicException.class)
	public void criarProjetoSemCliente() throws RepositoryException {

		//inserindo dados do projeto
		projeto = new Projeto();
		
		projeto.setNome("APS PROJETO");
		
		projeto.setClientes(null);
		
		//criando o projeto
		fachada.criarProjeto(projeto);
		
		//Verifica se não foi criado 
		Assert.assertNull(fachada.buscarClientePorNome("APS PROJETO"));

	}

	@Test
	public void criarProjetoSemAtividade() throws RepositoryException {
		
		String stringTest = "APS PROJETO Sem Atividade";
		
		projeto = new Projeto();
		projeto.setNome(stringTest);
		projeto.setAtividades(null);		
		fachada.criarProjeto(projeto);
		
		Assert.assertNotNull(fachada.buscarClientePorNome(stringTest));

	}

	@Test(expected=Exception.class)
	public void criarAtividadeSemProjeto() throws RepositoryException {

		atividade.setNome("[Troca] Boleto de troca");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("O sistema esta permitindo gerar boleto de troca do mesmo orcamento. "
						+ "Exe:Foi gerado boleto de troca no nome da cliente JOANA DARC "
						+ "na loja de Caruaru com o numero 45 do orcamento 9246. Logo em seguida o "
						+ "pessoal conseguiu gerar um outro boleto 46 com o nome de JOANA DARC CINTRA FARIAS"
						+ " ZEFERINO do mesmo orcamento. (A troca foi referente a quantidade total do orcamento).");
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
	public void criarUsuarioSemAtividade() throws RepositoryException {
		
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
	public void criarClienteSemProjeto() throws RepositoryException {
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste");
		cliente.setProjetos(null);
		fachada.criarCliente(cliente);
		Assert.assertNull(fachada.buscarClientePorNome("PROF Rodrigo Vilar Teste"));
	}



	@Test
	public void listarTodosUsuariosTest() throws RepositoryException {
		//criando usuarios
		usuario = new Usuario();
		usuario.setCargo("ASPIRA 1");
		usuario.setEmail("renan3@gmail.com");
		usuario.setLogin("renan1");
		usuario.setNome("Renan 1");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		usuario.setAtividades(null);
		//criando usuario no bd
		fachada.criarUsuario(usuario);
		usuario = new Usuario();
		usuario.setCargo("ASPIRA 2");
		usuario.setEmail("renan2@gmail.com");
		usuario.setLogin("renan2");
		usuario.setNome("Renan 3");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		usuario.setAtividades(null);
		//criando usuario no bd
		fachada.criarUsuario(usuario);
		usuario = new Usuario();
		usuario.setCargo("ASPIRA 3");
		usuario.setEmail("renan1@gmail.com");
		usuario.setLogin("renan3");
		usuario.setNome("Renan 2");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		usuario.setAtividades(null);
		//criando usuario no bd
		fachada.criarUsuario(usuario);
		//verificando se os usuários foram criados
		Assert.assertNotNull(fachada.buscarTodosUsuarios());		
		
		
	}

	@Test
	public void listarTodosClientesTest() throws RepositoryException {
		//criando clientes e inserindo no bd
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste 1");
		cliente.setProjetos(null);
		fachada.criarCliente(cliente);
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste 2");
		cliente.setProjetos(null);
		fachada.criarCliente(cliente);
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste 3");
		cliente.setProjetos(null);
		fachada.criarCliente(cliente);
		//verificando se os clientes foram criado
		Assert.assertNotNull(fachada.buscarTodosClientes());
	}

	@Test
	public void listarTodasAtividadesTest() throws RepositoryException {
		
		//CRIANDO A 1 ATIVIDADE
		// inserindo os dados da atividade
		atividade = new Atividade();
		atividade.setNome("[MELHORIA] Boleto Bancário Test 1");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("haahshhdhshasdfas");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("FECHADA");
		atividade.setPrioridade("IMPORTANTE");

		// inserindo os dados do usuário
		usuario = new Usuario();
		usuario.setCargo("ASPIRA 1");
		usuario.setEmail("renan1@gmail.com");
		usuario.setLogin("renan1");
		usuario.setNome("Renan Oliveira 1");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("1232112333");
		usuario.setStatus(true);
		// inserindo o usuario
		atividade.setUsuario(usuario);
		// criando a 1 atividade
		fachada.criarAtividade(atividade);
		//CRIANDO A 2 ATIVIDADE
		// inserindo os dados da atividade
		atividade = new Atividade();
		atividade.setNome("[MELHORIA] Boleto Bancário Test 2");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("asdfffasdfggasdfggas3edmdSDFASDFFASS.");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("IMPORTANTE");

		// inserindo os dados do usuário
		usuario = new Usuario();
		usuario.setCargo("ASPIRA 2");
		usuario.setEmail("renan2@gmail.com");
		usuario.setLogin("renan2");
		usuario.setNome("Renan Oliveira 2");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("12321323ASS");
		usuario.setStatus(true);
		// inserindo o usuario
		atividade.setUsuario(usuario);
		// criando a atividade
		fachada.criarAtividade(atividade);
		//CRIANDO A 3 ATIVIDADE
		// inserindo os dados da atividade
		atividade = new Atividade();
		atividade.setNome("[MELHORIA] Boleto Bancário Test 3");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("asdfffasdfggasdfggas3edmdASSFSWWSS.");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("IMPORTANTE");

		// inserindo os dados do usuário
		usuario = new Usuario();
		usuario.setCargo("ASPIRA 3");
		usuario.setEmail("renan3@gmail.com");
		usuario.setLogin("renan3");
		usuario.setNome("Renan Oliveira 3");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("12321ASDFFASD3");
		usuario.setStatus(true);
		// inserindo o usuario
		atividade.setUsuario(usuario);
		// criando a atividade
		fachada.criarAtividade(atividade);

		Assert.assertNotNull(fachada.buscarTodasAtividades());

	}

	@Test
	public void listarTodosProjetosTest() throws RepositoryException {
		projeto = new Projeto();
		projeto.setNome("APS PROJETO 1");		
		//criando o projeto
		fachada.criarProjeto(projeto);		
		projeto = new Projeto();
		projeto.setNome("APS PROJETO 2");		
		//criando o projeto
		fachada.criarProjeto(projeto);		
		projeto = new Projeto();
		projeto.setNome("APS PROJETO 3");		
		//criando o projeto
		fachada.criarProjeto(projeto);
		
		Assert.assertNotNull(fachada.buscarTodosProjetos());
	}

	@Test
	public void alterarClienteTest() throws RepositoryException {
		//criando um novo cliente
		Cliente clienteNovo = new Cliente();
		clienteNovo.setNome("Joaozinho");
		fachada.criarCliente(clienteNovo);
		//buscando o cliente criado
		Cliente clienteParaAlterar = fachada.buscarClientePorNome("Joaozinho");
		clienteParaAlterar.setNome("Joao");		
		//alterando o cliente
		fachada.alterarCliente(clienteParaAlterar);
		//buscar  o cliente alterado
		Cliente clienteAlterado = fachada.buscarClientePorNome("Joao");
		//verificando se o cliente foi alterado 
		Assert.assertEquals(clienteAlterado.getNome(),"Joao");
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
