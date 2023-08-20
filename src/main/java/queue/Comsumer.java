package queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Comsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("","","");
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("demo");

            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println(textMessage);
                    try {
                        textMessage.acknowledge();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
