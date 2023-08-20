package queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

import javax.jms.*;

public class JsonPublisher {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("","","");
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("demo");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fromDate", "29-08-2023");
            jsonObject.put("toDate", "15-09-2023");

            TextMessage textMessage = session.createTextMessage(jsonObject.toString());

            MessageProducer messageProducer = session.createProducer(destination);
            messageProducer.send(textMessage);
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
