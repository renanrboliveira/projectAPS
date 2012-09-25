package com.renanoliveira.dao;

import java.util.List;

import com.renanoliveira.entity.Setor;

public class SetorDao extends GenericDAOImpl<Setor> {

	public SetorDao() {
		super(Setor.class);
	}

	@Override
	public List<Setor> findAll() {
        return getEntityManager().createQuery("SELECT s FROM Setor s").getResultList();

	}

	@Override
	public Setor findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
