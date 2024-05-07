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
import models.Cliente;

/**
 *
 * @author joaov
 */
public class ClienteRepository {
    
    private static final String INSERT = 
            "INSERT INTO CLIENTE(ID, NOME, CPF) VALUES(?, ?, ?)";
    
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
    
}
