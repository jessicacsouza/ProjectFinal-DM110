package br.inatel.dm110.interfaces;

import java.util.List;

import br.inatel.dm110.api.CustomerTO;

public interface CustomerRegistration {
	
	 List<CustomerTO> listCustomers();

	 CustomerTO insertCustomer(CustomerTO customerTO);

}
