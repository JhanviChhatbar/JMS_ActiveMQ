package queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

import javax.jms.*;

public class JsonConsumer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("", "","");
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            Destination destination = session.createQueue("demo");
            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener((message) -> {
                TextMessage textMessage = (TextMessage)message;

                JSONObject jsonObject = new JSONObject(textMessage);
                System.out.println(jsonObject);
                try {
                    textMessage.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            });


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
