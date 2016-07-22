import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class KProdutor {
	
	public static void main(String[] args) throws NamingException, JMSException {
		InitialContext context = new InitialContext();
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection conn = cf.createConnection();
		Session s = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination loja =  (Destination) context.lookup("loja");
				
		MessageProducer producer = s.createProducer(loja);
		Message m = s.createTextMessage("luis fernando");
		m.setBooleanProperty("ebook", true);
		producer.send(m);
		
		
		producer.close();
		s.close();
		
		context.close();
		
		
	}

}
