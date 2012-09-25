package com.renanoliveira.dao;

import java.util.List;

import com.renanoliveira.entity.Cargo;

public class CargoDao extends GenericDAOImpl<Cargo>{

	public CargoDao() {
		super(Cargo.class);
	}

	@Override
	public List<Cargo> findAll() {
		return getEntityManager().createQuery("SELECT c FROM Cargo c").getResultList();

	}

	@Override
	public Cargo findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
