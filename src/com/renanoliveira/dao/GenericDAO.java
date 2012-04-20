package com.renanoliveira.dao;

import com.renanoliveira.util.HibernateUtil;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author renanaoliveira
 */
public abstract class GenericDAO<T> {
    
    
    private EntityManager entityManager;
    private Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        entityManager = HibernateUtil.getInstance().getFactory().createEntityManager();
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public void delete(T entity) {
        T entityToBeRemoved = entityManager.merge(entity);

        entityManager.remove(entityToBeRemoved);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public T find(int entityID) {
        return entityManager.find(entityClass, entityID);
    }

    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method
    public List<T> findAll() {
        //CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        //cq.select(cq.from(entityClass));
        //return entityManager.createQuery(cq).getResultList();
        return null;

    }
    
    public EntityManager getEntityManager(){
    
        return entityManager;
    }
    
    

    // Using the unchecked because JPA does not have a
    // ery.getSingleResult()<T> method
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;

        try {
            Query query = entityManager.createNamedQuery(namedQuery);

            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }

            result = (T) query.getSingleResult();

        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
        }

        return result;
    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {

        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}