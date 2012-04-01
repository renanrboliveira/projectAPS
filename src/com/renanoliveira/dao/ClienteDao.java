package com.renanoliveira.dao;

import com.renanoliveira.entity.Cliente;
import com.renanoliveira.util.HibernateUtil;
import javax.persistence.EntityManager;

/**
 *
 * @author renanaoliveira
 */
public class ClienteDao extends GenericDAO<Cliente> {    
    
    public ClienteDao(){
    
         super(Cliente.class);
    }
    
    
    
}
