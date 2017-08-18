package com.example.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * Created by Administrator on 2017/8/12.
 */
public class ActiveMQTest {
    /**
     * 点对点消息测试
     */
    @Test
    public void senderTest() throws JMSException {
        MessageSender sender = new MessageSender();
        sender.sendMessage("hello, active message queue");
    }
    @Test
    public void recieverTest() throws JMSException {
        MessageReciever reciever = new MessageReciever();
        reciever.recieveMessage("destination");
    }

    @Test
    public void psTest() throws JMSException {
        Publish publish = new Publish();
        Subscribe subscribe = new Subscribe();
        publish.sendMessage("hello, activemq");
//        subscribe.subscribeMessage();
    }

}

/**
 * 消息生产者
 */
class MessageSender {
    private  ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;

    /**
     * 发送消息
     * @param message
     * @throws JMSException
     */
    public void sendMessage(String message) throws JMSException {
        factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("destination");
        TextMessage textMessage = session.createTextMessage();
        MessageProducer producer = session.createProducer(destination);
        textMessage.setText(message);
        producer.send(textMessage);
        session.close();
        connection.close();
    }

}

/**
 * 消息接收者
 */
class MessageReciever {
    private  ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;

    /**
     * 接收消息
     * @param from
     * @throws JMSException
     */
    public void recieveMessage(String from) throws JMSException {
        factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("destination");
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive();
        if(message instanceof TextMessage) {
            System.out.println("接收到的消息为：" + ((TextMessage)message).getText());
        }else {
            System.out.println("接收成功");
        }
        session.close();
        connection.close();
    }

}

class Publish {
    private  ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;

    /**
     * 发送消息
     * @param message
     * @throws JMSException
     */
    public void sendMessage(String message) throws JMSException {
        factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        connection = factory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("destination");
        Topic topic = session.createTopic("topic");
        MessageProducer producer = session.createProducer(topic);
        producer.send(session.createTextMessage("the pushMessage"));
        session.close();
        connection.close();
    }
}

class Subscribe {
    private  ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;

    /**
     * 接收消息
     * @param
     * @throws JMSException
     */
    public void subscribeMessage() throws JMSException {
        factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        connection = factory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("destination");
        MessageConsumer consumer = session.createConsumer(session.createTopic("topic"));
        Message message = consumer.receive();
        System.out.println(((TextMessage)message).getText());
        session.close();
        connection.close();
    }
}