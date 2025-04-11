package br.com.horizon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.horizon.entities.Oficina;

public class OficinaDAOImpl implements OficinaDAO {

    private Connection conn;

    public OficinaDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Oficina oficina) {
        String insert = "INSERT INTO OFICINA (ID_OFICINA, NAME, ADDRESS, DESCRIPTION) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setInt(1, oficina.getId());
            ps.setString(2, oficina.getName());
            ps.setString(3, oficina.getAddress());
            ps.setString(4, oficina.getDescription());

            ps.executeUpdate();
            System.out.println("Oficina inserida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Oficina oficina) {
        String update = "UPDATE OFICINA SET NAME=?, ADDRESS=?, DESCRIPTION=? WHERE ID_OFICINA=?";

        try (PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setString(1, oficina.getName());
            ps.setString(2, oficina.getAddress());
            ps.setString(3, oficina.getDescription());
            ps.setInt(4, oficina.getId());

            ps.executeUpdate();
            System.out.println("Oficina atualizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM OFICINA WHERE ID_OFICINA=?";

        try (PreparedStatement ps = conn.prepareStatement(delete)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Oficina removida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Oficina searchById(int id) {
        String select = "SELECT * FROM OFICINA WHERE ID_OFICINA=?";

        try (PreparedStatement ps = conn.prepareStatement(select)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Oficina(
                        rs.getInt("ID_OFICINA"),
                        rs.getString("NAME"),
                        rs.getString("ADDRESS"),
                        rs.getString("DESCRIPTION")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Oficina> listAll() {
        List<Oficina> oficinas = new ArrayList<>();
        String select = "SELECT * FROM OFICINA";

        try (PreparedStatement ps = conn.prepareStatement(select)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Oficina oficina = new Oficina(
                        rs.getInt("ID_OFICINA"),
                        rs.getString("NAME"),
                        rs.getString("ADDRESS"),
                        rs.getString("DESCRIPTION")
                );
                oficinas.add(oficina);
            }

            System.out.println("Lista de oficinas carregada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oficinas;
    }
}
