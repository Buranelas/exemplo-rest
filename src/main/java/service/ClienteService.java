/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import exceptions.ValidacaoException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import models.Cliente;
import repositories.ClienteRepository;

/**
 *
 * @author joaov
 */
public class ClienteService {
   
    public Cliente insert(Cliente cliente) throws 
            ValidacaoException, SQLException, NamingException{
        
        if(cliente.getCpf().isEmpty()){
            throw new ValidacaoException("CPF não pode ser nulo");
        }
        if(cliente.getCpf().trim().length() != 11){
            throw new ValidacaoException("Valor de CPF inválido");
        }
        
        if(cliente.getNome().trim().isEmpty()){
            throw new ValidacaoException("Nome não pode ser nulo");
        }
        
        //Chamar o repositorio;
        
        return new ClienteRepository().insert(cliente);
        
    }
    
    public Cliente update(Cliente cliente) throws 
            ValidacaoException, SQLException, NamingException{
        
        if(cliente.getCpf().isEmpty()){
            throw new ValidacaoException("CPF não pode ser nulo");
        }
        if(cliente.getCpf().trim().length() != 11){
            throw new ValidacaoException("Valor de CPF inválido");
        }
        
        if(cliente.getNome().trim().isEmpty()){
            throw new ValidacaoException("Nome não pode ser nulo");
        }
        
        
        return new ClienteRepository().update(cliente);
        
    }
    
    public void delete(int id) throws 
            ValidacaoException, SQLException, NamingException{
        
        if(id == 0)
            throw new ValidacaoException("Informe o Cliente a ser Deletado");
        
        new ClienteRepository().delete(id);
        
    }
    
    public List<Cliente> findAll() throws 
            ValidacaoException, SQLException, NamingException{
        
        return new ClienteRepository().findAll();
        
    }
    
    public Cliente findById(int id) throws
            ValidacaoException, SQLException, NamingException{
        
        return new ClienteRepository().findById(id);
    }
}
