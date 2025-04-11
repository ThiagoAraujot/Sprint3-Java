package br.com.horizon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.horizon.entities.Fornecedor;

public class FornecedorDAOImpl implements FornecedorDAO {

    private Connection conn;

    public FornecedorDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Fornecedor fornecedor) {
        String sql = "INSERT INTO FORNECEDOR (ID_FORNECEDOR, NAME, DESCRIPTION, CNPJ, PHONE, EMAIL) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fornecedor.getId());
            stmt.setString(2, fornecedor.getName());
            stmt.setString(3, fornecedor.getDescription());
            stmt.setString(4, fornecedor.getCnpj());
            stmt.setString(5, fornecedor.getPhone());
            stmt.setString(6, fornecedor.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Fornecedor fornecedor) {
        String sql = "UPDATE FORNECEDOR SET NAME=?, DESCRIPTION=?, PHONE=?, EMAIL=? WHERE ID_FORNECEDOR=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getName());
            stmt.setString(2, fornecedor.getDescription());
            stmt.setString(3, fornecedor.getPhone());
            stmt.setString(4, fornecedor.getEmail());
            stmt.setInt(5, fornecedor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM FORNECEDOR WHERE ID_FORNECEDOR = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Fornecedor> listAll() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM FORNECEDOR";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fornecedor f = new Fornecedor(
                        rs.getInt("ID_FORNECEDOR"),
                        rs.getString("NAME"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("CNPJ"),
                        rs.getString("PHONE"),
                        rs.getString("EMAIL")
                );
                fornecedores.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fornecedores;
    }

    public Fornecedor searchById(int id) {
        String sql = "SELECT * FROM FORNECEDOR WHERE ID_FORNECEDOR = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Fornecedor(
                        rs.getInt("ID_FORNECEDOR"),
                        rs.getString("NAME"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("CNPJ"),
                        rs.getString("PHONE"),
                        rs.getString("EMAIL")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
