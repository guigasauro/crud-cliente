package br.com.mercado.dao;

import br.com.mercado.factory.ConnectionFactory;
import br.com.mercado.model.Cidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {
    public static void imprimirCidade(List<Cidade> cidades) {
        System.out.println("---- Cidades ----");
        for (Cidade cidade : cidades) {
            System.out.println("ID:   " + cidade.getIdCidade());
            System.out.println("Nome: " + cidade.getNome());
            System.out.println("---------------------------");
        }
    }

    public static List<Cidade> getAllCidade() {
        List<Cidade> cidades = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL
            String sql = "SELECT * FROM Cidade";
            pstmt = conn.prepareStatement(sql);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos Cidade
            while (rs.next()) {
                int idCidade = rs.getInt("idCidade");
                String nome = rs.getString("nome");

                Cidade cidade = new Cidade(idCidade, nome);
                cidades.add(cidade);
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

        return cidades;
    }

}
