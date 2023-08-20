package queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publisher {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("", "", "");
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("demo");

            TextMessage textMessage = session.createTextMessage("FirstMessage");
            MessageProducer messageProducer = session.createProducer(destination);
            messageProducer.send(textMessage);

            System.out.println("Message Published");
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
