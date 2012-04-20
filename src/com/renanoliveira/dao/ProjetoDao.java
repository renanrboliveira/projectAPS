package com.renanoliveira.dao;

import java.util.List;

import com.renanoliveira.entity.Cliente;
import com.renanoliveira.entity.Projeto;

/**
 *
 * @author Renan Oliveira
 */
public class ProjetoDao extends GenericDAO<Projeto> {
    
    

    public ProjetoDao() {
        
        super(Projeto.class);

    }
    
     public List<Projeto> listarTodos() {

        return getEntityManager().createQuery("SELECT p FROM Projeto p").getResultList();


    }
    
    public Projeto findByName(String nome){
    	return (Projeto) getEntityManager().createQuery("SELECT p FROM Projeto p WHERE nome = "+nome).getResultList().get(0);
    }
    
    
}
