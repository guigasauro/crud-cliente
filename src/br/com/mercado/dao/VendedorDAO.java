package br.com.mercado.dao;

import br.com.mercado.factory.ConnectionFactory;
import br.com.mercado.model.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {
    public static void imprimirVendedores(List<Vendedor> vendedores, String titulo) {
        if (vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor encontrado!\n");
            return;
        }

        System.out.println("---- " + titulo + " ----");
        for (Vendedor vendedor : vendedores) {
            System.out.println("ID:   " + vendedor.getId());
            System.out.println("Nome: " + vendedor.getNome());
            System.out.println("---------------------------");
        }
        System.out.println();
    }

    public static List<Vendedor> getAllVendedor(){
        List<Vendedor> vendedores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.createConectionToMySQL();

            String sql = "SELECT * FROM vendedor";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("idVendedor");
                String nome = rs.getString("nome");

                Vendedor vendedor = new Vendedor(id, nome);
                vendedores.add(vendedor);
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

        return vendedores;
    }

    public static Vendedor getVendedorById(int id){
        Vendedor vendedor = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.createConectionToMySQL();

            String sql = "SELECT * FROM vendedor WHERE idVendedor = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            while(rs.next()){
                String nome = rs.getString("nome");

                vendedor = new Vendedor(id, nome);
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

        return vendedor;
    }

    public static Vendedor getVendedorByNome(String nome){
        Vendedor vendedor = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.createConectionToMySQL();

            String sql = "SELECT * FROM vendedor WHERE nome = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);

            rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("idVendedor");

                vendedor = new Vendedor(id, nome);
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

        return vendedor;
    }

    public static void relatorio(int mes, int idVendedor) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = ConnectionFactory.createConectionToMySQL();

            String sql = "CALL GenerateSalesReport(?, ?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, mes);
            pstmt.setInt(2, idVendedor);
            System.out.println();

            rs = pstmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Nenhum relatório encontrado!\n");
                return;
            }

            System.out.println("---- Relatório de Vendas ----");
            do {
                System.out.println("Cliente: " + rs.getString("nome_cliente"));
                System.out.println("Total de Vendas: " + rs.getString("total_vendas"));
                System.out.println("Total de Produtos: " + rs.getString("total_produtos"));
                System.out.println("Total em Gasto: " + rs.getString("total_pago"));
                System.out.println("---------------------------");
            } while (rs.next());
            System.out.println();

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
    }
}
