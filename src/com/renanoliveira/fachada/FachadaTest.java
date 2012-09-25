package com.renanoliveira.fachada;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.renanoliveira.dao.RepositoryException;
import com.renanoliveira.entity.Atividade;
import com.renanoliveira.entity.Cargo;
import com.renanoliveira.entity.Cliente;
import com.renanoliveira.entity.Projeto;
import com.renanoliveira.entity.Setor;
import com.renanoliveira.entity.Usuario;
import com.renanoliveira.util.HibernateUtil;

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
	public void criarCargo() throws RepositoryException{
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Pleno");
		fachada.criarCargo(cargo);
		Assert.assertNotNull(cargo.getId());
		Cargo cargoFind = fachada.buscarCargoPorID(cargo.getId());
		Assert.assertEquals(cargo, cargoFind);
	}
	
	@Test
	public void criarSetor() throws RepositoryException{
		Setor setor = new Setor();
		setor.setDescricao("Suporte");
		fachada.criarSetor(setor);
		Assert.assertNotNull(setor.getId());
		Setor setorFind = fachada.buscarSetorPorID(setor.getId());
		Assert.assertEquals(setor, setorFind);
	}

	@Test
	public void criarProjeto() throws RepositoryException {		
		projeto = new Projeto();
		projeto.setNome("APS PROJETO");		
		fachada.criarProjeto(projeto);		
		Assert.assertNotNull(projeto.getId());
		Projeto projetoFind = fachada.buscarProjetoPorID(projeto.getId());
		Assert.assertEquals(projetoFind, projeto); 
	}

	@Test
	public void criarUsuario() throws RepositoryException {

		usuario = new Usuario();
		usuario.setEmail("renan@gmail.com");
		usuario.setLogin("renan");
		usuario.setNome("Renan Oliveira");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		Cargo cargo = new Cargo();
		cargo.setDescricao("ASPIRA");
		Setor setor = new Setor();
		setor.setDescricao("Desenvolvimento");
		fachada.criarUsuario(usuario);		
		Usuario usuarioFind = fachada.buscarUsuarioPorId(1);
		Assert.assertEquals(usuarioFind, usuario);
		

	}

	@Test
	public void criarCliente() throws RepositoryException {
				
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar");
		fachada.criarCliente(cliente);
		Cliente clienteFind = fachada.buscarClientePorId(cliente.getId());
		projeto = new Projeto();
		projeto.setNome("Projeto 1");
		projeto.setCliente(clienteFind);
		fachada.criarProjeto(projeto);
		Projeto projeto1 = fachada.buscarProjetoPorID(projeto.getId());
		Assert.assertEquals(projeto1.getCliente(), clienteFind);
		projeto = new Projeto();
		projeto.setNome("Projeto 2");
		projeto.setCliente(clienteFind);
		fachada.criarProjeto(projeto);
		Projeto projeto2 = fachada.buscarProjetoPorID(projeto.getId());
		Assert.assertEquals(projeto2.getCliente(), clienteFind);
		projeto = new Projeto();
		projeto.setNome("Projeto 3");
		projeto.setCliente(clienteFind);
		fachada.criarProjeto(projeto);
		Projeto projeto3 = fachada.buscarProjetoPorID(projeto.getId());
		Assert.assertEquals(projeto3.getCliente(), clienteFind);
		projeto = new Projeto();
		projeto.setNome("Projeto 4");
		projeto.setCliente(clienteFind);
		fachada.criarProjeto(projeto);
		projeto = new Projeto();
		projeto.setNome("Projeto 3");
		projeto.setCliente(clienteFind);
		fachada.criarProjeto(projeto);
		
		Projeto projeto4 = fachada.buscarProjetoPorID(projeto.getId());
		Assert.assertEquals(projeto4.getCliente(), clienteFind);
		Assert.assertEquals(clienteFind, cliente);

	}
	
	@Test
	public void criarAtividade() throws RepositoryException {
		atividade = new Atividade();
		atividade.setNome("[MELHORIA] Boleto Bancário Test");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("asdfffasdfggasdfggas3edmd.");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("IMPORTANTE");
		usuario = new Usuario();
		Cargo cargo = new Cargo();
		cargo.setDescricao("Estagiário");
		fachada.criarCargo(cargo);
		usuario.setCargo(cargo);
		Setor setor = new Setor();
		setor.setDescricao("Desenvolvimento");
		fachada.criarSetor(setor);
		usuario.setSetor(setor);		
		usuario.setEmail("renan@gmail.com");
		usuario.setLogin("renan");
		usuario.setNome("Renan Oliveira");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);
		fachada.criarUsuario(usuario);	
		atividade.setUsuario(fachada.buscarUsuarioPorId(usuario.getId()));
		projeto = new Projeto();		
		projeto.setNome("PROJETO BANCARIO");		
		fachada.criarProjeto(projeto);
		atividade.setProjeto(fachada.buscarProjetoPorID(projeto.getId()));
		fachada.criarAtividade(atividade);
		Atividade atividadeFind = fachada.buscarAtividadePorId(atividade.getId());
		Assert.assertEquals(atividadeFind, atividade);
	}

	@Test
	public void criarProjetoSemCliente() throws RepositoryException {

		projeto = new Projeto();		
		projeto.setNome("PROJETO BANCARIO");				
		fachada.criarProjeto(projeto);		
		Projeto projetoFind = fachada.buscarProjetoPorID(projeto.getId());
		Assert.assertNull(projetoFind.getCliente());
		
	}

	@Test
	public void criarProjetoSemAtividade() throws RepositoryException {
		
		String stringTest = "APS PROJETO Sem Atividade";		
		projeto = new Projeto();
		projeto.setNome(stringTest);
		projeto.setAtividades(null);	
		fachada.criarProjeto(projeto);		
		Assert.assertNull(fachada.buscarProjetoPorID(projeto.getId()).getAtividades());

	}
	
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
		fachada.criarAtividade(atividade);
		
		Assert.assertNull(atividade.getId());
		
	}

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
		Projeto projeto = new Projeto();
		projeto.setNome("APS");
		fachada.criarProjeto(projeto);
		Assert.assertNotNull(projeto.getId());
		atividade.setProjeto(projeto);
		fachada.criarAtividade(atividade);
		Assert.assertNull(fachada.buscarAtividadePorId(atividade.getId()).getUsuario());
	}

	@Test
	public void criarUsuarioSemAtividade() throws RepositoryException {
		
		usuario = new Usuario();
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Junior");
		fachada.criarCargo(cargo);
		usuario.setCargo(cargo);		
		usuario.setEmail("renan@gmail.com");
		usuario.setLogin("renan");
		usuario.setNome("Renan 1");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		fachada.criarUsuario(usuario);
		Assert.assertNull(fachada.buscarUsuarioPorId(usuario.getId()).getAtividades());
		
	}
	
	@Test
	public void criarClienteSemProjeto() throws RepositoryException {
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste");
		cliente.setProjetos(null);
		fachada.criarCliente(cliente);
		Assert.assertNull(fachada.buscarClientePorId(cliente.getId()).getProjetos());

		HibernateUtil.resetEntityManager();

	}
	
	@Test
	public void listarTodosSetor() throws RepositoryException {
		Setor setor = new Setor();
		setor.setDescricao("Suporte");
		fachada.criarSetor(setor);
		Assert.assertNotNull(setor.getId());
		Setor setorFind = fachada.buscarSetorPorID(setor.getId());
		Assert.assertEquals(setor, setorFind);
		setor = new Setor();
		setor.setDescricao("Desenvolvedor");
		fachada.criarSetor(setor);		
		Assert.assertNotNull(setor.getId());
		setorFind = fachada.buscarSetorPorID(setor.getId());
		Assert.assertEquals(setor, setorFind);
		setor = new Setor();
		setor.setDescricao("Diretoria");
		fachada.criarSetor(setor);
		Assert.assertNotNull(setor.getId());
		setorFind = fachada.buscarSetorPorID(setor.getId());
		Assert.assertEquals(setor, setorFind);
		
		Assert.assertEquals(fachada.buscarTodosSetor().size(), 3);

	}
	@Test
	public void listarTodosCargos() throws RepositoryException {
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Senior");
		fachada.criarCargo(cargo);
		Assert.assertNotNull(cargo.getId());
		Cargo cargoFind = fachada.buscarCargoPorID(cargo.getId());
		Assert.assertEquals(cargo, cargoFind);		
		cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Senior");
		fachada.criarCargo(cargo);
		Assert.assertNotNull(cargo.getId());
		cargoFind = fachada.buscarCargoPorID(cargo.getId());
		Assert.assertEquals(cargo, cargoFind);
		Assert.assertEquals(fachada.buscarTodosCargo().size(), 2);

	}

	@Test
	public void listarTodosUsuariosTest() throws RepositoryException {

		usuario = new Usuario();
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Junior");
		fachada.criarCargo(cargo);
		usuario.setCargo(cargo);	
		usuario.setEmail("renan3@gmail.com");
		usuario.setLogin("renan1");
		usuario.setNome("Renan 1");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		usuario.setAtividades(null);
		fachada.criarUsuario(usuario);
		Usuario usuarioFind = fachada.buscarUsuarioPorId(usuario.getId());
		Assert.assertEquals(usuario, usuarioFind);
		usuario = new Usuario();
		cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Junior");
		fachada.criarCargo(cargo);
		usuario.setCargo(cargo);	
		usuario.setEmail("renan2@gmail.com");
		usuario.setLogin("renan2");
		usuario.setNome("Renan 3");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		usuario.setAtividades(null);
		fachada.criarUsuario(usuario);
		usuarioFind = fachada.buscarUsuarioPorId(usuario.getId());
		Assert.assertEquals(usuario, usuarioFind);
		fachada.criarUsuario(usuario);
		usuario = new Usuario();
		cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Junior");
		fachada.criarCargo(cargo);
		usuario.setCargo(cargo);	
		usuario.setEmail("renan1@gmail.com");
		usuario.setLogin("renan3");
		usuario.setNome("Renan 2");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		usuario.setAtividades(null);
		fachada.criarUsuario(usuario);
		usuarioFind = fachada.buscarUsuarioPorId(usuario.getId());
		Assert.assertEquals(usuario, usuarioFind);
		
		Assert.assertEquals(fachada.buscarTodosUsuarios().size(), 3);		

	}

	@Test
	public void listarTodosClientesTest() throws RepositoryException {
		
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste 1");
		fachada.criarCliente(cliente);
		Cliente clienteFind = fachada.buscarClientePorId(cliente.getId());
		Assert.assertEquals(cliente, clienteFind);
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste 2");
		fachada.criarCliente(cliente);
		clienteFind = fachada.buscarClientePorId(cliente.getId());
		Assert.assertEquals(cliente, clienteFind);
		cliente = new Cliente();
		cliente.setNome("PROF Rodrigo Vilar Teste 3");
		fachada.criarCliente(cliente);
		clienteFind = fachada.buscarClientePorId(cliente.getId());
		Assert.assertEquals(cliente, clienteFind);
		
		Assert.assertEquals(fachada.buscarTodosClientes().size(), 3);
	}

	@Test
	public void listarTodasAtividadesTest() throws RepositoryException {
		
		atividade = new Atividade();
		atividade.setNome("[MELHORIA] Boleto Bancário Test 1");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("haahshhdhshasdfas");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("FECHADA");
		atividade.setPrioridade("IMPORTANTE");
		usuario = new Usuario();
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Junior");
		fachada.criarCargo(cargo);
		usuario.setCargo(cargo);	
		usuario.setEmail("renan1@gmail.com");
		usuario.setLogin("renan3");
		usuario.setNome("Renan");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);	
		fachada.criarUsuario(usuario);		
		atividade.setUsuario(usuario);
		projeto = new Projeto();
		projeto.setNome("APS PROJETO 1");		
		fachada.criarProjeto(projeto);		
		atividade.setProjeto(projeto);
		fachada.criarAtividade(atividade);
		Atividade atividadeFind = fachada.buscarAtividadePorId(atividade.getId());
		Assert.assertEquals(atividade, atividadeFind);
		
		atividade = new Atividade();
		atividade.setNome("[MELHORIA] Boleto Bancário Test 2");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("asdfffasdfggasdfggas3edmdSDFASDFFASS.");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("IMPORTANTE");
		usuario = new Usuario();
		cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Junior");
		fachada.criarCargo(cargo);
		usuario.setCargo(cargo);	
		usuario.setEmail("renan1@gmail.com");
		usuario.setLogin("renan3");
		usuario.setNome("Renan");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		fachada.criarUsuario(usuario);
		atividade.setUsuario(usuario);
		projeto = new Projeto();
		projeto.setNome("APS PROJETO 1");		
		fachada.criarProjeto(projeto);
		atividade.setProjeto(projeto);
		fachada.criarAtividade(atividade);
		atividadeFind = fachada.buscarAtividadePorId(atividade.getId());
		Assert.assertEquals(atividade, atividadeFind);
		
		atividade = new Atividade();
		atividade.setNome("[MELHORIA] Boleto Bancário Test 3");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("asdfffasdfggasdfggas3edmdASSFSWWSS.");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("IMPORTANTE");
		usuario = new Usuario();
		cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Junior");
		fachada.criarCargo(cargo);
		usuario.setCargo(cargo);	
		usuario.setEmail("renan1@gmail.com");
		usuario.setLogin("renan3");
		usuario.setNome("Renan");
		usuario.setPapel("ROLE_ADMIN");
		usuario.setSenha("123213");
		usuario.setStatus(true);		
		fachada.criarUsuario(usuario);
		atividade.setUsuario(usuario);
		projeto = new Projeto();
		projeto.setNome("APS PROJETO 1");		
		fachada.criarProjeto(projeto);
		atividade.setProjeto(projeto);
		fachada.criarAtividade(atividade);
		atividadeFind = fachada.buscarAtividadePorId(atividade.getId());
		Assert.assertEquals(atividade, atividadeFind);
		
		Assert.assertEquals(fachada.buscarTodasAtividades().size(), 3);
		HibernateUtil.resetEntityManager();


	}

	@Test
	public void listarTodosProjetosTest() throws RepositoryException {
		projeto = new Projeto();
		projeto.setNome("APS PROJETO 1");		
		fachada.criarProjeto(projeto);	
		Projeto projetoFind = fachada.buscarProjetoPorID(projeto.getId());
		Assert.assertEquals(projeto, projetoFind);
		projeto = new Projeto();
		projeto.setNome("APS PROJETO 2");
		fachada.criarProjeto(projeto);
		projetoFind = fachada.buscarProjetoPorID(projeto.getId());
		Assert.assertEquals(projeto, projetoFind);
		projeto = new Projeto();
		projeto.setNome("APS PROJETO 3");		
		fachada.criarProjeto(projeto);
		projetoFind = fachada.buscarProjetoPorID(projeto.getId());
		Assert.assertEquals(projeto, projetoFind);
		Assert.assertEquals(fachada.buscarTodosProjetos().size(), 3);
	}
	
	@Test
	public void alterarSetor() throws RepositoryException {
		Setor setor = new Setor();
		setor.setDescricao("Design");
		fachada.criarSetor(setor);
		Assert.assertNotNull(setor.getId());
		Setor setorFind = fachada.buscarSetorPorID(setor.getId());
		setorFind.setDescricao("Designer");
		fachada.alterarSetor(setorFind);
		Setor setorAlterado = fachada.buscarSetorPorID(setorFind.getId());
		Assert.assertEquals(setorFind.getDescricao(), setorAlterado.getDescricao());
	}
	
	@Test
	public void alterarCargo() throws RepositoryException {
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Senior");
		fachada.criarCargo(cargo);
		Assert.assertNotNull(cargo.getId());		
		Cargo cargoFind = fachada.buscarCargoPorID(cargo.getId());
		cargoFind.setDescricao("Design Gráfico");
		fachada.alterarCargo(cargoFind);
		Cargo cargoAlterado = fachada.buscarCargoPorID(cargoFind.getId());
		Assert.assertEquals(cargoFind.getDescricao(), cargoAlterado.getDescricao());
	}

	@Test
	public void alterarClienteTest() throws RepositoryException {

		Cliente clienteNovo = new Cliente();
		clienteNovo.setNome("Joaozinho");
		fachada.criarCliente(clienteNovo);
		Assert.assertNotNull(clienteNovo.getId());
		Cliente clienteParaAlterar = fachada.buscarClientePorId(clienteNovo.getId());
		clienteParaAlterar.setNome("Joao");		
		fachada.alterarCliente(clienteParaAlterar);
		Cliente clienteAlterado = fachada.buscarClientePorId(clienteParaAlterar.getId());
		Assert.assertEquals(clienteAlterado.getNome(),clienteNovo.getNome());
		
	}

	@Test
	public void alterarAtividadeTest() throws RepositoryException {
		
		Atividade atividadeNova = new Atividade();
		atividadeNova.setNome("Atividade outra coisa");
		atividadeNova.setPrioridade("IMPORTANTE");
		atividadeNova.setStatus("ATIVO");
		projeto = new Projeto();
		projeto.setNome("BLa");
		fachada.criarProjeto(projeto);
		atividadeNova.setProjeto(projeto);
		fachada.criarAtividade(atividadeNova);
		Assert.assertNotNull(atividadeNova.getId());
		Atividade atividadeParaAlterar = fachada.buscarAtividadePorId(atividadeNova.getId());
		atividadeParaAlterar.setPrioridade("URGENTE");
		atividadeParaAlterar.setStatus("FECHADO");
		fachada.alterarAtividade(atividadeParaAlterar);		
		Assert.assertEquals(fachada.buscarAtividadePorId(atividadeNova.getId()).getPrioridade(), atividadeParaAlterar.getPrioridade());
		Assert.assertEquals(fachada.buscarAtividadePorId(atividadeNova.getId()).getStatus(), atividadeParaAlterar.getStatus());
		
	}

	@Test
	public void alterarProjetoTest() throws RepositoryException {
		
		Projeto projetoNovo = new Projeto();
		projetoNovo.setNome("PROJETO TESTE");
		fachada.criarProjeto(projetoNovo);		
		Assert.assertNotNull(projetoNovo.getId());
		Projeto projetoAlterado = fachada.buscarProjetoPorID(projetoNovo.getId());		
		projetoAlterado.setNome("PROJETO TESTE ALTERADO");
		fachada.alterarProjeto(projetoAlterado);
		Assert.assertEquals(fachada.buscarProjetoPorID(projetoAlterado.getId()).getNome(), projetoAlterado.getNome());
	}
	
	@Test
	public void alterarUsuarioTest() throws RepositoryException {
		
		Usuario usuarioNovo = new Usuario();
		usuarioNovo.setNome("Vandhuy Martins");
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Junior");
		fachada.criarCargo(cargo);
		Assert.assertNotNull(cargo.getId());
		usuarioNovo.setCargo(cargo);	
		usuarioNovo.setEmail("dhuy@gmail.com");
		usuarioNovo.setLogin("dhuy");
		usuarioNovo.setPapel("ROLE_ADMIN");
		usuarioNovo.setStatus(true);
		fachada.criarUsuario(usuarioNovo);
		Assert.assertNotNull(usuarioNovo.getId());
		Usuario usuarioParaAlterar = fachada.buscarUsuarioPorId(usuarioNovo.getId());
		usuarioParaAlterar.setPapel("ROLE_USER");
		fachada.alterarUsuario(usuarioParaAlterar);
		Assert.assertEquals(fachada.buscarUsuarioPorId(usuarioNovo.getId()).getPapel(), usuarioParaAlterar.getPapel());
	}
	
	@Test
	public void removerSetorTest() throws RepositoryException {
		Setor setor = new Setor();
		setor.setDescricao("Marketing");
		fachada.criarSetor(setor);
		Assert.assertNotNull(setor.getId());
		Setor setorFind = fachada.buscarSetorPorID(setor.getId());
		fachada.removerSetor(setorFind);
		Assert.assertNull(fachada.buscarSetorPorID(setorFind.getId()));

	}
	
	@Test
	public void removerCargoTest() throws RepositoryException {
		Cargo cargo = new Cargo();
		cargo.setDescricao("Publicitário");
		fachada.criarCargo(cargo);
		Assert.assertNotNull(cargo.getId());
		Cargo cargoFind = fachada.buscarCargoPorID(cargo.getId());
		fachada.removerCargo(cargoFind);
		Assert.assertNull(fachada.buscarCargoPorID(cargoFind.getId()));

	}
	@Test
	public void removerUsuarioTest() throws RepositoryException {
		
		Usuario usuarioNovo = new Usuario();
		usuarioNovo.setNome("Vandhuy Martins");
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Junior");
		fachada.criarCargo(cargo);
		usuarioNovo.setCargo(cargo);
		usuarioNovo.setEmail("dhuy@gmail.com");
		usuarioNovo.setLogin("dhuy");
		usuarioNovo.setPapel("ROLE_ADMIN");
		usuarioNovo.setStatus(true);
		fachada.criarUsuario(usuarioNovo);		
		Usuario usuario = fachada.buscarUsuarioPorId(usuarioNovo.getId());		
		fachada.removerUsuario(usuario);				
		Assert.assertNull(fachada.buscarUsuarioPorId(usuario.getId()));
		
	}

	@Test
	public void removerProjetoTest() throws RepositoryException {
		Projeto projeto = new Projeto();
		projeto.setNome("PROJETO TESTE REMOVER");
		fachada.criarProjeto(projeto);
		Projeto projetoRemoved = fachada.buscarProjetoPorID(projeto.getId());		
		fachada.removerProjeto(projetoRemoved);				
		Assert.assertNull(fachada.buscarProjetoPorID(projeto.getId()));
		
	}


	@Test
	public void removerClienteTest() throws RepositoryException {		
		cliente = new Cliente();
		cliente.setNome("Renna Oliveira");
		fachada.criarCliente(cliente);
		Cliente clienteRemoved = fachada.buscarClientePorId(cliente.getId());
		fachada.removerCliente(clienteRemoved);
		Assert.assertNull(fachada.buscarClientePorId(cliente.getId()));
	}


	@Test
	public void removerAtividadeTest() throws RepositoryException {
		
		atividade = new Atividade();
		atividade.setNome("Melhoria nos testes");
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("O objetivo é melhorar os testes");
		atividade.setDataCadastro(new Date());
		atividade.setStatus("ABERTA");
		atividade.setPrioridade("IMPORTANTE");
		Projeto projeto = new Projeto();
		projeto.setNome("APS");
		fachada.criarProjeto(projeto);
		Assert.assertNotNull(projeto.getId());
		atividade.setProjeto(projeto);
		fachada.criarAtividade(atividade);		
		Atividade atividadeRemoved = fachada.buscarAtividadePorId(atividade.getId());		
		fachada.removerAtividade(atividadeRemoved);
		Assert.assertNull(fachada.buscarAtividadePorId(atividadeRemoved.getId()));
	}
	
	@Test
	public void localizarSetorPorID() throws RepositoryException {
		Setor setor = new Setor();
		setor.setDescricao("Diretoria");
		fachada.criarSetor(setor);
		Assert.assertNotNull(setor.getId());
		Assert.assertNotNull(fachada.buscarSetorPorID(setor.getId()));

	}
	
	@Test
	public void localizarCargoPorID() throws RepositoryException {
		Cargo cargo = new Cargo();
		cargo.setDescricao("Diretor Chefe");
		fachada.criarCargo(cargo);
		Assert.assertNotNull(cargo.getId());
		Assert.assertNotNull(fachada.buscarCargoPorID(cargo.getId()));
		

	}
	
	@Test
	public void localizarUsuarioPorID() throws RepositoryException {
		usuario = new Usuario();
		usuario.setNome("Jose");
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor Junior");
		fachada.criarCargo(cargo);
		usuario.setCargo(cargo);		usuario.setEmail("jose@gmail.com");
		usuario.setLogin("jose");
		usuario.setSenha("jose123");
		fachada.criarUsuario(usuario);
		Assert.assertNotNull(fachada.buscarUsuarioPorId(usuario.getId()));
	}

	@Test
	public void localizarProjetoPorID() throws RepositoryException {
		projeto = new Projeto();
		projeto.setNome("Renan Project");
		fachada.criarProjeto(projeto);
		Assert.assertNotNull(fachada.buscarProjetoPorID(projeto.getId()));
	}


	@Test
	public void localizarClientePorID() throws RepositoryException {
		cliente = new Cliente();
		cliente.setNome("Erick Guy");
		fachada.criarCliente(cliente);
		Assert.assertNotNull(fachada.buscarClientePorId(cliente.getId()));
	}

	@Test
	public void localizarAtividadePorID() throws RepositoryException {
		atividade = new Atividade();
		atividade.setNome("Atividade sem fim");
		atividade.setDataCadastro(new Date());
		atividade.setDataInicio(new Date());
		atividade.setDataTermino(new Date());
		atividade.setDescricao("Esse projeto faz parte da disciplina");
		atividade.setPrioridade("URGENTE");
		projeto = new Projeto();
		projeto.setNome("Bla bla");
		fachada.criarProjeto(projeto);
		atividade.setProjeto(projeto);
		fachada.criarAtividade(atividade);
		Assert.assertNotNull(fachada.buscarAtividadePorId(atividade.getId()));
	}
	
}