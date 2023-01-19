package com.technolgyworld.springboot.app.model.dao;

import com.technolgyworld.springboot.app.model.entity.Cliente;

import java.util.List;

public interface IClienteDAO {

    public List<Cliente> findAll();

    public void save(Cliente cliente);

    public Cliente findOne(Long id);

    public void delete(Long id);

}
