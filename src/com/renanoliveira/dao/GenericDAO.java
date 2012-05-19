package com.renanoliveira.dao;

import java.util.List;

/**
 * @author Renan Oliveira
 * */
public interface GenericDAO<T> {
	
    public void save(T entity)  throws RepositoryException;

	public void delete(T entity);

	public T update(T entity);

	public T findId(int entityID);
	
	public List<T> findAll();
	
	public T findByName(String name);

	

}
