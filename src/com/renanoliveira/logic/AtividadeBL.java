package com.renanoliveira.logic;

import com.renanoliveira.dao.AtividadeDao;
import com.renanoliveira.dao.RepositoryException;
import com.renanoliveira.entity.Atividade;

public class AtividadeBL {
	
	private AtividadeDao atividadeDao;
	
	public AtividadeBL() {
		
		atividadeDao = new AtividadeDao();
	
	}
	
	public Atividade criarAtividade(Atividade atividade) throws RepositoryException{
		
		if(atividade.getProjeto() != null){
			atividadeDao.save(atividade);
		}			
		
		return atividade;
	}

}
