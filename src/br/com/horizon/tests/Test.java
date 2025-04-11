package br.com.horizon.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.horizon.dao.*;
import br.com.horizon.entities.*;

public class Test {

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "rm553477", "");) {

            // CLIENTE
            ClienteDAO clienteDAO = new ClienteDAOImpl(conn);
            Cliente cliente = new Cliente(1, "João Silva", "12345678900", "11999998888", "joao@email.com", "Rua A, 123");
            clienteDAO.save(cliente);
            cliente.setName("João Pedro Silva");
            clienteDAO.update(cliente);
            Cliente clienteBuscado = clienteDAO.searchById(1);
            System.out.println("Cliente buscado: " + clienteBuscado.getName());
            clienteDAO.delete(1);

            // FORNECEDOR
            FornecedorDAO fornecedorDAO = new FornecedorDAOImpl(conn);
            Fornecedor fornecedor = new Fornecedor(1, "AutoPeças LTDA", "Fornecedor de peças automotivas", "12345678000199", "1133334444", "contato@autopecas.com");
            fornecedorDAO.save(fornecedor);
            fornecedor.setName("AutoPeças Brasil LTDA");
            fornecedorDAO.update(fornecedor);
            Fornecedor fornecedorBuscado = fornecedorDAO.searchById(1);
            System.out.println("Fornecedor buscado: " + fornecedorBuscado.getName());
            fornecedorDAO.delete(1);

            // OFICINA
            OficinaDAO oficinaDAO = new OficinaDAOImpl(conn);
            Oficina oficina = new Oficina(1, "Oficina do Zé", "Rua das Oficinas, 456", "Serviços gerais automotivos");
            oficinaDAO.save(oficina);
            oficina.setDescription("Serviços automotivos e revisões");
            oficinaDAO.update(oficina);
            Oficina oficinaBuscada = oficinaDAO.searchById(1);
            System.out.println("Oficina buscada: " + oficinaBuscada.getName());
            oficinaDAO.delete(1);

            // MECÂNICO
            oficinaDAO.save(new Oficina(2, "Oficina MecMaster", "Av. Industrial, 999", "Especializada em carros importados"));
            MecanicoDAO mecanicoDAO = new MecanicoDAOImpl(conn);
            Mecanico mecanico = new Mecanico(1, 2, "Carlos Almeida", "98765432100", 35);
            mecanicoDAO.save(mecanico);
            mecanico.setAge(36);
            mecanicoDAO.update(mecanico);
            Mecanico mecanicoBuscado = mecanicoDAO.searchById(1);
            System.out.println("Mecânico buscado: " + mecanicoBuscado.getName());
            mecanicoDAO.delete(1);
            oficinaDAO.delete(2);

            // CARRO
            clienteDAO.save(new Cliente(2, "Maria Oliveira", "45612378900", "11987654321", "maria@email.com", "Av. Brasil, 200"));
            CarroDAO carroDAO = new CarroDAOImpl(conn);
            Carro carro = new Carro(1, 2, 2022, 95000.00, "Toyota", "Corolla");
            carroDAO.save(carro);
            carro.setPreco(92000.00);
            carroDAO.update(carro);
            Carro carroBuscado = carroDAO.searchById(1);
            System.out.println("Carro buscado: " + carroBuscado.getModelo() + " - " + carroBuscado.getPreco());
            carroDAO.delete(1);
            clienteDAO.delete(2);

            System.out.println("\nTodos os testes CRUD foram executados com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
