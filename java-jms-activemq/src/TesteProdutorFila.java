import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteProdutorFila {

	public static void main(String[] args) throws NamingException, JMSException {
		// Properties properties = new Properties();
		// properties.setProperty("java.naming.factory.initial",
		// "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		//
		// properties.setProperty("java.naming.provider.url",
		// "tcp://192.168.0.94:61616");
		// properties.setProperty("queue.financeiro", "fila.financeiro");
//
//		InitialContext context = new InitialContext(properties);
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("financeiro");

		MessageProducer producer = session.createProducer(fila);
//		for (int i = 0; i < 100; i++) {
			Message message = session.createTextMessage("<pedido><id>13</id></pedido>");

			producer.send(message);

//		}

		new Scanner(System.in).nextLine(); // parar o programa para testar a
		// conexao

		connection.close();
		context.close();
	}
}
