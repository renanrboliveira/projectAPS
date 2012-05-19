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
    //em.createQuery(
    //		"SELECT e " + "FROM Project p JOIN p.employees e "+ "WHERE p.name = ?1 " + "ORDER BY e.name", Employee.class)
    //	.setParameter(1, projectName) .getResultList();
    
    public Usuario findByName(String nome){
    	return (Usuario) getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.nome = ?1 ", Usuario.class).setParameter(1, nome).getResultList().get(0);
    }

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
