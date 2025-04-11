package br.com.horizon.dao;

import br.com.horizon.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
    private Connection conn;

    public ClienteDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (ID_CLIENTE, NAME, CPF, PHONE, EMAIL, ADDRESS) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cliente.getId());
            ps.setString(2, cliente.getName());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getPhone());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cliente cliente) {
        String sql = "UPDATE CLIENTE SET NAME=?, PHONE=?, EMAIL=?, ADDRESS=? WHERE ID_CLIENTE=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getName());
            ps.setString(2, cliente.getPhone());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getAddress());
            ps.setInt(5, cliente.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente searchById(int id) {
        String sql = "SELECT * FROM CLIENTE WHERE ID_CLIENTE=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("ID_CLIENTE"),
                        rs.getString("NAME"),
                        rs.getString("CPF"),
                        rs.getString("PHONE"),
                        rs.getString("EMAIL"),
                        rs.getString("ADDRESS")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cliente> listAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTE";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("ID_CLIENTE"),
                        rs.getString("NAME"),
                        rs.getString("CPF"),
                        rs.getString("PHONE"),
                        rs.getString("EMAIL"),
                        rs.getString("ADDRESS")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
