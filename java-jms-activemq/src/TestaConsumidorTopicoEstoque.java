
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.modelo.Pedido;

public class TestaConsumidorTopicoEstoque {
	
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = (Connection) factory.createConnection();
		
		connection.setClientID("estoque");
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); 
		
		Topic destination = (Topic) context.lookup("loja");
		
		MessageConsumer consumer = session.createDurableSubscriber(destination, "assinatura","ebook is NULL OR ebook = true", false);
		
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				ObjectMessage objectMessage = (ObjectMessage)message;

                try {
                    Pedido pedido = (Pedido) objectMessage.getObject();
                    System.out.println(pedido.getCodigo());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
			}
		});
		
		new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();
		
		
		
		
		
	}

}
