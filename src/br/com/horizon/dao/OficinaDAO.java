package br.com.horizon.dao;
import br.com.horizon.entities.Oficina;
import java.util.List;

public interface OficinaDAO {

    void save(Oficina oficina);
    void update(Oficina oficina);
    void delete(int id);
    Oficina searchById(int id);
    List<Oficina> listAll();

}
