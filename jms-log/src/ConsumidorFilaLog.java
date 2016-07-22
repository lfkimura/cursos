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

public class ConsumidorFilaLog {

	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		connection.start();

		Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
		Destination fila = (Destination) context.lookup("LOG");
		MessageConsumer consumer = session.createConsumer(fila);
		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message texto) {
				TextMessage message = (TextMessage)texto;
				try {
					System.out.println(message.getText());
					session.commit();
//					message.acknowledge();
				} catch (JMSException e) {
					e.printStackTrace();
					try {
						session.rollback();
					} catch (JMSException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});

		new Scanner(System.in).nextLine(); // parar o programa para testar a
											// conexao

		connection.close();
		context.close();
	}

}
