package com.renanoliveira.fachada;

import java.util.List;

import com.renanoliveira.dao.AtividadeDao;
import com.renanoliveira.dao.ClienteDao;
import com.renanoliveira.dao.ProjetoDao;
import com.renanoliveira.dao.UsuarioDao;
import com.renanoliveira.entity.Atividade;
import com.renanoliveira.entity.Cliente;
import com.renanoliveira.entity.Projeto;
import com.renanoliveira.entity.Usuario;

/**
 *
 * @author renanaoliveira
 */
public class Fachada {

    private AtividadeDao atividadeDao;
    private ProjetoDao projetoDao;
    private UsuarioDao usuarioDao;
    private ClienteDao clienteDao;
    
    public Fachada(){
    
        atividadeDao = new AtividadeDao();
        projetoDao = new ProjetoDao();
        clienteDao = new ClienteDao();
        usuarioDao = new UsuarioDao();
    
    }

    public void criarProjeto(Projeto projeto) {
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
        return projetoDao.find(idProjeto);
    }

    public void criarCliente(Cliente cliente) {
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
        return clienteDao.find(idCliente);
    }

    public void criarAtividade(Atividade atividade) {
        atividadeDao.save(atividade);
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
        return atividadeDao.find(idArividade);
    }

    public void criarUsuario(Usuario usuario) {
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
        return usuarioDao.find(idUsuario);
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
}
