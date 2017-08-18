package com.example.test;

import com.example.util.CommonUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import javax.xml.soap.Text;

/**
 * 主题测试
 * Created by Administrator on 2017/8/18.
 */
public class TopicTest {
    /**
     * 推送测试
     * @throws JMSException
     */
    @Test
    public void testPublish() throws JMSException {
        Connection connection = CommonUtil.getConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic");
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("hello, activemq");
        producer.send(topic, textMessage);
        session.close();
        connection.close();
    }

    /**
     * 订阅测试
     * @throws JMSException
     */
    @Test
    public void testSubscribe() throws JMSException {
        Connection connection = CommonUtil.getConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic");
        MessageConsumer consumer = session.createConsumer(topic);
        Message message = consumer.receive();
        System.out.println(((TextMessage)message).getText());
        session.close();
        connection.close();
    }

    @Test
    public void testDurablePublish() throws JMSException {
        TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        TopicConnection topicConnection = factory.createTopicConnection();
        topicConnection.start();
        TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = topicSession.createTopic("durabe_topic");
        TopicPublisher publisher = topicSession.createPublisher(topic);
        TextMessage textMessage = topicSession.createTextMessage();
        textMessage.setText("hello, activemq");
//        publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
        publisher.send(textMessage);
        topicConnection.close();
    }

    @Test
    public void testDurableSubscribe() throws JMSException {
        TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        TopicConnection topicConnection = factory.createTopicConnection();
        topicConnection.setClientID("h34");
        topicConnection.start();
        TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = topicSession.createTopic("durabe_topic");
        TopicSubscriber durableSubscriber = topicSession.createDurableSubscriber(topic, "11");
        Message message = durableSubscriber.receive();
        System.out.println(((TextMessage)message).getText());
        topicConnection.close();
    }
}
