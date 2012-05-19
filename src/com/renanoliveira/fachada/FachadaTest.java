package com.renanoliveira.fachada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.renanoliveira.dao.RepositoryException;
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

	/** CORRIGIDO */
	@Before
	public void setUp() throws Exception {
		fachada = new Fachada();
	}

	
	/**CORRIGIDO*/
	@Test
	public void criarProjeto() throws RepositoryException {		
		//setando os dados do projeto
		projeto = new Projeto();
		projeto.setNome("APS PROJETO");		
		//criando o projeto
		fachada.criarProjeto(projeto);		
		//buscando o projeto criado dentro do banco de dados
		Projeto projetoFind = fachada.buscaProjetoPorNome("APS PROJETO");
		// verifica se o projeto foi criado, com a comparação de nome,
        // pois nao temos de busca, comparando pelo ID, pois o banco gera seu proprio id
		Assert.assertEquals(projetoFind.getNome(), projeto.getNome()); 

	}

	/**CORRIGIDO*/
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
		//criando o usuário no banco de dados
		fachada.criarUsuario(usuario);		
		//buscando o usuário criado no banco
		Usuario usuarioFind = fachada.buscarUsuarioPorNome("Renan Oliveira");
		// o mesmo metodo que foi feito acima, foi feito aqui.. 
		// verificamos se o usuario foi criado com a comparação do nome
		// USUARIO RETORNADO DO BANCO DE DADOS E COMPARADO COM O USUARIO CRIADO ACIMA
		Assert.assertEquals(usuarioFind.getNome(), usuario.getNome());
		

	}

	/**CORRIGIDO*/
	@Test
	public void criarCliente() throws RepositoryException {
		
				
		//inserindo os dados do cliento
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar");
		fachada.criarCliente(cliente);
		
		//um cliente tem uma lista de projetos
		projeto = new Projeto();
		projeto.setNome("Projeto 1");
		
		projeto = new Projeto();
		projeto.setNome("Projeto 2");
		
		projeto = new Projeto();
		projeto.setNome("Projeto 3");
		
		
		
	
		//criando um cliente
	
		//buscando o cliente criado
		Cliente clienteFind = fachada.buscarClientePorNome("PROF Rodrigo Vilar");
		//verificando se o cliente foi criado
		Assert.assertEquals(clienteFind.getNome(), cliente.getNome());

	}
	
	/**CORRIGIDO*/
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
		fachada.criarUsuario(usuario);//criando um usuario na base de dados		
		atividade.setUsuario(fachada.buscarUsuarioPorNome(usuario.getNome()));
		//criando a atividade
		

		projeto = new Projeto();		
		projeto.setNome("PROJETO BANCARIO");		
		fachada.criarProjeto(projeto);//criando um projeto na base de dados
		atividade.setProjeto(fachada.buscaProjetoPorNome(projeto.getNome()));
		
		fachada.criarAtividade(atividade);
		//buscando a atividade no banco
		Atividade atividadeFind = fachada.buscarAtividadePorNome("[MELHORIA] Boleto Bancário Test");
		//verificando se a atividade foi criada
		Assert.assertEquals(atividadeFind.getNome(), atividade.getNome());
	}

	/**CORRIGIDO*/
	@Test
	public void criarProjetoSemCliente() throws RepositoryException {

		//inserindo dados do projeto
		projeto = new Projeto();		
		projeto.setNome("PROJETO BANCARIO");		
		projeto.setClientes(null); // projeto sem cliente		
		//criando o projeto
		fachada.criarProjeto(projeto);		
		Projeto projetoFind = fachada.buscaProjetoPorNome("PROJETO BANCARIO");
		Assert.assertEquals(projetoFind.getNome(), projeto.getNome());
		
	}

	/**CORRIGIDO*/
	@Test
	public void criarProjetoSemAtividade() throws RepositoryException {
		
		String stringTest = "APS PROJETO Sem Atividade";		
		projeto = new Projeto();
		projeto.setNome(stringTest);
		projeto.setAtividades(null);//projeto sem atividade	
		fachada.criarProjeto(projeto);		
		Assert.assertNull(fachada.buscaProjetoPorNome(stringTest).getAtividades());//verificando se é null as atividades

	}
	
	/**CORRIGIDO*/
	@Test
	public void criarAtividadeSemProjeto() throws RepositoryException {
		
		atividade = new Atividade();
		atividade.setNome("[Troca] Boleto de troca");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("O sistema esta permitindo gerar boleto de troca do mesmo "+
						 "orcamento. A troca foi referente a quantidade total do orcamento");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("URGENTE");
		atividade.setUsuario(usuario);
		atividade.setProjeto(null);//atividade sem projeto
		fachada.criarAtividade(atividade);
		
		Assert.assertNull(fachada.buscarAtividadePorNome(atividade.getNome()).getProjeto());
		
	}

	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test
	public void criarAtividadeSemUsuario() throws RepositoryException {
		atividade = new Atividade();		
		atividade.setNome("[Troca] Boleto de troca 2");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("O sistema safasdfasdfasdfasdfasdfsadfas fsdfasf");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("FECHADA");
		atividade.setPrioridade("IMPORTANTE");
		atividade.setUsuario(null);//atividade sem usuario
		fachada.criarAtividade(atividade);
		Assert.assertNull(fachada.buscarAtividadePorNome(atividade.getNome()).getUsuario());
		
		
	}

	/**
	 * CORRIGIDO
	 * */
	@Test
	public void criarUsuarioSemAtividade() throws RepositoryException {
		
		//nesse caso podemos criar um usuario sem a atividade
		usuario = new Usuario();
		usuario.setCargo("ASPIRA");
		usuario.setEmail("renan@gmail.com");
		usuario.setLogin("renan");
		usuario.setNome("Renan 1");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		usuario.setAtividades(null);//ATIVIDADE NULA
		fachada.criarUsuario(usuario);
		//VERIFICANDO SE A ATIVIDADE E NULA
		Assert.assertNull(fachada.buscarUsuarioPorNome("Renan 1").getAtividades());
		
	}
	
	/**
	 * CORRIGIGO
	 * */
	@Test
	public void criarClienteSemProjeto() throws RepositoryException {
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste");
		cliente.setProjetos(null);//CLIENTE SEM PROJETO
		fachada.criarCliente(cliente);
		Assert.assertNull(fachada.buscarClientePorNome("PROF Rodrigo Vilar Teste").getProjetos());
	}


	/**CORRIGIDO*/
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
		Assert.assertEquals(fachada.buscarTodosUsuarios().size(), 3);		
		
		
	}
	/**CORRIGIDO*/
	@Test
	public void listarTodosClientesTest() throws RepositoryException {
		//criando clientes e inserindo no bd
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste 1");
		fachada.criarCliente(cliente);
		
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste 2");
		fachada.criarCliente(cliente);
		
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste 3");
		fachada.criarCliente(cliente);
		
		//verificando se os clientes foram criado
		Assert.assertEquals(fachada.buscarTodosClientes().size(), 3);//foi criado três clientes, verifico se realmente existe 3
	}
	/**CORRIGIDO*/
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

		// criando a atividade
		fachada.criarAtividade(atividade);

		Assert.assertEquals(fachada.buscarTodasAtividades().size(), 3);

	}
	/**CORRIGIDO*/
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
		
		Assert.assertEquals(fachada.buscarTodosProjetos().size(), 3);
		
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
	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test
	public void alterarAtividadeTest() throws RepositoryException {
		
		Atividade atividadeNova = new Atividade();
		atividadeNova.setNome("Atividade outra coisa");
		atividadeNova.setPrioridade("IMPORTANTE");
		fachada.criarAtividade(atividadeNova);//persistindo
		Atividade atividadeParaAlterar = fachada.buscarAtividadePorNome(atividadeNova.getNome());
		atividadeParaAlterar.setPrioridade("URGENTE");
		fachada.alterarAtividade(atividadeParaAlterar);//ALTERANDO A ATIVIDADE		
		Assert.assertEquals(fachada.buscarAtividadePorNome(atividadeNova.getNome()).getPrioridade(), "URGENTE");
		
	}

	/**CORRIGIDO
	 * @throws RepositoryException */

	@Test
	public void alterarProjetoTest() throws RepositoryException {
		
		Projeto projetoNovo = new Projeto();
		projetoNovo.setNome("PROJETO TESTE");
		fachada.criarProjeto(projetoNovo);
		
		Projeto projetoAlterado = fachada.buscaProjetoPorNome(projetoNovo.getNome());		
		projetoAlterado.setNome("PROJETO TESTE ALTERADO");
		fachada.alterarProjeto(projetoAlterado);
	
		//verifica se projeto o nome foi alterado
		Assert.assertEquals(fachada.buscaProjetoPorNome("PROJETO TESTE ALTERADO").getNome(), "PROJETO TESTE ALTERADO");
		
	}
	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test
	public void alterarUsuarioTest() throws RepositoryException {
		
		Usuario usuarioNovo = new Usuario();
		usuarioNovo.setNome("Vandhuy Martins");
		usuarioNovo.setCargo("ASPIRA");
		usuarioNovo.setEmail("dhuy@gmail.com");
		usuarioNovo.setLogin("dhuy");
		usuarioNovo.setPapel("ROLE_ADMIN");
		usuarioNovo.setStatus(true);
		fachada.criarUsuario(usuarioNovo);//persisti
		Usuario usuarioParaAlterar = fachada.buscarUsuarioPorNome(usuarioNovo.getNome());
		usuarioParaAlterar.setPapel("ROLE_USER");
		fachada.alterarUsuario(usuarioParaAlterar);//altera
		
		Assert.assertEquals(fachada.buscarUsuarioPorNome(usuarioNovo.getNome()).getPapel(), "ROLE_USER");
	}
		
	/**CORRIGIDO*/
	@Test(expected=NoResultException.class)
	public void removerUsuarioTest() throws RepositoryException {
		
		Usuario usuarioNovo = new Usuario();
		usuarioNovo.setNome("Vandhuy Martins");
		usuarioNovo.setCargo("ASPIRA");
		usuarioNovo.setEmail("dhuy@gmail.com");
		usuarioNovo.setLogin("dhuy");
		usuarioNovo.setPapel("ROLE_ADMIN");
		usuarioNovo.setStatus(true);
		fachada.criarUsuario(usuarioNovo);//CRIANDO UM USUARIO		
		Usuario usuario = fachada.buscarUsuarioPorNome(usuarioNovo.getNome());		
		fachada.removerUsuario(usuario);//removendo o usuario				
		fachada.buscarUsuarioPorNome(usuarioNovo.getNome());//aguardando a exceção no result exception
		
	}
	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test(expected=NoResultException.class)
	public void removerProjetoTest() throws RepositoryException {
		Projeto projeto = new Projeto();
		projeto.setNome("PROJETO TESTE REMOVER");
		fachada.criarProjeto(projeto);
		Projeto projetoRemoved = fachada.buscaProjetoPorNome(projeto.getNome());		
		fachada.removerProjeto(projetoRemoved);//removendo projeto				
		fachada.buscaProjetoPorNome(projeto.getNome());//aguadando exceção no result exception
		
	}


	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test(expected=NoResultException.class)
	public void removerClienteTest() throws RepositoryException {		
		cliente = new Cliente();
		cliente.setNome("Renna Oliveira");
		fachada.criarCliente(cliente);//criando cliente
		Cliente clienteRemoved = fachada.buscarClientePorNome(cliente.getNome());
		fachada.removerCliente(clienteRemoved);//removendo cliente
		fachada.buscarClientePorNome(cliente.getNome());//aguardando exceção
	}


	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test(expected=NoResultException.class)
	public void removerAtividadeTest() throws RepositoryException {
		
		atividade = new Atividade();
		atividade.setNome("Melhoria nos testes");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("O objetivo é melhorar os testes");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("IMPORTANTE");
		// criando a atividade
		fachada.criarAtividade(atividade);//criando a tividade		
		Atividade atividadeRemoved = fachada.buscarAtividadePorNome(atividade.getNome());		
		fachada.removerAtividade(atividadeRemoved);//removendo a atividade criada
		fachada.buscarAtividadePorNome(atividade.getNome());//aguardando a exceção
	}
	


	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test
	public void localizarUsuarioPorNome() throws RepositoryException {
		
		usuario = new Usuario();
		usuario.setNome("Renan 1");
		fachada.criarUsuario(usuario);//criando usuario
		Assert.assertEquals(fachada.buscarUsuarioPorNome(usuario.getNome()).getNome(), usuario.getNome());

	}


	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test
	public void localizarProjetoPorNome() throws RepositoryException {
		Projeto projeto = new Projeto();
		projeto.setNome("APS PROJETO TESTE NOME");
		fachada.criarProjeto(projeto);//criando projeto
		Assert.assertEquals(fachada.buscaProjetoPorNome(projeto.getNome()).getNome(), projeto.getNome() );
		
	}


	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test
	public void localizarClientePorNome() throws RepositoryException {
		cliente = new Cliente();
		cliente.setNome("Vandhuy");
		fachada.criarCliente(cliente);
		Assert.assertEquals(fachada.buscarClientePorNome(cliente.getNome()).getNome(), cliente.getNome());
	}


	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test
	public void localizarAtividadePorNome() throws RepositoryException {
		atividade = new Atividade();
		atividade.setNome("Erick");
		fachada.criarAtividade(atividade);
		Assert.assertEquals(fachada.buscarAtividadePorNome(atividade.getNome()).getNome(), atividade.getNome());
	}
	

	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test
	public void localizarUsuarioPorID() throws RepositoryException {
		usuario = new Usuario();
		usuario.setNome("Jose");
		fachada.criarUsuario(usuario);
			
		Assert.assertEquals(fachada.buscarUsuarioPorId(1).getNome(),usuario.getNome());
	}


	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test
	public void localizarProjetoPorID() throws RepositoryException {
		projeto = new Projeto();
		projeto.setNome("Renan Project");
		fachada.criarProjeto(projeto);
		Assert.assertEquals(fachada.buscarProjetoPorID(1).getNome(), projeto.getNome());
	}


	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test
	public void localizarClientePorID() throws RepositoryException {
		cliente = new Cliente();
		cliente.setNome("Erick Guy");
		fachada.criarCliente(cliente);
		Assert.assertEquals(fachada.buscarClientePorId(1).getNome(), cliente.getNome());
	}


	/**CORRIGIDO
	 * @throws RepositoryException */
	@Test
	public void localizarAtividadePorID() throws RepositoryException {
		atividade = new Atividade();
		atividade.setNome("Criar um fachada");
		fachada.criarAtividade(atividade);
		Assert.assertEquals(fachada.buscarAtividadePorId(1).getNome(), atividade.getNome());
	}
	
}
