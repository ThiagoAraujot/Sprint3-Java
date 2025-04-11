package br.com.horizon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.horizon.entities.Mecanico;

public class MecanicoDAOImpl implements MecanicoDAO {

    private Connection conn;

    public MecanicoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Mecanico mecanico) {
        String insert = "INSERT INTO MECANICO (ID_MECANICO, ID_OFICINA, NAME, CPF, AGE) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            ps.setInt(1, mecanico.getId());
            ps.setInt(2, mecanico.getIdOficina());
            ps.setString(3, mecanico.getName());
            ps.setString(4, mecanico.getCpf());
            ps.setInt(5, mecanico.getAge());

            ps.executeUpdate();
            System.out.println("Mecânico inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Mecanico mecanico) {
        String update = "UPDATE MECANICO SET ID_OFICINA=?, NAME=?, CPF=?, AGE=? WHERE ID_MECANICO=?";

        try (PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setInt(1, mecanico.getIdOficina());
            ps.setString(2, mecanico.getName());
            ps.setString(3, mecanico.getCpf());
            ps.setInt(4, mecanico.getAge());
            ps.setInt(5, mecanico.getId());

            ps.executeUpdate();
            System.out.println("Mecânico atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM MECANICO WHERE ID_MECANICO=?";

        try (PreparedStatement ps = conn.prepareStatement(delete)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Mecânico removido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Mecanico searchById(int id) {
        String select = "SELECT * FROM MECANICO WHERE ID_MECANICO=?";

        try (PreparedStatement ps = conn.prepareStatement(select)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Mecanico(
                        rs.getInt("ID_MECANICO"),
                        rs.getInt("ID_OFICINA"),
                        rs.getString("NAME"),
                        rs.getString("CPF"),
                        rs.getInt("AGE")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Mecanico> listAll() {
        List<Mecanico> mecanicos = new ArrayList<>();
        String select = "SELECT * FROM MECANICO";

        try (PreparedStatement ps = conn.prepareStatement(select)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mecanico mecanico = new Mecanico(
                        rs.getInt("ID_MECANICO"),
                        rs.getInt("ID_OFICINA"),
                        rs.getString("NAME"),
                        rs.getString("CPF"),
                        rs.getInt("AGE")
                );
                mecanicos.add(mecanico);
            }

            System.out.println("Lista de mecânicos carregada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mecanicos;
    }
}
