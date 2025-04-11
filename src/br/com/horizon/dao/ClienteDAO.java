package br.com.horizon.dao;

import br.com.horizon.entities.Cliente;

import java.util.List;

public interface ClienteDAO {

    void save(Cliente cliente);
    void update(Cliente Cliente);
    void delete(int id);
    Cliente searchById(int id);
    List<Cliente> listAll();
}
