package br.com.mercado.dao;

import br.com.mercado.factory.ConnectionFactory;
import br.com.mercado.model.ItemVenda;
import br.com.mercado.model.ProdutoView;
import br.com.mercado.dao.ProdutoViewDAO;

import java.sql.*;
import java.util.List;

public class ItemVendaDAO {
    public static List<ItemVenda> getAllItemVenda(){
        List<ItemVenda> itensVenda = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.createConectionToMySQL();

            String sql = "SELECT * FROM itemVenda";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("idItemVenda");
                int idProduto = rs.getInt("idProduto");
                int idVenda = rs.getInt("idVenda");
                int quantidade = rs.getInt("quantidade");
                double preco = rs.getDouble("preco");

                ItemVenda itemVenda = new ItemVenda(id, idProduto, idVenda, quantidade, preco);
                itensVenda.add(itemVenda);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return itensVenda;
    }

    public static ItemVenda getForIdItemVenda(int id){
        ItemVenda itemVenda = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.createConectionToMySQL();

            String sql = "SELECT * FROM itemVenda WHERE idItemVenda = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while(rs.next()){
                int idProduto = rs.getInt("idProduto");
                int idVenda = rs.getInt("idVenda");
                int quantidade = rs.getInt("quantidade");
                double preco = rs.getDouble("preco");

                itemVenda = new ItemVenda(id, idProduto, idVenda, quantidade, preco);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return itemVenda;
    }

    public static List<ItemVenda> getForIdVendaItemVenda(int idVenda){
        List<ItemVenda> itensVenda = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.createConectionToMySQL();

            String sql = "SELECT * FROM itemVenda WHERE idVenda = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idVenda);

            rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("idItemVenda");
                int idProduto = rs.getInt("idProduto");
                int quantidade = rs.getInt("quantidade");
                int preco = rs.getInt("preco");

                ItemVenda itemVenda = new ItemVenda(id, idProduto, idVenda, quantidade, preco);
                itensVenda.add(itemVenda);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return itensVenda;
    }

    public static int save(ItemVenda itemVenda, int idVenda){
        String sql = "INSERT INTO itemVenda (idProduto, idVenda, quantidade, preco) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        int generatedId = 0;
        
        try{
            conn = ConnectionFactory.createConectionToMySQL();

            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, itemVenda.getIdProduto());
            pstmt.setInt(2, idVenda);
            pstmt.setInt(3, itemVenda.getQuantidade());
            pstmt.setDouble(4, itemVenda.getPreco());

            pstmt.executeUpdate();

            
            generatedKeys = pstmt.getGeneratedKeys();
            if(generatedKeys.next()){
                generatedId = generatedKeys.getInt(1);
            }
            
            String updateQuery = "UPDATE produto SET qtd = ? WHERE idProduto = ?";
            pstmt = conn.prepareStatement(updateQuery);
                // Retrieve the current qtd value using a SELECT statement
            String selectQuery = "SELECT qtd FROM produto WHERE idProduto = ?";
            try (PreparedStatement selectStatement = conn.prepareStatement(selectQuery)) {
                selectStatement.setInt(1, itemVenda.getIdProduto());
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int currentQtd = resultSet.getInt("qtd");
                        int updatedQtd = currentQtd - itemVenda.getQuantidade();
                        // Update the qtd value in the produto table
                        pstmt.setInt(1, updatedQtd);
                        pstmt.setInt(2, itemVenda.getIdProduto());
                        pstmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            throw new RuntimeException(e);
        } finally{
            try{
                if(generatedKeys != null){
                    generatedKeys.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        
        return generatedId;
    }

    public static int getTotal(List<ItemVenda> carrinho){
        int total = 0;
        for (ItemVenda produto : carrinho) {
            total += produto.getPreco() * produto.getQuantidade();
        }
        return total;
    }

    public static void ImprimeCarrinho(List<ItemVenda> carrinho, String titulo){
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio!\n");
            return;
        }
        
        System.out.println("---- " + titulo + " ----");
        for (ItemVenda produto : carrinho) {
            System.out.println("ID: " + produto.getIdProduto() + " - " + ProdutoViewDAO.getForIdProdutoView(produto.getIdProduto()).getNome() + " - " + produto.getQuantidade() + " - " + produto.getPreco());
        }
        System.out.println();
    }
}
