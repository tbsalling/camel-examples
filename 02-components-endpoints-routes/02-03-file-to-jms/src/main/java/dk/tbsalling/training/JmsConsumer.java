package dk.tbsalling.training;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class JmsConsumer {

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(JmsConsumer.class);

    @Async
    public void run() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("TEST.TOPIC");
        session.createConsumer(destination);
        MessageConsumer consumer = session.createConsumer(destination);

        while(true) {
            Message message = consumer.receive();

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                LOG.info("Received: " + text);
            } else if (message instanceof ActiveMQObjectMessage) {
                ActiveMQObjectMessage activeMQMessage = (ActiveMQObjectMessage) message;
                LOG.info("Received: " + activeMQMessage.getObject().getClass().getSimpleName() + ": " + activeMQMessage.getObject());
            } else {
                LOG.info("Received: " + message);
            }
        }
    }

}
