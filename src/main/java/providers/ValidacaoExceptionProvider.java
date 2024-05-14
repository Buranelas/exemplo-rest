/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package providers;

import com.buranello.exemplo.rest.controllers.ClienteController;
import dto.ExceptionResponse;
import exceptions.ValidacaoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author joaov
 */
@Provider
public class ValidacaoExceptionProvider implements ExceptionMapper<ValidacaoException>{
    
    private static final Logger LOGGER = 
            Logger.getLogger(ClienteController.class.getName());
    
    @Context
    private HttpServletRequest request;
    
    @Override
    public Response toResponse(ValidacaoException e){
         ExceptionResponse response = 
                    new ExceptionResponse(e.getMessage(), 
                            new Date(), 
                            request.getRequestURI(), 
                            Response.Status.BAD_REQUEST.toString());
            return Response.
                    status(Response.Status.BAD_REQUEST).
                    entity(response).build();
    }
    
}
