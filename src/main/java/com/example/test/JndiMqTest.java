package com.example.test;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/8/13.
 */
public class JndiMqTest {
    public JndiMqTest() throws NamingException, IOException, TimeoutException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("connectionFactory");
        Connection connection = factory.createConnection();
        try{
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination)context.lookup("MyQueue");
            MessageProducer producer = session.createProducer(destination);
            TextMessage textMessage = session.createTextMessage("Hello, ActiveMQ");
            producer.send(textMessage);
            System.out.println("the message is " + textMessage.getText());
            session.close();
            connection.close();
        }catch (Exception e) {

        }
    }
    public static void main(String[] args) {
        try {
//            BasicConfigurator.configure();
            new JndiMqTest();
            System.out.println("111");
        }catch (Exception e) {

        }
    }
}

class Producer{

}