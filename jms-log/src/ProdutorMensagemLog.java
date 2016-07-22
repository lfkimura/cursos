import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class ProdutorMensagemLog {
	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

		Connection connection = factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination destination = (Destination) context.lookup("LOG");
		MessageProducer producer = session.createProducer(destination);

		Message message = session.createTextMessage("INFO | P 1 .armazenando mensagem na fila");
		producer.send(message, DeliveryMode.NON_PERSISTENT, 1, 500000);

		Message message2 = session.createTextMessage("INFO | P 2 .armazenando mensagem na fila");
		producer.send(message2, DeliveryMode.PERSISTENT, 2, 500000);
		

		Message message3 = session.createTextMessage("ERROR | P 9 .armazenando mensagem na fila");
		producer.send(message3, DeliveryMode.PERSISTENT, 9, 500000);
		Message message4 = session.createTextMessage("ERROR | NP 9.armazenando mensagem na fila");
		producer.send(message4, DeliveryMode.NON_PERSISTENT, 9, 500000);

	}
}
