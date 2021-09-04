package br.inatel.dm110.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import br.inatel.dm110.api.CustomerService;
import br.inatel.dm110.api.CustomerTO;
import br.inatel.dm110.interfaces.LocalRegistry;

public class CustomerServiceImpl implements CustomerService {
	
	@EJB
	private LocalRegistry localRegistryBean;

	@Override
	public List<CustomerTO> listCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getCustomerById(Integer id) {
		CustomerTO customerTO = localRegistryBean.getCustomerById(id);
		
		if (customerTO != null) {
			return Response.ok(customerTO).build();
		}
		
		return Response.noContent().build();
	}

	@Override
	public CustomerTO insertCustomer(CustomerTO cliente) {
		// TODO Auto-generated method stub
		return null;
	}

}
