package br.inatel.dm110.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import br.inatel.dm110.entity.Customer;

@Stateless
public class CustomerDAO {
	
	@PersistenceContext(unitName = "trabalho_dm110_pu")
	private EntityManager em;

	public void insertCustomer(Customer customer) {
		em.persist(customer);
	}
	
	public Customer getCustomerById(Integer id) {
		return em.createQuery("from Customer c where id = '" + id + "'", Customer.class).getSingleResult();
	}

	public List<Customer> listCustomers() {
		return em.createQuery("from Customer c", Customer.class).getResultList();
	}
	
	public Customer updateCustomer(Customer customer) {
		return em.merge(customer);		
	}
	

}
