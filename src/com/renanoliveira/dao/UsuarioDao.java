package com.renanoliveira.dao;

import com.renanoliveira.entity.Usuario;
import java.util.List;

/**
 *
 * @author renanaoliveira
 */
public class UsuarioDao extends GenericDAO<Usuario> {

    public UsuarioDao() {

        super(Usuario.class);

    }

    public List<Usuario> listarTodos() {

        return getEntityManager().createQuery("SELECT u FROM Usuario u").getResultList();


    }
}
