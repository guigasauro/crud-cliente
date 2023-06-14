package br.com.mercado.dao;

import br.com.mercado.factory.ConnectionFactory;
import br.com.mercado.model.ClienteView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteViewDAO {
    public static void imprimirClientes(List<ClienteView> clientes, String titulo) {
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado!\n");
            return;
        }

        System.out.println("---- " + titulo + " ----");
        for (ClienteView cliente : clientes) {
            System.out.println("ID:              " + cliente.getIdCliente());
            System.out.println("Nome:            " + cliente.getNome());
            System.out.println("Telefone:        " + cliente.getTelefone());
            System.out.println("Anime preferido: " + cliente.getNomeAnime());
            System.out.println("Cidade Natal:    " + cliente.getNomeCidade());
            System.out.println("Time torcedor:   " + cliente.getNomeTimeTorcedor());
            System.out.println("---------------------------");
        }
        System.out.println();
    }

    public static List<ClienteView> getAllClienteView() {
        List<ClienteView> clienteViews = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL
            String sql = "SELECT * FROM clienteView";
            pstmt = conn.prepareStatement(sql);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos ClienteView
            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nome = rs.getString("nome");
                int telefone = rs.getInt("telefone");
                String nomeAnime = rs.getString("nomeAnime");
                String nomeCidade = rs.getString("nomeCidade");
                String nomeTime = rs.getString("nomeTimeTorcedor");

                ClienteView clienteView = new ClienteView(idCliente, nome, telefone, nomeAnime, nomeCidade, nomeTime);
                clienteViews.add(clienteView);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Fechar os recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clienteViews;
    }

    public static List<ClienteView> getClientesById(int id) {
        List<ClienteView> clientes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL
            String sql = "SELECT * FROM clienteView WHERE idCliente = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos ClienteView
            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nome = rs.getString("nome");
                int telefone = rs.getInt("telefone");
                String nomeAnime = rs.getString("nomeAnime");
                String nomeCidade = rs.getString("nomeCidade");
                String nomeTime = rs.getString("nomeTimeTorcedor");

                ClienteView clienteView = new ClienteView(idCliente, nome, telefone, nomeAnime, nomeCidade, nomeTime);
                clientes.add(clienteView);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Fechar os recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clientes;
    }
}
