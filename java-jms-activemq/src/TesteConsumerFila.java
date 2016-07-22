import java.util.Enumeration;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteConsumerFila {

	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
		Destination fila = (Destination) context.lookup("financeiro");
		MessageConsumer consumer = session.createConsumer(fila);
		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message texto) {
				TextMessage message = (TextMessage)texto;
				try {
					System.out.println(message.getText());
					session.rollback();
//					message.acknowledge();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

//		QueueBrowser browser = session.createBrowser((Queue) fila);
//		Enumeration msgs = browser.getEnumeration();
//		while (msgs.hasMoreElements()) { 
//		Enumeration msgs = consumer.getgetEnumeration();
		
			
//		    TextMessage msg = (TextMessage) msgs.nextElement();
//		    System.out.println("Message: " + msg.getText()); 
//		    msg.acknowledge();
//		}
//		String text = browser.getMessageSelector();
//		System.out.println(text);
//		Message message = consumer.receive();
//		consumer.setMessageListener(new MessageListener() {
//
//			@Override
//			public void onMessage(Message texto) {
//				TextMessage message = (TextMessage)texto;
//				try {
//					System.out.println(message.getText());
//				} catch (JMSException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//
//		});
		// System.out.println("Recebendo msg: "+ message);

		new Scanner(System.in).nextLine(); // parar o programa para testar a
											// conexao

		connection.close();
		context.close();
	}

}
