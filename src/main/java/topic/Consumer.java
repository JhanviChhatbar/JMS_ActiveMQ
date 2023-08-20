package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("", "", "");
        try {
            Connection connection = connectionFactory.createConnection();
            connection.setClientID("1");
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic("demo-topic");

            MessageConsumer messageConsumer = session.createDurableConsumer(topic, "Consumer-1");

            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println(textMessage.getText());
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
