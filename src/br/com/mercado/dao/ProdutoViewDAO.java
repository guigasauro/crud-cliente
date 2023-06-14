package br.com.mercado.dao;

import br.com.mercado.factory.ConnectionFactory;
import br.com.mercado.model.ProdutoView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoViewDAO {
    public static void imprimirProdutos(List<ProdutoView> produtos, String titulo) {
        if (produtos.size() == 0) {
            System.out.println("Nenhum produto encontrado!");
            return;
        }
        System.out.println("---- "+ titulo + " ----");
        for (ProdutoView produto : produtos) {
            System.out.println("ID:           " + produto.getIdProduto());
            System.out.println("Nome:         " + produto.getNome());
            System.out.println("Preço:        " + produto.getPreco());
            System.out.println("Categoria:    " + produto.getNomeCategoria());
            System.out.println("Fabricado em: " + produto.getEnderecoFabricante());
            System.out.println("Quantidade:   " + produto.getQuantidade());
            System.out.println("---------------------------");
        }
    }

    public static List<ProdutoView> getAllProdutoView() {
        List<ProdutoView> produtoViews = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL
            String sql = "SELECT * FROM produtoView";
            pstmt = conn.prepareStatement(sql);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos ProdutoView
            while (rs.next()) {
                int idProduto = rs.getInt("idProduto");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String nomeCategoria = rs.getString("nomeCategoria");
                String nomeFabricante = rs.getString("nomeFabricante");
                String enderecoFabricante = rs.getString("enderecoFabricante");
                int quantidade = rs.getInt("quantidade");

                ProdutoView produtoView = new ProdutoView(idProduto, nome, preco, nomeCategoria, nomeFabricante, enderecoFabricante, quantidade);
                produtoViews.add(produtoView);
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

        return produtoViews;
    }

    public static ProdutoView getForIdProdutoView(int id){
        ProdutoView produtoView = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL com a cláusula WHERE para filtrar pelo id
            String sql = "SELECT * FROM produtoView WHERE idProduto = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos ProdutoView
            while (rs.next()) {
                int idProduto = rs.getInt("idProduto");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String nomeCategoria = rs.getString("nomeCategoria");
                String nomeFabricante = rs.getString("nomeFabricante");
                String enderecoFabricante = rs.getString("enderecoFabricante");
                int quantidade = rs.getInt("quantidade");

                produtoView = new ProdutoView(idProduto, nome, preco, nomeCategoria, nomeFabricante, enderecoFabricante, quantidade);
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

        return produtoView;
    }

    public static List<ProdutoView> getForNameProdutoView(String nome) {
        List<ProdutoView> produtoViews = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL com a cláusula WHERE para filtrar pelo nome
            String sql = "SELECT * FROM produtoView WHERE nome = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos ProdutoView
            while (rs.next()) {
                int idProduto = rs.getInt("idProduto");
                String nomeProduto = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String nomeCategoria = rs.getString("nomeCategoria");
                String nomeFabricante = rs.getString("nomeFabricante");
                String enderecoFabricante = rs.getString("enderecoFabricante");
                int quantidade = rs.getInt("quantidade");

                ProdutoView produtoView = new ProdutoView(idProduto, nomeProduto, preco, nomeCategoria, nomeFabricante, enderecoFabricante, quantidade);
                produtoViews.add(produtoView);
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

        return produtoViews;
    }

    public static List<ProdutoView> getForPriceProdutoViewBy(double minPrice, double maxPrice) {
        List<ProdutoView> produtoViews = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL com a cláusula WHERE e a cláusula BETWEEN para filtrar pelo preço
            String sql = "SELECT * FROM produtoView WHERE preco BETWEEN ? AND ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, minPrice);
            pstmt.setDouble(2, maxPrice);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos ProdutoView
            while (rs.next()) {
                int idProduto = rs.getInt("idProduto");
                String nomeProduto = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String nomeCategoria = rs.getString("nomeCategoria");
                String nomeFabricante = rs.getString("nomeFabricante");
                String enderecoFabricante = rs.getString("enderecoFabricante");
                int quantidade = rs.getInt("quantidade");

                ProdutoView produtoView = new ProdutoView(idProduto, nomeProduto, preco, nomeCategoria, nomeFabricante, enderecoFabricante, quantidade);
                produtoViews.add(produtoView);
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

        return produtoViews;
    }

    public static List<ProdutoView> getForCategoryProdutoView(String nome) {
        List<ProdutoView> produtoViews = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL com a cláusula WHERE para filtrar pelo nome
            String sql = "SELECT * FROM produtoView WHERE nomeCategoria = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos ProdutoView
            while (rs.next()) {
                int idProduto = rs.getInt("idProduto");
                String nomeProduto = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String nomeCategoria = rs.getString("nomeCategoria");
                String nomeFabricante = rs.getString("nomeFabricante");
                String enderecoFabricante = rs.getString("enderecoFabricante");
                int quantidade = rs.getInt("quantidade");

                ProdutoView produtoView = new ProdutoView(idProduto, nomeProduto, preco, nomeCategoria, nomeFabricante, enderecoFabricante, quantidade);
                produtoViews.add(produtoView);
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

        return produtoViews;
    }

    public static List<ProdutoView> getForCityProdutoView(String nome) {
        List<ProdutoView> produtoViews = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL com a cláusula WHERE para filtrar pelo nome
            String sql = "SELECT * FROM produtoView WHERE enderecoFabricante = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos ProdutoView
            while (rs.next()) {
                int idProduto = rs.getInt("idProduto");
                String nomeProduto = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String nomeCategoria = rs.getString("nomeCategoria");
                String nomeFabricante = rs.getString("nomeFabricante");
                String enderecoFabricante = rs.getString("enderecoFabricante");
                int quantidade = rs.getInt("quantidade");

                ProdutoView produtoView = new ProdutoView(idProduto, nomeProduto, preco, nomeCategoria, nomeFabricante, enderecoFabricante, quantidade);
                produtoViews.add(produtoView);
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

        return produtoViews;
    }

    public static List<ProdutoView> getForAmountProdutoView(int num) {
        List<ProdutoView> produtoViews = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL com a cláusula WHERE para filtrar pelo nome
            String sql = "SELECT * FROM produtoView WHERE quantidade <= ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Iterar pelos resultados e criar os objetos ProdutoView
            while (rs.next()) {
                int idProduto = rs.getInt("idProduto");
                String nomeProduto = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String nomeCategoria = rs.getString("nomeCategoria");
                String nomeFabricante = rs.getString("nomeFabricante");
                String enderecoFabricante = rs.getString("enderecoFabricante");
                int quantidade = rs.getInt("quantidade");

                ProdutoView produtoView = new ProdutoView(idProduto, nomeProduto, preco, nomeCategoria, nomeFabricante, enderecoFabricante, quantidade);
                produtoViews.add(produtoView);
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

        return produtoViews;
    }


    /**
     *
     SELECT pv.idProduto, pv.nome, pv.preco, pv.nomeCategoria, pv.nomeFabricante
     FROM produtoView pv
     JOIN estoque e ON pv.idProduto = e.idProduto
     WHERE e.quantidade > 4;
     *
    public static List<ProdutoView> buscarProdutosPorPreco(String url, String username, String password,
                                                       double precoMinimo, double precoMaximo) {
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT p.id_produto, p.nome, p.preco, c.nome AS nome_categoria, f.nome AS nome_fabricante " +
                "FROM produto p " +
                "JOIN categoria c ON p.id_categoria = c.id_categoria " +
                "JOIN fabricante f ON p.id_fabricante = f.id_fabricante " +
                "WHERE p.preco BETWEEN ? AND ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, precoMinimo);
            statement.setDouble(2, precoMaximo);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idProduto = resultSet.getInt("id_produto");
                String nomeProduto = resultSet.getString("nome");
                double precoProduto = resultSet.getDouble("preco");
                String nomeCategoria = resultSet.getString("nome_categoria");
                String nomeFabricante = resultSet.getString("nome_fabricante");

                ProdutoView produto = new ProdutoView(idProduto, nomeProduto, precoProduto, nomeCategoria, nomeFabricante);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }
     */

}
