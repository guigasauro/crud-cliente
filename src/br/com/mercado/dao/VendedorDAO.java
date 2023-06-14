package br.com.mercado.dao;

import br.com.mercado.factory.ConnectionFactory;
import br.com.mercado.model.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {
    public static void imprimirVendedores(List<Vendedor> vendedores, String titulo) {
        System.out.println("---- " + titulo + " ----");
        for (Vendedor vendedor : vendedores) {
            System.out.println("ID:   " + vendedor.getId());
            System.out.println("Nome: " + vendedor.getNome());
            System.out.println("---------------------------");
        }
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
}
