package br.com.mercado.dao;

import br.com.mercado.factory.ConnectionFactory;
import br.com.mercado.model.Venda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    public static void imprimirVendas(List<Venda> vendas, String titulo){
        System.out.println("---- " + titulo + " ----");
        for(Venda venda : vendas){
            System.out.println("[" + venda.getId() + "]" + " - " + venda.getIdCliente() + " - " + venda.getIdVendedor() + " - " + venda.getIdFormaPagamento() + " - " + venda.getDataVenda() + " - " + venda.getValorFinal() + " - " + venda.getPorcDesconto() + " - " + venda.getStatusPago());
            System.out.println("---------------------------");
        }
    }

    public static int save(Venda venda){
        String sql = "INSERT INTO venda (idCliente, idVendedor, idFormaPagamento, dataVenda, valorFinal, porcDesconto, statusPago) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        int generatedId = 0;

        try{
            conn = ConnectionFactory.createConectionToMySQL();

            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, venda.getIdCliente());
            pstmt.setInt(2, venda.getIdVendedor());
            pstmt.setInt(3, venda.getIdFormaPagamento());
            pstmt.setDate(4, venda.getDataVenda());
            pstmt.setDouble(5, venda.getValorFinal());
            pstmt.setDouble(6, venda.getPorcDesconto());
            pstmt.setBoolean(7, venda.getStatusPago());
            

            pstmt.executeUpdate();

            generatedKeys = pstmt.getGeneratedKeys();
            if(generatedKeys.next()){
                generatedId = generatedKeys.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            throw new RuntimeException(e);
        } finally {
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

    public static List<Venda> getForCliente(int idCliente){
        String sql = "SELECT * FROM venda WHERE idCliente = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Venda> vendas = new ArrayList<>();

        try{
            conn = ConnectionFactory.createConectionToMySQL();

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, idCliente);

            rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("idVenda");
                int idVendedor = rs.getInt("idVendedor");
                int idFormaPagamento = rs.getInt("idFormaPagamento");
                Date dataVenda = rs.getDate("dataVenda");
                double valorFinal = rs.getDouble("valorFinal");
                double porcDesconto = rs.getDouble("porcDesconto");
                boolean statusPago = rs.getBoolean("statusPago");

                Venda venda = new Venda(id, idCliente, idVendedor, idFormaPagamento, dataVenda, valorFinal, porcDesconto, statusPago);
                vendas.add(venda);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            throw new RuntimeException(e);
        } finally {
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
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return vendas;
    }
}
