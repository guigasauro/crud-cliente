package br.com.mercado.dao;

import br.com.mercado.factory.ConnectionFactory;
import br.com.mercado.model.Cliente;

import java.sql.*;

public class ClienteDAO {

    // CREATE
    public static int save(Cliente cliente) {
        String sql = "INSERT INTO cliente(nome, telefone, idAnime, idCidade, idTimeTorcedor) VALUES (?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;
        int generatedId = 0;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Criar um PreparedStatement para executar a query e obter o ID gerado
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Definir os valores para os parâmetros da query
            pstmt.setString(1, cliente.getNome());
            pstmt.setInt(2, cliente.getTelefone());
            pstmt.setInt(3, cliente.getIdAnime());
            pstmt.setInt(4, cliente.getIdCidade());
            pstmt.setInt(5, cliente.getIdTimeTorcedor());

            // Executar a query
            pstmt.executeUpdate();

            // Obter o ID gerado
            generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Fechar os recursos
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
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

        return generatedId;
    }

    public static int getClientId(int value) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int clientId = 0;

        try {
            // Estabelecer conexão com o banco de dados
            conn = ConnectionFactory.createConectionToMySQL();

            // Preparar a consulta SQL
            String sql = "SELECT idCliente FROM cliente WHERE idCliente = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, value);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Verificar se o resultado possui um registro
            if (rs.next()) {
                clientId = rs.getInt("idCliente");
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

        return clientId;
    }

    public static Cliente getClienteById(int id) {
        String sql = "SELECT * FROM cliente WHERE idCliente=?";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Cliente cliente = null;

        try {
            conn = ConnectionFactory.createConectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {
                String nome = rset.getString("nome");
                int telefone = rset.getInt("telefone");
                int idAnime = rset.getInt("idAnime");
                int idCidade = rset.getInt("idCidade");
                int idTimeTorcedor = rset.getInt("idTimeTorcedor");
                boolean possuiDesconto = rset.getBoolean("possuiDesconto");

                cliente = new Cliente(nome, telefone, idAnime, idCidade, idTimeTorcedor, possuiDesconto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }

    /**
    // READ
    public List<Cliente> getCliente(){
        String sql = "SELECT * FROM cliente";

        List<Cliente> clientes = new ArrayList<Cliente>();

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco """SELECT"""
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();
            while (rset.next()) {
                Cliente cliente = new Cliente();
                // recuperar o id
                cliente.setId(rset.getInt("id"));
                // recuperar nome
                cliente.setNome(rset.getString("nome"));
                // recuperar Celular
                cliente.setCelular(rset.getInt("celular"));
                // recuperar data de cadastro
                cliente.setDesconto(rset.getBoolean("desconto"));

                clientes.add(cliente);
            }
            } catch(Exception e){
                e.printStackTrace();
            }finally{
                try {
                    if (rset != null) {
                        rset.close();
                    }
                    if (pstm != null) {
                        pstm.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return clientes;

    }

    // UPDATE
    public void update(Cliente cliente){
        String sql = "UPDATE cliente SET nome = ?, celular = ?, desconto = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar conexão com o banco
            conn = ConnectionFactory.createConectionToMySQL();

            //Criar a classe para eecutar a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Adicionar os valores para atualizar
            pstm.setString(1, cliente.getNome());
            pstm.setInt(2, cliente.getCelular());
            pstm.setBoolean(3, cliente.getDesconto());

            //Qual o ID do registro que deseja atualizar?
            pstm.setInt(4, cliente.getId());

            //Executar a query
            pstm.execute();


        } catch (Exception e){
            e.printStackTrace();

        } finally {
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    // DELETE
    public void deleteByID(int id){

        String sql = "DELETE FROM cliente WHERE id=?";

        Connection conn = null;

        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1,id);

            pstm.execute();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    // -------------------------------------------------------
    // SEACH FOR NAME
    public List<Cliente> getClientePorNome(String nome){

        String sql = "SELECT * FROM cliente WHERE nome=?";

        List<Cliente> clientes = new ArrayList<Cliente>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco """SELECT"""
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConectionToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1,nome);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Cliente cliente = new Cliente();
                // recuperar o id
                cliente.setId(rset.getInt("id"));
                // recuperar nome
                cliente.setNome(rset.getString("nome"));
                // recuperar celular
                cliente.setCelular(rset.getInt("celular"));
                // recuperar data de cadastro
                cliente.setDesconto(rset.getBoolean("desconto"));

                clientes.add(cliente);
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientes;

    }

    // SEACH FOR ID
    public Cliente getClientePorID(int id) {
        String sql = "SELECT * FROM cliente WHERE id=?";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Cliente cliente = null;

        try {
            conn = ConnectionFactory.createConectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rset = pstm.executeQuery();

            if (rset.next()) {
                cliente = new Cliente();
                cliente.setId(rset.getInt("id"));
                cliente.setNome(rset.getString("nome"));
                cliente.setCelular(rset.getInt("celular"));
                cliente.setDesconto(rset.getBoolean("desconto"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }

     */
}
