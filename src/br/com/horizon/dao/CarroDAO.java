package br.com.horizon.dao;

import java.util.List;

import br.com.horizon.entities.Carro;

public interface CarroDAO {
	
	void save(Carro carro);
	void update(Carro carro);
	void delete(int id);
	Carro searchById(int id);
	List<Carro> listAll();

}
