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
        	CustomerTO customerTO = new CustomerTO();
        	customerTO.setId(customer.getId());
        	customerTO.setName(customer.getName());
        	customerTO.setEmail(customer.getEmail());
        	customerTO.setBirthDate(customer.getBirthDate());
        	customerTO.setCpf(customer.getCpf());
        	customerTO.setCep(customer.getCep());
        	customerTO.setGender(customer.getGender());

        	listCustomersTO.add(customerTO);
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

}