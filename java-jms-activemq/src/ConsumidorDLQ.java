import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.naming.InitialContext;

public class ConsumidorDLQ {
	
	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		
		Connection c = factory.createConnection();
		
		c.start();
		
		Session s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = (Destination) context.lookup("DLQ");
		MessageConsumer consumer = s.createConsumer(destination);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message m) {
				System.out.println(m);
				
			}
		});
		
		new Scanner(System.in).nextLine();
		
		s.close();
		c.close();
		context.close();
		
	}

}
