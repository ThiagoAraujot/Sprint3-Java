package br.com.horizon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.horizon.entities.Carro;

public class CarroDAOImpl implements CarroDAO {

    private Connection conn;

    public CarroDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Carro carro) {
        String insert = "INSERT INTO CARRO (ID_CARRO, ID_CLIENTE, ANO, PRECO, MARCA, MODELO) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setInt(1, carro.getId());
            ps.setInt(2, carro.getIdCliente());
            ps.setInt(3, carro.getAno());
            ps.setDouble(4, carro.getPreco());
            ps.setString(5, carro.getMarca());
            ps.setString(6, carro.getModelo());

            ps.executeUpdate();
            System.out.println("Carro inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Carro carro) {
        String update = "UPDATE CARRO SET ID_CLIENTE=?, ANO=?, PRECO=?, MARCA=?, MODELO=? WHERE ID_CARRO=?";

        try (PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setInt(1, carro.getIdCliente());
            ps.setInt(2, carro.getAno());
            ps.setDouble(3, carro.getPreco());
            ps.setString(4, carro.getMarca());
            ps.setString(5, carro.getModelo());
            ps.setInt(6, carro.getId());

            ps.executeUpdate();
            System.out.println("Carro atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM CARRO WHERE ID_CARRO=?";

        try (PreparedStatement ps = conn.prepareStatement(delete)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Carro removido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Carro searchById(int id) {
        String select = "SELECT * FROM CARRO WHERE ID_CARRO=?";

        try (PreparedStatement ps = conn.prepareStatement(select)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Carro(
                        rs.getInt("ID_CARRO"),
                        rs.getInt("ID_CLIENTE"),
                        rs.getInt("ANO"),
                        rs.getDouble("PRECO"),
                        rs.getString("MARCA"),
                        rs.getString("MODELO")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Carro> listAll() {
        String select = "SELECT * FROM CARRO";
        List<Carro> carros = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(select)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Carro carro = new Carro(
                        rs.getInt("ID_CARRO"),
                        rs.getInt("ID_CLIENTE"),
                        rs.getInt("ANO"),
                        rs.getDouble("PRECO"),
                        rs.getString("MARCA"),
                        rs.getString("MODELO")
                );
                carros.add(carro);
            }

            System.out.println("Lista de carros carregada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carros;
    }
}
