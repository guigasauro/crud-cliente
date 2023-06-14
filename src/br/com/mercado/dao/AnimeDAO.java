package br.com.mercado.dao;

import br.com.mercado.factory.ConnectionFactory;
import br.com.mercado.model.Anime;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AnimeDAO {

    public static void imprimirAnimes(List<Anime> animes) {
        System.out.println("---- Animes ----");
        for (Anime anime : animes) {
            System.out.println("ID:   " + anime.getIdAnime());
            System.out.println("Nome: " + anime.getNome());
            System.out.println("----------------");
        }
    }

    public static List<Anime> getAllAnime() {
        List<Anime> animes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL
            String sql = "SELECT * FROM anime";
            pstmt = conn.prepareStatement(sql);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos Anime
            while (rs.next()) {
                int idAnime = rs.getInt("idAnime");
                String nome = rs.getString("nome");

                Anime anime = new Anime(idAnime, nome);
                animes.add(anime);
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

        return animes;
    }


}
