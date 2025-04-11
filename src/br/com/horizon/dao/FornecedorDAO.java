package br.com.horizon.dao;

import br.com.horizon.entities.Fornecedor;

import java.util.List;

public interface FornecedorDAO {
    void save(Fornecedor fornecedor);
    void update(Fornecedor fornecedor);
    void delete(int id);
    Fornecedor searchById(int id);
    List<Fornecedor> listAll();
}
