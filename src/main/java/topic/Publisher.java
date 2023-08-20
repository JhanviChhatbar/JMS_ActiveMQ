package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publisher {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("", "", "");
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic("demo-topic");
            MessageProducer messageProducer = session.createProducer(destination);

            TextMessage textMessage = session.createTextMessage("New Message for topic");
            messageProducer.send(textMessage);

            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
