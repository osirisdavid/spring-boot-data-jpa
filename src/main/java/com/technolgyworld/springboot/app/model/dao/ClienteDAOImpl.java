package com.technolgyworld.springboot.app.model.dao;

import com.technolgyworld.springboot.app.model.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClienteDAOImpl implements IClienteDAO {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAll() {
        // TODO Auto-generated method stub
        return em.createQuery("from Cliente").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        if (cliente.getId() != null && cliente.getId() > 0) {
            em.merge(cliente);
        } else {
            em.persist(cliente);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        em.remove(findOne(id));
    }

}
