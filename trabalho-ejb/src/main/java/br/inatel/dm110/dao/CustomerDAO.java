package br.inatel.dm110.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.inatel.dm110.entity.Customer;

@Stateless
public class CustomerDAO {
	
	@PersistenceContext(unitName = "customer")
	private EntityManager em;

	public void insertCustomer(Customer customer) {
		em.persist(customer);
	}
	
	public Customer getCustomerById(String id) {
		return em.createQuery("from Customer c where id = '" + id + "'", Customer.class).getSingleResult();
	}

	public List<Customer> listCustomers() {
		return em.createQuery("from Customer c", Customer.class).getResultList();
	}
	
	public void updateCustomer(Customer customer) {
		em.merge(customer);
	}
	

}
