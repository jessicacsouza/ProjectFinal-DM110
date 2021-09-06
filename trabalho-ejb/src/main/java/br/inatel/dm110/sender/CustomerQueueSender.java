package br.inatel.dm110.sender;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import br.inatel.dm110.entity.Customer;

@Stateless
public class CustomerQueueSender {

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(lookup = "java:/jms/queue/dm110queue_project")
	private Queue queue;
	
	private static Logger log = Logger.getLogger(CustomerQueueSender.class.getName());
	
	private void sendMessage(String text) {
		try {
			Connection conn = connectionFactory.createConnection();
			Session session = conn.createSession();
			MessageProducer producer = session.createProducer(queue);
			
			TextMessage  textMessage = session.createTextMessage(text);
			producer.send(textMessage);
			
			log.info("Message sent: " + textMessage);			
		} catch(JMSException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void sendCustomerCreatedMessage(Customer customer) {
		//TODO Create message with operation type 'create'
		String text = "";
		sendMessage(text);
	}
	
	public void sendCustomerUpdatedMessage(Customer customer) {
		//TODO Create message with operation type 'update'
		String text = "";
		sendMessage(text);
	}
}
