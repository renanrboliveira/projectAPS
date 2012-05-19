package com.renanoliveira.dao;

import com.renanoliveira.entity.Cliente;
import com.renanoliveira.entity.Usuario;
import java.util.List;

/**
 *
 * @author renanaoliveira
 */
public class UsuarioDao extends GenericDAOImpl<Usuario> {

    public UsuarioDao() {

        super(Usuario.class);

    }

    public List<Usuario> listarTodos() {

        return getEntityManager().createQuery("SELECT u FROM Usuario u").getResultList();

    }
    
    public Usuario findByName(String nome){
    	return (Usuario) getEntityManager().createQuery("SELECT u FROM Usuario u WHERE nome = '"+nome+"'").getSingleResult();
    }

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
