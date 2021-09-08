package br.inatel.dm110.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path ("/customers")
public interface CustomerService {
	
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    List<CustomerTO> listCustomers();
    
    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getCustomerById(@PathParam("id") Integer id);

    @POST
    @Path ("")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    CustomerTO insertCustomer(CustomerTO customerTO);
    
    @PUT
    @Path ("/{id}")
    @Consumes (MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    CustomerTO updateCustomer(@PathParam("id") Integer id , CustomerTO customerTO);

}
