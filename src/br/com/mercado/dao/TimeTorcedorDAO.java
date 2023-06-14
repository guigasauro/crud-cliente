package br.com.mercado.dao;

import br.com.mercado.factory.ConnectionFactory;
import br.com.mercado.model.TimeTorcedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeTorcedorDAO {
    public static void imprimirTimeTorcedor(List<TimeTorcedor> timeTorcedores) {
        if (timeTorcedores.isEmpty()) {
            System.out.println("Nenhum time encontrado!\n");
            return;
        }
        System.out.println("---- Times ----");
        for (br.com.mercado.model.TimeTorcedor timeTorcedor : timeTorcedores) {
            System.out.println("ID:   " + timeTorcedor.getIdTimeTorcedor());
            System.out.println("Nome: " + timeTorcedor.getNome());
            System.out.println("---------------------------");
        }
        System.out.println();
    }

    public static List<TimeTorcedor> getAllTimeTorcedor() {
        List<TimeTorcedor> timeTorcedores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL
            String sql = "SELECT * FROM timeTorcedor";
            pstmt = conn.prepareStatement(sql);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos TimeTorcedor
            while (rs.next()) {
                int idTimeTorcedor = rs.getInt("idTimeTorcedor");
                String nome = rs.getString("nome");

                br.com.mercado.model.TimeTorcedor timeTorcedor = new br.com.mercado.model.TimeTorcedor(idTimeTorcedor, nome);
                timeTorcedores.add(timeTorcedor);
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

        return timeTorcedores;
    }
}
