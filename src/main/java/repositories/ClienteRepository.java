/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import infrestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Cliente;

/**
 *
 * @author joaov
 */
public class ClienteRepository {
    
    private static final String INSERT = 
            "INSERT INTO CLIENTE(ID, NOME, CPF) VALUES(?, ?, ?)";
    
    private static final String UPDATE =
            "UPDATE CLIENTE SET NOME = ? AND CPF ? WHERE ID = ?";
    
    private static final String DELETE =
            "DELETE CLIENTE WHERE ID = ?";
    
    private static final String FIND_ALL =
            "SELECT * FROM CLIENTE";
    
    private static final String FIND_BY_ID =
            "SELECT * FROM CLIENTE WHERE ID = ?";
    
    
    public Cliente insert(Cliente cliente) throws SQLException{
        
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            connection = new ConnectionFactory().getConnection();
            
            pstmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(1, cliente.getCpf());
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            
            rs.next();
            cliente.setId(rs.getInt(1));
        } finally{
            
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (connection != null)
                connection.close();
        }
        return cliente;
        
    }
    
    public Cliente update(Cliente cliente) throws SQLException{
        
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try{
            connection = new ConnectionFactory().getConnection();
            
            pstmt = connection.prepareStatement(UPDATE);
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setInt(3, cliente.getId());
            pstmt.executeUpdate();
            
        } finally{
            
            if (pstmt != null)
                pstmt.close();
            if (connection != null)
                connection.close(); 
        }
        return cliente;
    }
    
    public void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            connection = new ConnectionFactory().getConnection();
            pstmt = connection.prepareStatement(DELETE);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();
            if (connection != null)
                connection.close();
        }
    }
    
     public List<Cliente> findAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            connection = new ConnectionFactory().getConnection();
            pstmt = connection.prepareStatement(FIND_ALL);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                clientes.add(cliente);
            }
        } finally {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (connection != null)
                connection.close();
        }
        
        return clientes;
    }
     
      public Cliente findById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            connection = new ConnectionFactory().getConnection();
            pstmt = connection.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                return cliente;
            }
        } finally {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (connection != null)
                connection.close();
        }
        
        return null;
    }
    
}
