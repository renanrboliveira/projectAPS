package com.renanoliveira.dao;

import java.util.List;

import com.renanoliveira.entity.Cliente;

/**
 *
 * @author renanaoliveira
 */
public class ClienteDao extends GenericDAOImpl<Cliente> {    
    
    public ClienteDao(){
    
         super(Cliente.class);
    }
    
    public List<Cliente> listarTodos() {

        return getEntityManager().createQuery("SELECT c FROM Cliente c").getResultList();


    }
    
    public Cliente findByName(String nome){
    	return (Cliente) getEntityManager().createQuery("SELECT c FROM Cliente c WHERE nome = "+nome).getResultList().get(0);
    }

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
}
