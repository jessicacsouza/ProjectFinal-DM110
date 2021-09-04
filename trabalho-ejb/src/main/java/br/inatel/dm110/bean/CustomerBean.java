package br.inatel.dm110.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.inatel.dm110.api.CustomerTO;
import br.inatel.dm110.dao.CustomerDAO;
import br.inatel.dm110.entity.Customer;
import br.inatel.dm110.interfaces.LocalRegistry;
import br.inatel.dm110.interfaces.RemoteRegistration;

@Stateless
@Remote (RemoteRegistration.class)
@Local (LocalRegistry.class)
public class CustomerBean implements RemoteRegistration, LocalRegistry
{

    @EJB
    private CustomerDAO customerDAO;

    @Override
    public List<CustomerTO> listCustomers() {

        List<CustomerTO> listCustomersTO = new ArrayList<>();

        List<Customer> listCustomersEntity = customerDAO.listCustomers();
        for (Customer customer : listCustomersEntity)
        {

        	listCustomersTO.add(createNewCustomerTO(customer));
        }

        return listCustomersTO;

    }

	@Override
    public CustomerTO insertCustomer(CustomerTO customerTO)
    {
        Customer customer = new Customer(
        		customerTO.getName(), 
        		customerTO.getBirthDate(), 
        		customerTO.getGender(), 
        		customerTO.getEmail(),
        		customerTO.getCep(),
        		customerTO.getCpf()
        		);
        
        customerDAO.insertCustomer(customer);
        
        customerTO.setId(customer.getId());

        return customerTO;
    }
    
    @Override
	public CustomerTO getCustomerById(Integer id) {
		Customer customer = customerDAO.getCustomerById(id);
    	
		return createNewCustomerTO(customer);
	}
    
	@Override
	public CustomerTO updateCustomer(Integer id, CustomerTO customerTO) {
		Customer customer = customerDAO.getCustomerById(id);
    	customer.setId(customerTO.getId());
    	customer.setName(customerTO.getName());
    	customer.setEmail(customerTO.getEmail());
    	customer.setBirthDate(customerTO.getBirthDate());
    	customer.setCpf(customerTO.getCpf());
    	customer.setCep(customerTO.getCep());
    	customer.setGender(customerTO.getGender());
		
		return createNewCustomerTO(customerDAO.updateCustomer(customer));
		
	}

    private CustomerTO createNewCustomerTO(Customer customer) {
    	if (customer != null) {
	    	CustomerTO customerTO = new CustomerTO();
	    	customerTO.setId(customer.getId());
	    	customerTO.setName(customer.getName());
	    	customerTO.setEmail(customer.getEmail());
	    	customerTO.setBirthDate(customer.getBirthDate());
	    	customerTO.setCpf(customer.getCpf());
	    	customerTO.setCep(customer.getCep());
	    	customerTO.setGender(customer.getGender());
	    	return customerTO;
	    }
    	
    	return null;
    	
	}

}