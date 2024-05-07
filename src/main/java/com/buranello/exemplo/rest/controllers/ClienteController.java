package com.buranello.exemplo.rest.controllers;

import dto.ClienteFindAllResponse;
import dto.ClienteRequest;
import exceptions.ValidacaoException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.NamingException;
import models.Cliente;
import service.ClienteService;

/**
 *
 * @author 
 */
@Path("cliente")
@Produces(MediaType.APPLICATION_JSON)
public class ClienteController {
    
//    @GET
//    @Path(value = "ping")
//    public Response ping(){
//        return Response
//                .ok("pong")
//                .build();
//    }
//    
//    @GET
//    @Path("pong")
//    public Response pong(){
//        return Response
//                .ok("ping")
//                .build();
//    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(ClienteRequest cliente) throws 
            ValidacaoException, SQLException, NamingException{
        //Passar pela camada de service
        try{
            ClienteService clienteService = new ClienteService();
        Cliente clienteDomain = 
                clienteService.insert(Cliente.requestToCliente(cliente));
        
        return Response.created(
                URI.create("http://localhost:8080/exemplo-rest/cliente/"+
                        clienteDomain.getId())
        ).build();
        
        }catch(ValidacaoException validacaoException){
            return Response.status(Response.Status.BAD_REQUEST). 
                    entity(validacaoException).build();
        }catch(SQLException | NamingException ex){
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(new ValidacaoException
        ("Algo deu errado, tente novamente mais tarde")).build();
        }
        
    }
    
    @GET
    @Path("{id}")
    public Cliente findById(@PathParam("id") int id){
        
        System.out.println(id);
        return new Cliente(id, "Joao", "12632383943");
        
    }
    
    @GET
    public List<ClienteFindAllResponse> findAll(@QueryParam("nome") @DefaultValue("1234") String nome, 
            @QueryParam("cpf") String cpf){
        ArrayList<ClienteFindAllResponse> listaClientes = new ArrayList<>();
        listaClientes.add (new ClienteFindAllResponse(1, "Joao"));
        listaClientes.add (new ClienteFindAllResponse(2, "Vitor"));
        listaClientes.add (new ClienteFindAllResponse(3, "João V"));
        
        return listaClientes;
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Cliente edit(@PathParam("id") int id, Cliente cliente){
        return cliente;
    }
    
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id){
    }
}
