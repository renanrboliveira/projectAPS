package com.renanoliveira.dao;

import java.util.List;

import com.renanoliveira.entity.Atividade;

/**
 *
 * @author renanaoliveira
 */
public class AtividadeDao extends GenericDAOImpl<Atividade> {
    
    public AtividadeDao(){
    
        super(Atividade.class);
    }
    
    public List<Atividade> listarTodos() {

        return getEntityManager().createQuery("SELECT a FROM Atividade a").getResultList();


    }
    
    public Atividade findByName(String nome){
    	return (Atividade) getEntityManager().createQuery("SELECT a FROM Atividade a WHERE nome = "+nome).getResultList().get(0);
    }

	@Override
	public List<Atividade> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
