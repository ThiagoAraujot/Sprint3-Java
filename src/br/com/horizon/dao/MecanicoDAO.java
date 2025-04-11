package br.com.horizon.dao;

import br.com.horizon.entities.Mecanico;

import java.util.List;

public interface MecanicoDAO {

    void save(Mecanico mecanico);
    void update(Mecanico mecanico);
    void delete(int id);
    Mecanico searchById(int id);
    List<Mecanico> listAll();

}
