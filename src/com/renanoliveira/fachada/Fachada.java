package com.renanoliveira.fachada;

import java.util.List;

import com.renanoliveira.dao.AtividadeDao;
import com.renanoliveira.dao.CargoDao;
import com.renanoliveira.dao.ClienteDao;
import com.renanoliveira.dao.ProjetoDao;
import com.renanoliveira.dao.RepositoryException;
import com.renanoliveira.dao.SetorDao;
import com.renanoliveira.dao.UsuarioDao;
import com.renanoliveira.entity.Atividade;
import com.renanoliveira.entity.Cargo;
import com.renanoliveira.entity.Cliente;
import com.renanoliveira.entity.Projeto;
import com.renanoliveira.entity.Setor;
import com.renanoliveira.entity.Usuario;
import com.renanoliveira.logic.AtividadeBL;

/**
 *
 * @author renanaoliveira
 */
public class Fachada {

    private AtividadeDao atividadeDao;
    private ProjetoDao projetoDao;
    private UsuarioDao usuarioDao;
    private ClienteDao clienteDao;
    private AtividadeBL atividadeBL;
    private SetorDao setorDao;
    private CargoDao cargoDao;
    
    public Fachada(){
    
        atividadeDao = new AtividadeDao();
        projetoDao = new ProjetoDao();
        clienteDao = new ClienteDao();
        usuarioDao = new UsuarioDao();
        atividadeBL = new AtividadeBL();
        setorDao = new SetorDao();
        cargoDao = new CargoDao();
    
    }

    public void criarProjeto(Projeto projeto) throws RepositoryException {
        projetoDao.save(projeto);
    }

    public void removerProjeto(Projeto projeto) {
        projetoDao.delete(projeto);
    }

    public Projeto alterarProjeto(Projeto projeto) {
        return projetoDao.update(projeto);
    }

    public List<Projeto> buscarTodosProjetos() {
        return projetoDao.listarTodos();
    }

    public Projeto buscarProjetoPorID(int idProjeto) {
        return projetoDao.findId(idProjeto);
    }

    public void criarCliente(Cliente cliente) throws RepositoryException {
        clienteDao.save(cliente);
    }

    public void removerCliente(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    public Cliente alterarCliente(Cliente cliente) {
        return  clienteDao.update(cliente);
    }

    public List<Cliente> buscarTodosClientes() {
        return clienteDao.listarTodos();
    }

    public Cliente buscarClientePorId(int idCliente) {
        return clienteDao.findId(idCliente);
    }

    public void criarAtividade(Atividade atividade) throws RepositoryException {
        atividadeBL.criarAtividade(atividade);
    }

    public void removerAtividade(Atividade atividade) {
        atividadeDao.delete(atividade);
    }

    public Atividade alterarAtividade(Atividade atividade) {
        return atividadeDao.update(atividade);
    }

    public List<Atividade> buscarTodasAtividades() {
        return atividadeDao.findAll();
    }

    public Atividade buscarAtividadePorId(int idArividade) {
        return atividadeDao.findId(idArividade);
    }

    public void criarUsuario(Usuario usuario) throws RepositoryException {
        usuarioDao.save(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    public Usuario alterarUsuario(Usuario usuario) {
        return usuarioDao.update(usuario);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioDao.listarTodos();
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
        return usuarioDao.findId(idUsuario);
    }

	public Usuario buscarUsuarioPorNome(String nome) {
		
		return usuarioDao.findByName(nome);
	}

	public Projeto buscaProjetoPorNome(String nome) {
		return projetoDao.findByName(nome);
	}

	public Cliente buscarClientePorNome(String nome) {
		return clienteDao.findByName(nome);
	}

	public Atividade buscarAtividadePorNome(String nome) {
		return atividadeDao.findByName(nome);
	}
	
	public void criarSetor(Setor setor) throws RepositoryException {
		setorDao.save(setor);
	}

	public void removerSetor(Setor setor) {
		setorDao.delete(setor);
	}

	public Setor alterarSetor(Setor setor) {
		return setorDao.update(setor);
	}

	public List<Setor> buscarTodosSetor() {
		return setorDao.findAll();
	}

	public Setor buscarSetorPorID(int id) {
		return setorDao.findId(id);
	}
	
	public void criarCargo(Cargo cargo) throws RepositoryException {
		cargoDao.save(cargo);
	}

	public void removerCargo(Cargo cargo) {
		cargoDao.delete(cargo);
	}

	public Cargo alterarCargo(Cargo cargo) {
		return cargoDao.update(cargo);
	}

	public List<Cargo> buscarTodosCargo() {
		return cargoDao.findAll();
	}

	public Cargo buscarCargoPorID(int id) {
		return cargoDao.findId(id);
	}


}
