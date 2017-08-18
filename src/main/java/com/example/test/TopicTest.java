package com.example.test;

import com.example.util.CommonUtil;
import org.junit.Test;

import javax.jms.*;

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
    public void testSubcribe() throws JMSException {
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
}
